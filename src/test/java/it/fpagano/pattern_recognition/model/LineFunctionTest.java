package it.fpagano.pattern_recognition.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class LineFunctionTest {

    @Test
    public void whenPointsAreDifferent_LineFunctionMustExists() {
        Optional<LineFunction> f = LineFunction.of(new Point(0.0,0.0), new Point(2.0, 2.0));
        Assert.assertTrue(f.isPresent());
    }

    @Test
    public void whenPointsAreEquals_LineFunctionMustNotExists() {
        Optional<LineFunction> f = LineFunction.of(new Point(1.0,1.0), new Point(1.0, 1.0));
        Assert.assertFalse(f.isPresent());
    }

    @Test
    public void whenPointBelogToTheFunction_TheResultMustBeTrue() {
        Optional<LineFunction> f = LineFunction.of(new Point(1.0,1.0), new Point(2.0, 2.0));
        LineFunction lineFunction = f.get();
        boolean b = lineFunction.pointsBelogToLineFunction(new Point(0.0, 0.0));
        Assert.assertTrue(b);
    }

    @Test
    public void verticalPoints_MustBeOnTheSameLine() {
        Optional<LineFunction> f = LineFunction.of(new Point(1.0,1.0), new Point(1.0, 2.0));
        LineFunction lineFunction = f.get();
        boolean b = lineFunction.pointsBelogToLineFunction(new Point(1.0, 3.0));
        Assert.assertTrue(b);
    }

    @Test
    public void horizontalPoints_MustBeOnTheSameLine() {
        Optional<LineFunction> f = LineFunction.of(new Point(1.0,1.0), new Point(2.0, 1.0));
        LineFunction lineFunction = f.get();
        boolean b = lineFunction.pointsBelogToLineFunction(new Point(3.0, 1.0));
        Assert.assertTrue(b);
    }

    @Test
    public void calculateSlope() {
        double slope = LineFunction.calculateSlope(new Point(1.0, 1.0), new Point(2.0, 2.0));
        Assert.assertEquals(1.0, slope, LineFunction.EPSILON);
    }

    @Test
    public void whenPointsAreOnTheSameX_SlopeMustBeInfinite() {
        double slope = LineFunction.calculateSlope(new Point(1.0, 1.0), new Point(1.0, 2.0));
        Assert.assertTrue(Double.isInfinite(slope));
    }

    @Test
    public void whenPointsAreOnTheSameY_SlopeMustBeZero() {
        double slope = LineFunction.calculateSlope(new Point(1.0, 1.0), new Point(2.0, 1.0));
        Assert.assertEquals(0, slope, LineFunction.EPSILON);
    }

    /*
    todo: giving 4 different points, searching for 2 point, service must return
    n! / k!(n-k)!
    elements

    e.g. 4 different points must generate 6 lines.
     */
}