package rogue.model.items.scroll;

import org.junit.Test;

import rogue.model.creature.Player;
import rogue.model.creature.PlayerFactoryImpl;
import rogue.model.creature.PlayerLifeImpl;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class ScrollImplTest {

    /*
     * TODO update TESTS
     */

    private static final int STRENGTH_3 = 3;
    private Player pl;

    @Test
    public void testGetStrengthValue() {
        final Scroll scroll = new ScrollImpl(ScrollType.SCROLL_II);
        /*
         * After creating the scroll item, strength value
         * should be the same every time.
         */
        final int value = scroll.getEffectValue();
        assertEquals(value, scroll.getEffectValue());
    }

    @Test
    public void testUseGain() {
        pl = new PlayerFactoryImpl().create();
        final Scroll scroll = new ScrollImpl(ScrollType.SCROLL_II);
        /*
         * No max for scroll.
         * Expect true and correct strength update.
         */
        final int beforeValue = pl.getLife().getStrength();
        assertTrue(scroll.use(pl));
        assertEquals(beforeValue + scroll.getEffectValue(), pl.getLife().getStrength());
    }

    @Test
    public void testCorruptBelowZero() {
        /*
         * Strength cannot go below 0, set strength to 3
         * and use scroll.
         * Expect true and strength set to 0.
         */
        pl = new PlayerFactoryImpl().createByLife(new PlayerLifeImpl.Builder().initStrength(STRENGTH_3).build());
        final Scroll scroll = new ScrollImpl(ScrollType.CORRUPT_SCROLL_II);
        assertEquals(STRENGTH_3, pl.getLife().getStrength());
        assertTrue(scroll.use(pl));
        assertEquals(0, pl.getLife().getStrength());
    }
}
