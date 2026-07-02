package it.unibo.geometrybash.model.powerup;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.geometrybash.model.geometry.Vector2;

/**
 * test for {@link PowerUpFactory}.
 */
class PowerUpFactoryTest {

    @Test
    void testCreatePowerUp() {
        final Vector2 position = new Vector2(0, 0);
        assertTrue(PowerUpFactory.create(PowerUpType.COIN, position) instanceof Coin);
        assertTrue(PowerUpFactory.create(PowerUpType.SHIELD, position) instanceof ShieldPowerUp);
        assertTrue(PowerUpFactory.create(PowerUpType.SPEED_BOOST, position) instanceof SpeedBoostPowerUp);
    }
}
