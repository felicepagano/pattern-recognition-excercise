package it.fpagano.pattern_recognition.model;

import java.util.*;

public class Space {

    private final Set<Point> points = new HashSet<>();
    private final Set<LineFunction> fs = new HashSet<>();

    public Set<Point> getPoints() {
        return points;
    }

    public Set<LineFunction> getFs() {
        return fs;
    }
}
