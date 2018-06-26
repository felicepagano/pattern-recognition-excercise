package it.fpagano.pattern_recognition.service;

import it.fpagano.pattern_recognition.model.LineFunction;
import it.fpagano.pattern_recognition.model.Point;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class InMemEagerService implements IService {

    private final Set<Point> points = Collections.synchronizedSet(new HashSet<>());
    private final Map<LineFunction, Set<Point>> fs = new ConcurrentHashMap();

    @Override
    public Optional<Point> addPoint(Point p) {
        if(points.add(p)) {
            for (final Point point: points) {
                Optional<LineFunction> f = LineFunction.of(point, p);
                f.ifPresent(lineFunction -> updateLines(lineFunction, point, p));
            }
            return Optional.of(p);
        }
        return Optional.empty();
    }

    @Override
    public Set<Point> getPoints() {
        return points;
    }

    @Override
    public Set<Set<Point>> getLines(int n) {
        return fs.values().stream()
                .filter(value -> value.size() >= n)
                .collect(Collectors.toSet());
    }

    @Override
    public void deleteAll() {
        points.clear();
    }

    private void updateLines(LineFunction lineFunction, Point p1, Point p2) {
        fs.merge(lineFunction, Set.of(p1, p2), (l1, l2) -> Stream.concat(l1.stream(), l2.stream())
                .collect(Collectors.toSet()));

    }
}
