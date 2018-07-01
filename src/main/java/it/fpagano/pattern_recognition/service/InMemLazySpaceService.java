package it.fpagano.pattern_recognition.service;

import it.fpagano.pattern_recognition.model.LineFunction;
import it.fpagano.pattern_recognition.model.Point;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class InMemLazySpaceService implements SpaceService {

  private final Set<Point> points = Collections.synchronizedSet(new HashSet<>());
  private final Map<LineFunction, Set<Point>> fs = new ConcurrentHashMap();

  @Override
  public Optional<Point> addPoint(Point p) {
    if(points.add(p)) {
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
    //for each point generate the lines and filter
    return null;
  }

  @Override
  public void deleteAll() {
    points.clear();
  }
}
