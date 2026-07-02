package rogue.model.items.potion;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class PotionTypeTest {

    private static final int POTION_I_MAX = 10;
    private static final int POTION_I_MIN = 5;
    private static final int POTION_II_MAX = 20;
    private static final int POTION_II_MIN = 15;
    private static final int POTION_III_MAX = 33;
    private static final int POTION_III_MIN = 25;
    private static final int POTION_IV_MAX = 50;
    private static final int POTION_IV_MIN = 40;
    private static final int POTION_V_VALUE = 100;
    private static final int CORRUPT_POTION_I_MAX = -3;
    private static final int CORRUPT_POTION_I_MIN = -1;
    private static final int CORRUPT_POTION_II_MAX = -5;
    private static final int CORRUPT_POTION_II_MIN = -3;

    private static final String TOO_HIGH = "too high";
    private static final String TOO_LOW = "too low";

    @Test
    public void testPotionI() {
        final int test = PotionType.POTION_I.getHpValue();
        assertTrue(TOO_HIGH, test <= POTION_I_MAX);
        assertTrue(TOO_LOW, test >= POTION_I_MIN);
    }

    @Test
    public void testPotionII() {
        final int test = PotionType.POTION_II.getHpValue();
        assertTrue(TOO_HIGH, test <= POTION_II_MAX);
        assertTrue(TOO_LOW, test >= POTION_II_MIN);
    }

    @Test
    public void testPotionIII() {
        final int test = PotionType.POTION_III.getHpValue();
        assertTrue(TOO_HIGH, test <= POTION_III_MAX);
        assertTrue(TOO_LOW, test >= POTION_III_MIN);
    }

    @Test
    public void testPotionIV() {
        final int test = PotionType.POTION_IV.getHpValue();
        assertTrue(TOO_HIGH, test <= POTION_IV_MAX);
        assertTrue(TOO_LOW, test >= POTION_IV_MIN);
    }

    @Test
    public void testPotionV() {
        final int test = PotionType.POTION_V.getHpValue();
        assertEquals(test, POTION_V_VALUE);
    }

    @Test
    public void testCorruptPotionI() {
        final int test = PotionType.CORRUPT_POTION_I.getHpValue();
        assertTrue(TOO_HIGH, test >= CORRUPT_POTION_I_MAX);
        assertTrue(TOO_LOW, test <= CORRUPT_POTION_I_MIN);
    }

    @Test
    public void testCorruptPotionII() {
        final int test = PotionType.CORRUPT_POTION_II.getHpValue();
        assertTrue(TOO_HIGH, test >= CORRUPT_POTION_II_MAX);
        assertTrue(TOO_LOW, test <= CORRUPT_POTION_II_MIN);
    }
}
