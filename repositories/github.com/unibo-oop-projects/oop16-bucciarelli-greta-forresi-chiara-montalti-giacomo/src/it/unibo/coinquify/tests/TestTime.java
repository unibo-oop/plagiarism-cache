package it.unibo.coinquify.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import it.unibo.coinquify.utils.Time;
import it.unibo.coinquify.utils.TimeImpl;

/**
 * Test time implementation.
 *
 */
public class TestTime {
    /**
     * test time.
     */
    @Test
    public void testTime() {
        final Time t1 = new TimeImpl(10, 0);
        final Time t2 = new TimeImpl(10, 10);
        assertFalse(t1.equals(t2));
        assertFalse(t2.before(t1));
        assertTrue(t1.before(t2));
        assertEquals(t1.getHour(), t2.getHour());
        assertTrue(t1.getMinutes() < t2.getMinutes());

        // t1 now is 0:0
        t1.setHour(0);
        t1.setMinutes(0);
        assertTrue(t1.after(t2));

        // t2 is 0:1
        t2.setHour(0);
        t2.setMinutes(1);
        // in this implementation 0:1 isn't after 0:0
        assertFalse(t2.after(t1));
    }
}
