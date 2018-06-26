package it.fpagano.pattern_recognition.service;

import it.fpagano.pattern_recognition.model.Point;

import java.util.Optional;
import java.util.Set;

public interface IService {

    Optional<Point> addPoint(Point p);
    Set<Point> getPoints();
    Set<Set<Point>> getLines(int n);
    void deleteAll();
}
