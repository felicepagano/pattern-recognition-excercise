package it.fpagano.pattern_recognition.model;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Space {

    private final Set<Point> points = Collections.synchronizedSet(new HashSet<>());
    private final Map<LineFunction, Set<Point>> fs = new ConcurrentHashMap<>();

    public void addPoint(Point p) {
        if(points.add(p)) {
            for (Point point: points) {
                Optional<LineFunction> f = LineFunction.of(point, p);
                f.ifPresent(lineFunction -> updateLines(lineFunction, point, p));
            }
        }
    }

    private void updateLines(LineFunction lineFunction, Point p1, Point p2) {
        fs.merge(lineFunction, Set.of(p1, p2), (l1, l2) -> Stream.concat(l1.stream(), l2.stream())
                .collect(Collectors.toSet()));

    }

    public Set<Set<Point>> getLines(int n) {
        return fs.entrySet().stream()
                .filter(entry -> entry.getValue().size() >= n)
                .map(Map.Entry::getValue)
                .collect(Collectors.toSet());
    }


}
