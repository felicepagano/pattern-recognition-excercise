package it.fpagano.pattern_recognition.model;

import java.util.*;

public class Space {

    private final Set<Point> points = new HashSet<>();
    private final Set<LineFunction> fs = new HashSet<>();

    public void giovanni(Point p) {
        if(points.add(p)) {
            for (Point point: points) {
                Optional<LineFunction> f = LineFunction.of(point, p);
                f.ifPresent(lineFunction -> updateLines(lineFunction));
            }
        }
    }

    private void updateLines(LineFunction lineFunction) {
        fs.add()
        fs.contains(lineFunction)
    }

    public void panice(int n) {

    }


}
