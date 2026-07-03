package model.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.function.Supplier;

import org.junit.Test;

import model.data.GameData;
import model.data.GameDataImpl;
import model.entities.powerup.PowerUpType;

/**
 * Test for game data.
 */
public class GameDataTest {

    private static final String POWER_UP = "Power up counter error";
    private static final String DEAD_ENEMIES = "Dead enemies counter error";
    private static final String SHIELD = "Shield error";
    private static final String TIMER = "Timer counter error";
    private static final String SCORE = "Score error";

    private final GameData gd = new GameDataImpl();
    private static final double DELTA = 0.1;

    private boolean testCounter(final String errorMessage, final Runnable inc, final Supplier<Integer> get) {
        final int length = 10;
        for (int i = 1; i <= length; i++) {
            inc.run();
            assertEquals(errorMessage, (int) get.get(), i);
        }
        return true;
    }

    /**
     * Test lives.
     */
    @Test
    public final void testLife() {
        final double firstLife = 3.0, secondLife = 4.0, thirdLife = 5.0;
        this.gd.setLife(firstLife);
        assertEquals(this.gd.getLife(), firstLife, DELTA);
        assertEquals(POWER_UP, this.gd.getNumPowerUpTakenByType(PowerUpType.LIFE), 0);
        this.gd.setLife(secondLife);
        gd.increasePowerUpByType(PowerUpType.LIFE);
        assertEquals(this.gd.getLife(), secondLife, DELTA);
        assertEquals(POWER_UP, this.gd.getNumPowerUpTakenByType(PowerUpType.LIFE), 1);
        this.gd.setLife(thirdLife);
        assertEquals(this.gd.getLife(), thirdLife, DELTA);
        assertEquals(POWER_UP, this.gd.getNumPowerUpTakenByType(PowerUpType.LIFE), 1);
    }

    /**
     * Test scores.
     */
    @Test
    public final void testScore() {
        int score = 1000;
        final int addingScore = 500;
        this.gd.addScore(score);
        assertEquals(SCORE, this.gd.getScore(), score);
        score += addingScore;
        this.gd.addScore(addingScore);
        assertEquals(SCORE, this.gd.getScore(), score);
    }

    /**
     * Test dead enemies.
     */
    @Test
    public final void testDeadEemies() {
        assertTrue(DEAD_ENEMIES, testCounter(DEAD_ENEMIES, () -> this.gd.increaseNumDeadEemies(), () -> this.gd.getNumDeadEemies()));
    }

    /**
     * Test timer.
     */
    @Test
    public final void testTimer() {
        final int firstTime = 2500, secondTime = 10000, thirdTime = 120000;
        assertEquals(TIMER, this.gd.getTimer(), 0);
        this.gd.addTime(firstTime);
        assertEquals(TIMER, this.gd.getTimer(), firstTime);
        this.gd.addTime(secondTime);
        assertEquals(TIMER, this.gd.getTimer(), firstTime + secondTime);
        this.gd.addTime(thirdTime);
        assertEquals(TIMER, this.gd.getTimer(), firstTime + secondTime + thirdTime);
    }

    /**
     * Test power ups.
     */
    @Test
    public final void testPowerUp() {
        assertTrue(POWER_UP, testCounter(POWER_UP, () -> this.gd.increasePowerUpByType(PowerUpType.DAMAGE),
                () -> this.gd.getNumPowerUpTakenByType(PowerUpType.DAMAGE)));
        assertTrue(POWER_UP, testCounter(POWER_UP, () -> this.gd.increasePowerUpByType(PowerUpType.RATE_OF_FIRE),
                () -> this.gd.getNumPowerUpTakenByType(PowerUpType.RATE_OF_FIRE)));
        assertFalse(SHIELD, this.gd.isDefensePowerUpActive());
        this.gd.turnOnShield();
        assertTrue(SHIELD, this.gd.isDefensePowerUpActive());
        this.gd.turnOnShield();
        assertTrue(SHIELD, this.gd.isDefensePowerUpActive());
        this.gd.turnOffShield();
        assertFalse(SHIELD, this.gd.isDefensePowerUpActive());
    }

    private void testUnsupported(final Runnable inc) {
        try {
            inc.run();
            fail("UnsupportedOperationException not catched");
        } catch (UnsupportedOperationException exp) {
            System.out.print("");
        } catch (Exception e) {
            fail("UnsupportedOperationException not catched");
        }
    }

    /**
     * Test unmodifiable game data.
     */
    @Test
    public final void testUnmodifiable() {
        final GameData ugd = this.gd.unmodifiableGameData();
        testUnsupported(() -> ugd.setLife(1));
        testUnsupported(() -> ugd.addScore(1));
        testUnsupported(() -> ugd.increaseNumDeadEemies());
        testUnsupported(() -> ugd.addTime(1));
        testUnsupported(() -> ugd.increasePowerUpByType(PowerUpType.DAMAGE));
        testUnsupported(() -> ugd.increasePowerUpByType(PowerUpType.RATE_OF_FIRE));
        testUnsupported(() -> ugd.turnOnShield());
        testUnsupported(() -> ugd.turnOffShield());
        /* check if ugd is a copy of gd */
        gd.increasePowerUpByType(PowerUpType.DAMAGE);
        assertNotEquals(ugd.getNumPowerUpTakenByType(PowerUpType.DAMAGE),
                gd.getNumPowerUpTakenByType(PowerUpType.DAMAGE));
        gd.turnOnShield();
        assertNotEquals(ugd.isDefensePowerUpActive(), gd.isDefensePowerUpActive());
    }
}
