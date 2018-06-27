package it.fpagano.pattern_recognition.model;

import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;

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
     * x intercept. used when slope is infinite.
     */
    private Double xi;

    /**
     * Equazione per ricavare il valore di y-intercept.
     * Dato un punto e la slope ricava b.
     */
    private static final BiFunction<Point, Double, Double> retrieveB = (p, slope) -> p.y - (slope * p.x);

    /**
     * delta value when check doubles.
     */
    public static final double EPSILON = 0.0000001d;

    private LineFunction(Double slope, Double b, Double xi) {
        this.m = slope;
        this.b = b;
        this.xi = xi;
    }

    /**
     * y = m * x + b
     * @param p point to test
     * @return true if the point belong to this linefunction
     */
    public boolean pointsBelogToLineFunction(Point p) {
        if(Double.isInfinite(m) && checkDoubleEq(xi, p.x)) {
            return true;
        }
        return p.y == m * p.x + b;
    }

    /**
     * Two linefunctions are equals if the slope, x-intercept and y-intercept are equals.
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineFunction that = (LineFunction) o;
        return checkDoubleEq(m, that.m) &&
                checkDoubleEq(b, that.b) &&
                checkDoubleEq(xi, that.xi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(m, b, xi);
    }

    /**
     * Given two point, initialize the LineFunction.
     * @param p1
     * @param p2
     * @return
     */
    public static Optional<LineFunction> of(Point p1, Point p2) {
        if(p1.equals(p2)) {
            return Optional.empty();
        }

        double slope = calculateSlope(p1, p2);
        Double b = retrieveB.apply(p2, slope);
        Double xi = Double.isInfinite(b) ? p2.x : Double.NaN;
        return Optional.of(new LineFunction(slope, b, xi));
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
        double dx = p2.x - p1.x;
        if(dx == 0) {
            return Double.POSITIVE_INFINITY;
        }

        double dy = p2.y - p1.y;

        double slope = dy / dx;
        return slope;
    }

    /**
     * Check if two double are equals.
     *
     * This method identify two NaN as equals. Comparison will consider an epsilon
     * @param a
     * @param b
     * @return
     */
    private static boolean checkDoubleEq(final double a, final double b) {
        if(Double.isNaN(a) && Double.isNaN(b)) return true;
        if (a == b) return true;
        return Math.abs(a - b) < EPSILON;
    }

}
