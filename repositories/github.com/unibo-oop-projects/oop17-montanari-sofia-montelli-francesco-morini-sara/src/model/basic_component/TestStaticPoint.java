package model.basic_component;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
/**
 * Test Static Point.
 */
public class TestStaticPoint {
    private StaticPoint2D p1;
    private static final int INITIAL_X = 2;
    private static final int INITIAL_Y = 3;

    /**
     * Initialized the point used in the test.
     */
    @Before public void init() {
        p1 = new StaticPoint2DImpl(INITIAL_X, INITIAL_Y);
    }

    /**
     * Test for the static point.
     */
    @Test
    public void test() {
        StaticPoint2D point2D = new StaticPoint2DImpl(0, 0);
        assertEquals("x = 0", point2D.getX(), 0);
        assertEquals("y = 0", point2D.getY(), 0);

        point2D = new StaticPoint2DImpl(1, 0);
        assertEquals("x = 1", point2D.getX(), 1);
        assertEquals("y = 0", point2D.getY(), 0);

        point2D = new StaticPoint2DImpl(0, 1);
        assertEquals("x = 0", point2D.getX(), 0);
        assertEquals("y = 1", point2D.getY(), 1);

        point2D = new StaticPoint2DImpl(3, 1);
        assertEquals("x = 3", point2D.getX(), 3);
        assertEquals("y = 1", point2D.getY(), 1);
    }

    /**
     * Test the equals method.
     */
    @Test
    public void testEquals() {
        final StaticPoint2D p2 = new StaticPoint2DImpl(INITIAL_X, INITIAL_Y);
        assertEquals("two point with the same coordinate shiuld be equal", p1, p2);
    }

    /**
     * Test the adjacency between point.
     */
    @Test
    public void testDistance() {
        final StaticPoint2D p2 = new StaticPoint2DImpl(INITIAL_X, INITIAL_Y);
        final StaticPoint2D p3 = new StaticPoint2DImpl(INITIAL_X + 1, INITIAL_Y);
        final StaticPoint2D p4 = new StaticPoint2DImpl(INITIAL_X, INITIAL_Y + 2);
        final StaticPoint2D p5 = new StaticPoint2DImpl(INITIAL_X + 1, INITIAL_Y + 1);
        final StaticPoint2D p6 = new StaticPoint2DImpl(INITIAL_X + 2, INITIAL_Y + 2);
        assertEquals("if two point are equal their distance must be zero", p1.getDistance(p2), 0);
        assertEquals("distance between p1 e p3 = 1", p1.getDistance(p3), 1);
        assertEquals("distance between p1 e p4 = 2", p1.getDistance(p4), 2);
        assertEquals("distance between p1 e p5 = 1", p1.getDistance(p5), 1);
        assertEquals("distance between p1 e p6 = 2", p1.getDistance(p6), 2);
    }

    /**
     * Test for the adjacency in the same position.
     */
    @Test
    public void testAdjacentSame() {
        final StaticPoint2D p2 = new StaticPoint2DImpl(INITIAL_X, INITIAL_Y);
        assertTrue("two point in the same position should be adjacent", p1.checkAdjacent(p2));
    }

    /**
     * Test for the adjacency in horizontal.
     */
    @Test
    public void testAdjacentOrizzontal() {
        final StaticPoint2D p2 = new StaticPoint2DImpl(INITIAL_X + 1, INITIAL_Y);
        assertTrue("two point with the same Y should be adjacent", p1.checkAdjacent(p2));
    }

    /**
     * Test for the adjacency in vertical.
     */
    @Test
    public void testAdjacentVertical() {
        final StaticPoint2D p2 = new StaticPoint2DImpl(INITIAL_X, INITIAL_Y + 1);
        assertTrue("two point with the same X should be adjacent", p1.checkAdjacent(p2));
    }

    /**
     * Test for the adjacency in diagonal.
     */
    @Test
    public void testAdjacentDiagonal() {
        final StaticPoint2D p2 = new StaticPoint2DImpl(INITIAL_X + 1, INITIAL_Y + 1);
        assertTrue("two point in a diagonal allignament should be adjacent", p1.checkAdjacent(p2));
    }

    /**
     * Test if two dot are not adjacent.
     */
    @Test
    public void testNotAdjacent() {
        final StaticPoint2D p2 = new StaticPoint2DImpl(INITIAL_X + 2, INITIAL_Y + 1);
        assertFalse("this two point should not be adjacent", p1.checkAdjacent(p2));
    }

    /**
     * Test for the aligned method.
     */
    @Test
    public void aligned() {
        assertTrue("same Y, the point should be alligned", p1.alligned(new StaticPoint2DImpl(INITIAL_X + 1, INITIAL_Y)));
        assertFalse("a diagonal shape is not considered and allignament", p1.alligned(new StaticPoint2DImpl(1, 1)));
    }
}
