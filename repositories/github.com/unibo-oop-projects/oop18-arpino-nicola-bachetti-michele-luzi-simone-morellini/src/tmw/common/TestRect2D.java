package tmw.common;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * This class test if rec2D works properly.
 */
public class TestRect2D {

    private final Rec2D rec = new Rec2D(new P2d(100, 50), new P2d(200, 100));
    private final Rec2D rec2 = new Rec2D(new P2d(300, 200), new P2d(400, 150));
    private final Rec2D rec3 = new Rec2D(new P2d(0, 0), new P2d(500, 500));
    private final Rec2D rec4 = new Rec2D(new P2d(100, 100), 100, 50);
    private final Rec2D rec5 = new Rec2D(new P2d(50, 75), new P2d(150, 150));
    private final Rec2D rec6 = new Rec2D(new P2d(300, 100), new P2d(400, 50));

    private final P2d point = new P2d(150, 75);
    private final P2d point1 = new P2d(300, 100);
    private final P2d point2 = new P2d(100, 100);
    private final P2d point3 = new P2d(200, 20);

    /**
     * Test the isPointIn method.
     */
    @Test
    public void testIsPointIn() {
        assertTrue(rec.isPointIn(point));
        assertFalse(rec.isPointIn(point1));
        assertTrue(rec.isPointIn(point2));
        assertFalse(rec.isPointIn(point3));
    }

    /**
     * Test the intersect.
     */
    @Test
    public void testIntersect() {
        assertTrue(rec.intersects(rec5));
        assertFalse(rec.intersects(rec6));
    }

    /**
     * Generic test to check if the rectangle is created in the right way.
     */
    @Test
    public void testGeneric() {
        assertEquals(rec.getWidth(), rec2.getWidth(), 0);
        assertEquals(rec.getHeight(), rec2.getHeight(), 0);
        assertNotEquals(rec.getWidth(), rec3.getWidth(), 0);
        assertNotEquals(rec.getHeight(), rec3.getHeight(), 0);
        assertNotEquals(rec, rec4);
    }

}
