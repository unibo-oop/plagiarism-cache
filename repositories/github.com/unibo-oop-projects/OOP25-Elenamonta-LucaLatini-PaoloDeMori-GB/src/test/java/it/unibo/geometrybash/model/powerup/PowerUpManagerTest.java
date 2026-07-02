package it.unibo.geometrybash.model.powerup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test for {@link PowerUpManager}.
 */
class PowerUpManagerTest {

    private PowerUpManager manager;

    @BeforeEach
    void setUp() {
        manager = new PowerUpManager();
    }

    @Test
    void testInitialState() {
        assertFalse(manager.isShielded());
        assertEquals(1.0f, manager.getSpeedMultiplier());
    }

    @Test
    void testShield() {
        manager.activateShield();
        assertTrue(manager.isShielded());
        manager.consumeShield();
        assertFalse(manager.isShielded());
    }

    @Test
    void testSpeedBoostExpiration() {
        final float multiplier = 2.0f;
        final float duration = 2.0f;
        manager.applySpeedModifier(multiplier, duration);
        assertEquals(multiplier, manager.getSpeedMultiplier());
        manager.update(1.0f);
        assertEquals(multiplier, manager.getSpeedMultiplier());
        manager.update(2.0f);
        assertEquals(1.0f, manager.getSpeedMultiplier());
    }

    @Test
    void testReset() {
        manager.activateShield();
        final float multiplier = 1.5f;
        manager.applySpeedModifier(multiplier, 2.0f);
        manager.reset();
        assertFalse(manager.isShielded());
        assertEquals(1.0f, manager.getSpeedMultiplier());
    }
}
