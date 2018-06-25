package it.fpagano.pattern_recognition.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class LineFunction {

    /**
     * Slope.
     */
    private Double m;

    /**
     * y intercept, which is the y coordinate of the location where the line crosses the y axis
     */
    private Double b;

    /**
     * equazione di una retta passante per due punti
     * y = m * x + b
     */
    private final Predicate<Point> f = p -> p.y == m * p.x + b;

    /**
     * Equazione per ricavare il valore di y-intercept.
     * Dato un punto e la slope ricava b.
     */
    private static final BiFunction<Point, Double, Double> retrieveB = (p, slope) -> p.y - (slope * p.x);

    /**
     * delta value when check...
     */
    private static final double EPSILON = 0.0000001d;

    /**
     * Set of points that match the LineFunction f
     */
    private Set<Point> matchingPoint = new HashSet<>();

    private LineFunction(Double slope, Double b) {
        this.m = slope;
        this.b = b;
    }

    /**
     * Check if the given point belong to the line function. In that case the point will be added to the list
     * of points that belong the function.
     * @param p
     * @return true if the point will be part of the function. otherwise false.
     */
    public boolean pointsBelogToLineFunction(Point p) {
        if(f.test(p)) {
            return matchingPoint.add(p);
        }

        return false;
    }

    public Set<Point> getMatchingPoint() {
        return matchingPoint;
    }

    public static Optional<LineFunction> of(Point p1, Point p2) {
        if(p1.equals(p2)) {
            return Optional.empty();
        }

        double slope = calculateSlope(p1, p2);

        return Optional.of(new LineFunction(slope, retrieveB.apply(p2, slope)));
    }


    /**
     * Retrieve the slope between two points.
     *
     * Formula is: (p2.x - p1.x) / (p2.y - p1.y)
     *
     * @param p1
     * @param p2
     * @return the slope value based on the formula. if (p2.y - p1.y) is 0 a infinity value will be returned.
     */
    public static double calculateSlope(Point p1, Point p2) {
        int dx = p2.x - p1.x;
        if(dx == 0) {
            return Double.POSITIVE_INFINITY;
        }

        int dy = p2.y - p1.y;

        return dy / dx;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineFunction lineFunction = (LineFunction) o;
        return equals(m, lineFunction.m) &&
                equals(b, lineFunction.b);
    }

    private static boolean equals(final double a, final double b) {
        if (a == b) return true;
        return Math.abs(a - b) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Objects.hash(m, b);
    }

}