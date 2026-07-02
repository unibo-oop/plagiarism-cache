package it.unibo.oop.mge.c3d;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.unibo.oop.mge.c3d.geometry.Line;
import it.unibo.oop.mge.c3d.geometry.Point2D;

class LineImplTest {

    @Test
    public void test() {
        final Line a = Line.fromPoints(Point2D.fromDoubles(-10, -10), Point2D.fromDoubles(10, 10));
        assertEquals(10, a.solveFor(10));
    }

}
