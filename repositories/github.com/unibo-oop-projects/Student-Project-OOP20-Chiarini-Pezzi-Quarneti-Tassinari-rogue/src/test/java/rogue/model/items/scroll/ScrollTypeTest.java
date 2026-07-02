package rogue.model.items.scroll;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class ScrollTypeTest {

    private static final int SCROLL_I_DURATION = 15;
    private static final int SCROLL_II_DURATION = 25;
    private static final int SCROLL_III_DURATION = 15;
    private static final int SCROLL_IV_DURATION = 25;
    private static final int SCROLL_V_DURATION = 15;
    private static final int CORRUPT_SCROLL_I_DURATION = 30;
    private static final int CORRUPT_SCROLL_II_DURATION = 20;

    private static final int SCROLL_I_MAX = 3;
    private static final int SCROLL_I_MIN = 1;
    private static final int SCROLL_II_MAX = 3;
    private static final int SCROLL_II_MIN = 1;
    private static final int SCROLL_III_MAX = 5;
    private static final int SCROLL_III_MIN = 3;
    private static final int SCROLL_IV_MAX = 5;
    private static final int SCROLL_IV_MIN = 3;
    private static final int SCROLL_V_MAX = 7;
    private static final int SCROLL_V_MIN = 5;
    private static final int CORRUPT_SCROLL_I_MAX = -3;
    private static final int CORRUPT_SCROLL_I_MIN = -1;
    private static final int CORRUPT_SCROLL_II_MAX = -5;
    private static final int CORRUPT_SCROLL_II_MIN = -3;

    private static final String TOO_HIGH = "too high";
    private static final String TOO_LOW = "too low";

    @Test
    public void testEffectDuration() {
        assertEquals(SCROLL_I_DURATION, ScrollType.SCROLL_I.getEffectDuration());
        assertEquals(SCROLL_II_DURATION, ScrollType.SCROLL_II.getEffectDuration());
        assertEquals(SCROLL_III_DURATION, ScrollType.SCROLL_III.getEffectDuration());
        assertEquals(SCROLL_IV_DURATION, ScrollType.SCROLL_IV.getEffectDuration());
        assertEquals(SCROLL_V_DURATION, ScrollType.SCROLL_V.getEffectDuration());
        assertEquals(CORRUPT_SCROLL_I_DURATION, ScrollType.CORRUPT_SCROLL_I.getEffectDuration());
        assertEquals(CORRUPT_SCROLL_II_DURATION, ScrollType.CORRUPT_SCROLL_II.getEffectDuration());
    }

    @Test
    public void testScrollI() {
        final int test = ScrollType.SCROLL_I.getEffectValue();
        assertTrue(TOO_HIGH, test <= SCROLL_I_MAX);
        assertTrue(TOO_LOW, test >= SCROLL_I_MIN);
    }

    @Test
    public void testScrollII() {
        final int test = ScrollType.SCROLL_II.getEffectValue();
        assertTrue(TOO_HIGH, test <= SCROLL_II_MAX);
        assertTrue(TOO_LOW, test >= SCROLL_II_MIN);
    }

    @Test
    public void testScrollIII() {
        final int test = ScrollType.SCROLL_III.getEffectValue();
        assertTrue(TOO_HIGH, test <= SCROLL_III_MAX);
        assertTrue(TOO_LOW, test >= SCROLL_III_MIN);
    }

    @Test
    public void testScrollIV() {
        final int test = ScrollType.SCROLL_IV.getEffectValue();
        assertTrue(TOO_HIGH, test <= SCROLL_IV_MAX);
        assertTrue(TOO_LOW, test >= SCROLL_IV_MIN);
    }

    @Test
    public void testScrollV() {
        final int test = ScrollType.SCROLL_V.getEffectValue();
        assertTrue(TOO_HIGH, test <= SCROLL_V_MAX);
        assertTrue(TOO_LOW, test >= SCROLL_V_MIN);
    }

    @Test
    public void testCorruptScrollI() {
        final int test = ScrollType.CORRUPT_SCROLL_I.getEffectValue();
        assertTrue(TOO_HIGH, test >= CORRUPT_SCROLL_I_MAX);
        assertTrue(TOO_LOW, test <= CORRUPT_SCROLL_I_MIN);
    }

    @Test
    public void testCorruptScrollII() {
        final int test = ScrollType.CORRUPT_SCROLL_II.getEffectValue();
        assertTrue(TOO_HIGH, test >= CORRUPT_SCROLL_II_MAX);
        assertTrue(TOO_LOW, test <= CORRUPT_SCROLL_II_MIN);
    }
}
