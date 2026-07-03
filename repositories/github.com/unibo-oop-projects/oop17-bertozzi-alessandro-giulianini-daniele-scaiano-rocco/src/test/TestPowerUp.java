package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.stream.IntStream;
import org.junit.Test;
import game.GameImpl;
import game.GameMode;
import game.ID;
import game.Player;
import game.buffs.HighGradeBuff;
import game.buffs.LowGradeBuff;
import game.buffs.PlayerPowerUp;
import game.buffs.PowerUpType;
import utilities.ImageLoader;
import utilities.Pair;

/**
 * Miscellaneous tests for ImageLoader and PowerUps.
 */
public class TestPowerUp {

    private static final int EXPECTED_X = 5;
    private static final int EXPECTED_Y = 9;
    private static final int INITIAL_HEALTH = 20;
    private static final int FINAL_HEALTH = 40;

    /**
     * 
     */
    @Test
    public void testImageManagement() {
        final ImageLoader loader = ImageLoader.getLoader();
        assertNotNull(loader);
        loader.findImages();
        assertFalse(loader.getEntityImages().isEmpty());
    }

    /**
     * 
     */
    @Test
    public void testPowerUpBasics() {
        final PlayerPowerUp pu = new PlayerPowerUp(ID.POWER_UP, new Pair<>(0, 0), 1, 1, PowerUpType.SHIELD, new HighGradeBuff());
        pu.update();
        pu.setVelocity(1, 1);
        pu.update();
        pu.setVelocity(1, 1);
        pu.update();
        assertEquals(pu.getPosition().getX().intValue(), 3);
        assertEquals(pu.getPosition().getY().intValue(), 3);
        pu.setVelocity(1, 3);
        pu.update();
        pu.setVelocity(1, 3);
        pu.update();
        assertEquals(pu.getPosition().getX().intValue(), EXPECTED_X);
        assertEquals(pu.getPosition().getY().intValue(), EXPECTED_Y);
        pu.collide(new PlayerPowerUp(ID.POWER_UP, new Pair<>(0, 0), 1, 1, PowerUpType.SHIELD, new HighGradeBuff()));
        assertFalse(pu.isActivated());
    }

    /**
     * 
     */
    @Test
    public void testShieldPowerUp() {
        final Player player = new Player(ID.PLAYER, new GameImpl(GameMode.SINGLEPLAYER));
        final PlayerPowerUp pu = new PlayerPowerUp(ID.POWER_UP, new Pair<>(0, 0), 1, 1, PowerUpType.SHIELD, new HighGradeBuff());
        pu.collide(player);
        assertTrue(player.getShield() > 0);
        assertTrue(pu.isActivated());
        assertNotNull(pu.getEntityToBuff());
        IntStream.range(0, PowerUpType.SHIELD.getLifetime() + 1).forEach(i -> pu.update());
        assertTrue(pu.isDead());
        assertEquals(player.getShield(), 0);
    }

    /**
     * 
     */
    @Test
    public void testHealthPowerUp() {
        final Player player = new Player(ID.PLAYER, new GameImpl(GameMode.SINGLEPLAYER));
        final PlayerPowerUp pu = new PlayerPowerUp(ID.POWER_UP, new Pair<>(0, 0), 1, 1, PowerUpType.HEALTH_RECOVERY, new LowGradeBuff());
        player.setHealth(INITIAL_HEALTH);
        assertSame(player.getHealth(), INITIAL_HEALTH);
        pu.collide(player);
        assertSame(player.getHealth(), FINAL_HEALTH);
        pu.update();
        assertTrue(pu.isDead());
    }

    /**
     * 
     */
    @Test
    public void testSpeedPowerUp() {
        final Player player = new Player(ID.PLAYER, new GameImpl(GameMode.SINGLEPLAYER));
        final PlayerPowerUp pu = new PlayerPowerUp(ID.POWER_UP, new Pair<>(0, 0), 1, 1, PowerUpType.SPEED_BOOST, new LowGradeBuff());
        player.setVelocity(2, 2);
        pu.collide(player);
        assertTrue(player.getVelocity().equals(new Pair<>(4, 4)));
        IntStream.range(0, PowerUpType.SPEED_BOOST.getLifetime() + 1).forEach(i -> pu.update());
        assertTrue(pu.isDead());
    }
}

