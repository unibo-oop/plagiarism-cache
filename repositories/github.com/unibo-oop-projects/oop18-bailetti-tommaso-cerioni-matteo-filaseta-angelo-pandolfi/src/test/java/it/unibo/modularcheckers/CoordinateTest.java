package it.unibo.modularcheckers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import it.unibo.modularcheckers.model.Coordinate;

/**
 * Test class for coordinate class.
 * CHECKSTYLE: MagicNumber OFF
 */
public class CoordinateTest {

    /**
     * Testing coordinate constructor & get methods.
     */
    @Test
    public void testBasic() {
        final Coordinate c = new Coordinate(5, 10);
        assertEquals("X must be equals to 5", (Integer) 5, c.getX());
        assertEquals("Y must be equals to 10", (Integer) 10, c.getY());
    }

    /**
     * Testing coordinate equals.
     */
    @Test
    public void testEquals() {
        final Coordinate c1 = new Coordinate(15, 25);
        final Coordinate c2 = new Coordinate(15, 25);
        final Coordinate c3 = new Coordinate(15, 22);
        assertNotEquals(c1, c3);
        assertEquals("C1 must be equals to C2", c1, c2);
    }
}
