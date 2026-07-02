package casim.utils.range;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Test class for {@link Ranges}.
 */
class RangesTest {

    private static final int INT_RANGE_START = 0;
    private static final int INT_RANGE_END = 10;
    private static final int INT_RANGE_STEP = 2;
    private static final double DOUBLE_RANGE_START = 0.5;
    private static final double DOUBLE_RANGE_END = 5.0;
    private static final double DOUBLE_RANGE_STEP = 1.5;

    /**
     * Test for {@link Ranges#of(Comparable, Comparable, java.util.function.Function)} method. 
     */
    @Test
    void testOfGeneric() {
        final var emptyRange = Ranges.of(INT_RANGE_START, INT_RANGE_START, x -> x + 1).iterator();
        final var range1 = Ranges.of(INT_RANGE_START, INT_RANGE_END, x -> x + INT_RANGE_STEP).iterator();
        final var range2 = Ranges.of(DOUBLE_RANGE_START, DOUBLE_RANGE_END, x -> x + DOUBLE_RANGE_STEP).iterator();
        assertFalse(emptyRange.hasNext());
        for (int i = INT_RANGE_START; i < INT_RANGE_END; i += INT_RANGE_STEP) {
            assertTrue(range1.hasNext());
            assertEquals(i, range1.next());
        }
        assertFalse(range1.hasNext());
        for (double i = DOUBLE_RANGE_START; i < DOUBLE_RANGE_END; i += DOUBLE_RANGE_STEP) {
            assertTrue(range2.hasNext());
            assertEquals(i, range2.next());
        }
        assertFalse(range2.hasNext());
    }

    /**
     * Test for {@link Ranges#of(int, int, int))} method. 
     */
    @Test
    void testOfIntegerWithStep() {
        final var emptyRange = Ranges.of(INT_RANGE_START, INT_RANGE_START, 1).iterator();
        final var range1 = Ranges.of(INT_RANGE_START, INT_RANGE_END, INT_RANGE_STEP).iterator();
        assertFalse(emptyRange.hasNext());
        for (int i = INT_RANGE_START; i < INT_RANGE_END; i += INT_RANGE_STEP) {
            assertTrue(range1.hasNext());
            assertEquals(i, range1.next());
        }
    }

    /**
     * Test for {@link Ranges#of(int, int))} method. 
     */
    @Test
    void testOfInteger() {
        final var emptyRange = Ranges.of(INT_RANGE_START, INT_RANGE_START).iterator();
        final var range1 = Ranges.of(INT_RANGE_START, INT_RANGE_END).iterator();
        assertFalse(emptyRange.hasNext());
        for (int i = INT_RANGE_START; i < INT_RANGE_END; i++) {
            assertTrue(range1.hasNext());
            assertEquals(i, range1.next());
        }
    }

    /**
     * Test for {@link Ranges#of(double, double, double))} method. 
     */
    @Test
    void testOfDouble() {
        final var range = Ranges.of(DOUBLE_RANGE_START, DOUBLE_RANGE_END, x -> x + DOUBLE_RANGE_STEP).iterator();
        for (double i = DOUBLE_RANGE_START; i < DOUBLE_RANGE_END; i += DOUBLE_RANGE_STEP) {
            assertTrue(range.hasNext());
            assertEquals(i, range.next());
        }
        assertFalse(range.hasNext());
    }
}
