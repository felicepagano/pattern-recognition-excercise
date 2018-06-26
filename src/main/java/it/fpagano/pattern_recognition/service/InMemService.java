package it.fpagano.pattern_recognition.service;

import it.fpagano.pattern_recognition.model.LineFunction;
import it.fpagano.pattern_recognition.model.Point;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemService implements IService {

    private final Set<Point> points = Collections.synchronizedSet(new HashSet<>());
    private final Map<LineFunction, Set<Point>> fs = new ConcurrentHashMap();

    @Override
    public Point addPoint(Point p) {
        if(points.add(p)) {
            for (Point point: points) {
                Optional<LineFunction> f = LineFunction.of(point, p);
                f.ifPresent(lineFunction -> updateLines(lineFunction));
            }
            return p;
        }
        return null;
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

    private void updateLines(LineFunction lineFunction) {

    }
}
