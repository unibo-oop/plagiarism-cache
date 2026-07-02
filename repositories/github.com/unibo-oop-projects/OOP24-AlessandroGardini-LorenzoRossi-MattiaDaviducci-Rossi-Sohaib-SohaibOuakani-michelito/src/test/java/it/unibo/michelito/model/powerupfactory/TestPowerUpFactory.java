package it.unibo.michelito.model.powerupfactory;

import it.unibo.michelito.model.powerups.api.PowerUpFactory;
import it.unibo.michelito.model.powerups.api.PowerUp;
import it.unibo.michelito.model.powerups.api.PowerUpType;
import it.unibo.michelito.model.powerups.impl.PowerUpFactoryImpl;
import it.unibo.michelito.util.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;


final class TestPowerUpFactory {
    private PowerUpFactory factory;
    private final Position spawn = new Position(0, 0);
    @BeforeEach
    void setUp() {
        this.factory = new PowerUpFactoryImpl();
    }

    @Test
    void testPowerUpCreation() {
        assertInstanceOf(PowerUp.class, factory.createPowerUp(spawn, PowerUpType.SPEED_POWERUP));
        assertInstanceOf(PowerUp.class, factory.createPowerUp(spawn, PowerUpType.BOMB_TYPE_POWERUP));
        assertInstanceOf(PowerUp.class, factory.createPowerUp(spawn, PowerUpType.BOMB_LIMIT_POWERUP));
    }
}
