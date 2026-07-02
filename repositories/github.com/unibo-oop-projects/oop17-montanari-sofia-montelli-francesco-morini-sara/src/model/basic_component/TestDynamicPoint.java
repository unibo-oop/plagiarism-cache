package model.basic_component;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * Class Test of Dynamic Point.
 */
public class TestDynamicPoint {
    private DynamicPoint2D p1;
    private static final int INITIAL_X = 2;
    private static final int INITIAL_Y = 3;

    /**
     * Initialized the point used in the test.
     */
    @Before 
    public void init() {
        p1 = new DynamicPoint2DImpl(2, 3);
    }

    /**
     * Test Basic.
     */
    @Test
    public void testBasic() {
        assertEquals("the initial X should be the one specified in the constructor", p1.getX(), INITIAL_X);
        assertEquals("the initial Y should be the one specified in the constructor", p1.getY(), INITIAL_Y);
    }
    /**
     * Test MoveTo.
     */
    @Test
    public void testMoveTo() {
        final int pointX = 2;
        final int pointY = 4;
        p1.moveTo(pointX, pointY);
        assertEquals("now the X should be the one specifed into the move method", p1.getX(), pointX);
        assertEquals("now the X should be the one specifed into the move method", p1.getY(), pointY);
    }
    /**
     * Test Translation.
     */
    @Test
    public void testTranslation() {
        final int deltaX = 1;
        final int deltaY = 1;
        p1.traslate(1, 1);
        assertEquals("we should have traslate by the specified value", p1.getX(), INITIAL_X + deltaX);
        assertEquals("we should have traslate of the specified value", p1.getY(), INITIAL_Y + deltaY);
    }

    /**
     * Test for the equals method.
     */
    @Test
    public void testEquals() {
        final StaticPoint2D p2 = new DynamicPoint2DImpl(INITIAL_X, INITIAL_Y);
        assertEquals("two point with the same coordinate should be equal", p1, p2);
    }
}
