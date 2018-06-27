package it.fpagano.pattern_recognition.service;

import it.fpagano.pattern_recognition.model.LineFunction;
import it.fpagano.pattern_recognition.model.Point;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This implementation will keep in memory the list of the points added by the user
 * and a map that group by all the points related to a linefunction.
 *
 * This implementation is eager, it means that everytime a point is added a computation
 * is made for each of the already existing point.
 */
@Service
public class InMemEagerSpaceService implements SpaceService {

    private final Set<Point> points = Collections.synchronizedSet(new HashSet<>());
    private final Map<LineFunction, Set<Point>> fs = new ConcurrentHashMap();

    @Override
    public Optional<Point> addPoint(Point p) {
        if (points.add(p)) {
            points
                //consider to use parallelism .parallelStream()
                .stream()
                .forEach(point -> findAndUpdateLineFunctionForThePoint(p, point));
            return Optional.of(p);
        }
        return Optional.empty();
    }

    private void findAndUpdateLineFunctionForThePoint(Point p, Point point) {
        Optional<LineFunction> f = LineFunction.of(point, p);
        f.ifPresent(lineFunction -> updateLines(lineFunction, point, p));
    }

    @Override
    public Set<Point> getPoints() {
        return points;
    }

    @Override
    public Set<Set<Point>> getLines(int n) {
        return fs.values()
                // consider to use parallelism .parallelStream()
                .stream()
                .filter(value -> value.size() >= n)
                .collect(Collectors.toSet());
    }

    @Override
    public void deleteAll() {
        points.clear();
    }

    /**
     * Update the points related to a line function
     * @param lineFunction
     * @param p1
     * @param p2
     */
    private void updateLines(LineFunction lineFunction, Point p1, Point p2) {
        fs.merge(lineFunction, Set.of(p1, p2), this::concatLineFunctionsPoints);
    }

    private Set<Point> concatLineFunctionsPoints(Set<Point> l1, Set<Point> l2) {
        return Stream.concat(l1.stream(), l2.stream())
                .collect(Collectors.toSet());
    }
}
