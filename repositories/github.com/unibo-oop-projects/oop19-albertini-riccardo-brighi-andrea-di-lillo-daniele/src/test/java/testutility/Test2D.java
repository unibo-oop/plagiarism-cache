package testutility;

import element.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Test2D {

    private static final double DOUBLE_PRECISION = 0.1;
    private static final double RADIANS_PRECISION = 0.1;
    private static final double DEGREES_PRECISION = 4.0;

    @Test
    void testDistancePoint2D() {
        final Point2D p0 = Elements.ORIGIN;
        final Point2D p1 = new Point2DImpl(0, 1);

        assertEquals(1, p0.distance(p1), DOUBLE_PRECISION);
    }

    @Test
    void testSubtractionPoint2D() {
        final Point2D p0 = new Point2DImpl(0, 1);
        final Point2D p1 = new Point2DImpl(1, 2);

        assertEquals(new Vector2DImpl(1, 1), p1.subtraction(p0));
        assertEquals(new Vector2DImpl(-1, -1), p0.subtraction(p1));
    }

    @Test
    void testSumVector2D() {
        final Point2D p = new Point2DImpl(0, 1);
        final Vector2D v = new Vector2DImpl(1, 0);

        assertEquals(1.0, v.getModulus(), DOUBLE_PRECISION);
        assertEquals(0, v.getRadiansAngle(), RADIANS_PRECISION);
        assertEquals(new Point2DImpl(1, 1), p.sum(v));
    }


    @Test
    void testNormalizedVector2D() {
        final Vector2D v = new Vector2DImpl(3, 4);
        assertEquals(new Vector2DImpl(3 / 5.0, 4 / 5.0), v.getNormalizedVector());
        assertEquals(5.0, v.getModulus(), DOUBLE_PRECISION);
    }

    @Test
    void testAngleVector2D() {
        Vector2D v = new Vector2DImpl(2, 1);
        assertEquals(Math.sqrt(5.0), v.getModulus(), DOUBLE_PRECISION);
        assertEquals(Math.PI / 6, v.getRadiansAngle(), RADIANS_PRECISION);
        assertEquals(30, v.getDegreesAngle(), 4);

        v = new Vector2DImpl(-2, 1);
        assertEquals(Math.sqrt(5.0), v.getModulus(), DOUBLE_PRECISION);
        assertEquals(5 * Math.PI / 6, v.getRadiansAngle(), RADIANS_PRECISION);
        assertEquals(150, v.getDegreesAngle(), DEGREES_PRECISION);

        v = new Vector2DImpl(-2, -1);
        assertEquals(Math.sqrt(5.0), v.getModulus(), DOUBLE_PRECISION);
        assertEquals(-5 * Math.PI / 6, v.getRadiansAngle(), RADIANS_PRECISION);
        assertEquals(-150, v.getDegreesAngle(), DEGREES_PRECISION);

        v = new Vector2DImpl(2, -1);
        assertEquals(Math.sqrt(5.0), v.getModulus(), DOUBLE_PRECISION);
        assertEquals(-Math.PI / 6, v.getRadiansAngle(), RADIANS_PRECISION);
        assertEquals(-30, v.getDegreesAngle(), DEGREES_PRECISION);

        v = new Vector2DImpl(-1, 0);
        assertEquals(Math.sqrt(1.0), v.getModulus(), DOUBLE_PRECISION);
        assertEquals(Math.PI, v.getRadiansAngle(), RADIANS_PRECISION);
        assertEquals(180, v.getDegreesAngle(), DEGREES_PRECISION);
    }

    @Test
    void testNullVector2D() {
        final Vector2D v = Elements.VECTOR_NULL;
        assertEquals(Math.sqrt(0.0), v.getModulus(), DOUBLE_PRECISION);
        assertTrue(v.isNullVector());
        assertThrows(IllegalStateException.class, v::getRadiansAngle);
        assertThrows(IllegalStateException.class, v::getDegreesAngle);
        assertThrows(IllegalStateException.class, v::getNormalizedVector);
    }

    @Test
    void testRound() {
        assertEquals(13.000_005, Elements.round(13.000_004_9, 6));

        assertEquals(213.000_004, Elements.round(213.000_004_4, 6));
    }
}
