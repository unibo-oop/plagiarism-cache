package rogue.model.items.potion;

import org.junit.Test;

import rogue.model.creature.Player;
import rogue.model.creature.PlayerFactoryImpl;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

public class PotionImplTest {

    private static final int REMOVE_AMOUNT_40 = 40;
    private static final int REMOVE_AMOUNT_10 = 10;
    private static final int PLAYER_START_LIFE = 12;

    private Player pl;

    @Test
    public void testGetHpValue() {
        final PotionImpl potion = new PotionImpl(PotionType.POTION_II);
        /*
         * Once the potion's created, getHpValue should 
         * return the same value every time. Only PotionType's
         * getHpValue random.
         */
        final int value = potion.getHpValue();
        assertEquals(value, potion.getHpValue());
    }

    @Test
    public void testUseWithNormalHealth() {
        pl = new PlayerFactoryImpl().create();
        final PotionImpl potion = new PotionImpl(PotionType.POTION_II);
        /*
         * Trying to use a potion without max health.
         * Expecting true return and correct update of the health.
         */
        // player health points are initially 12
        final int hp = pl.getLife().getHealthPoints();
        assertFalse(potion.use(pl));
        // player max health points is 12
        assertEquals(PLAYER_START_LIFE, pl.getLife().getHealthPoints());
        final var exp = 50;
        pl.getLife().increaseExperience(exp);
        assertTrue(potion.use(pl));
        assertEquals(hp + potion.getHpValue(), pl.getLife().getHealthPoints());
    }

    @Test
    public void testWithMaxHealth() {
        pl = new PlayerFactoryImpl().create();
        final PotionImpl potion = new PotionImpl(PotionType.POTION_II);
        /*
         * Using potion with max health.
         */
        assertFalse(potion.use(pl));
    }

    @Test
    public void testExceedMaxHealth() {
        pl = new PlayerFactoryImpl().create();
        final PotionImpl potion = new PotionImpl(PotionType.POTION_II);
        /*
         * Use potion that would exceed the max health,
         * expecting true return and correct health update.
         * potion II give 15-20 removing 10.
         */
        pl.getLife().hurt(REMOVE_AMOUNT_10);
        assertTrue(potion.use(pl));
        assertEquals(pl.getLife().getMaxHealthPoints(), pl.getLife().getHealthPoints());
    }

    @Test
    public void useCorruptWithMaxHealth() {
        pl = new PlayerFactoryImpl().create();
        final PotionImpl potion = new PotionImpl(PotionType.CORRUPT_POTION_I);
        /*
         * Use a corrupt potion with max health,
         * Except true and correct health update.
         */
        assertTrue(potion.use(pl));
        assertEquals(pl.getLife().getMaxHealthPoints() + potion.getHpValue(), pl.getLife().getHealthPoints());
    }

    @Test
    public void useCorruptWithNormalHealth() {
        pl = new PlayerFactoryImpl().create();
        final PotionImpl potion = new PotionImpl(PotionType.CORRUPT_POTION_I);
        /*
         * Use a corrupt potion with normal health,
         * Except true and correct health update.
         */
        // player health points are initially 12
        final int hp = pl.getLife().getHealthPoints();
        assertTrue(potion.use(pl));
        System.out.println(potion.getHpValue());
        assertEquals(hp + potion.getHpValue(), pl.getLife().getHealthPoints());
    }

    @Test
    public void useCorruptBelowZero() {
        pl = new PlayerFactoryImpl().create();
        final PotionImpl potion = new PotionImpl(PotionType.CORRUPT_POTION_II);
        /*
         * Use corrupt potion when the potion value would
         * set the health below zero.
         * Expect true and correct update of health to 0.
         * corrupt potion II removes from 15-20, set health to 10.
         */
        pl.getLife().hurt(REMOVE_AMOUNT_40);
        assertTrue(potion.use(pl));
        assertEquals(0, pl.getLife().getHealthPoints());
    }
}
