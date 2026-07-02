package javawulf;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javawulf.model.Coordinate;
import javawulf.model.CoordinateImpl;
import javawulf.model.powerup.PowerUp;
import javawulf.model.powerup.PowerUpFactory;
import javawulf.model.powerup.PowerUpFactoryImpl;

/**
 * Test for creation of all powerUps types.
 */
final class PowerUpFactoryTest {

    private static final int X_COORDINATE_POWERUP = 10;
    private static final int Y_COORDINATE_POWERUP = 10;
    private PowerUpFactory factory;
    private Coordinate coordinatesPowerUp;

    @BeforeEach
    void setUpFactory() {
        factory = new PowerUpFactoryImpl();
        coordinatesPowerUp = new CoordinateImpl(X_COORDINATE_POWERUP, Y_COORDINATE_POWERUP);
    }

    @Test
    void testCreatePowerUpAttack() {
        // Create the powerUp and check if its not null
        final PowerUp powerUpAttack = factory.createPowerUpAttack(coordinatesPowerUp);
        assertNotNull(powerUpAttack);
        // Checks on the valuers of powerUp
        assertEquals(coordinatesPowerUp.getPosition(), powerUpAttack.getPosition().getPosition());
        assertEquals(PowerUpFactoryImpl.DURATION_ATTACK, powerUpAttack.getDuration());
        assertEquals(PowerUpFactoryImpl.POINTS_ATTACK, powerUpAttack.getPoints());
        assertEquals(PowerUpFactoryImpl.TYPE_ATTACK, powerUpAttack.getType());
    }

    @Test
    void testCreatePowerUpDoublePoints() {
        // Create the powerUp and check if its not null
        final PowerUp powerUpDoublePoints = factory.createPowerUpDoublePoints(coordinatesPowerUp);
        assertNotNull(powerUpDoublePoints);
        // Checks on the valuers of powerUp
        assertEquals(coordinatesPowerUp.getPosition(), powerUpDoublePoints.getPosition().getPosition());
        assertEquals(PowerUpFactoryImpl.DURATION_DOUBLEPOINTS, powerUpDoublePoints.getDuration());
        assertEquals(PowerUpFactoryImpl.POINTS_DOUBLEPOINTS, powerUpDoublePoints.getPoints());
        assertEquals(PowerUpFactoryImpl.TYPE_DOUBLEPOINTS, powerUpDoublePoints.getType());
    }

    @Test
    void testCreatePowerUpInvincibility() {
        // Create the powerUp and check if its not null
        final PowerUp powerUpIncincibility = factory.createPowerUpInvincibility(coordinatesPowerUp);
        assertNotNull(powerUpIncincibility);
        // Checks on the valuers of powerUp
        assertEquals(coordinatesPowerUp.getPosition(), powerUpIncincibility.getPosition().getPosition());
        assertEquals(PowerUpFactoryImpl.DURATION_INVINCIBILITY, powerUpIncincibility.getDuration());
        assertEquals(PowerUpFactoryImpl.POINTS_INVINCIBILITY, powerUpIncincibility.getPoints());
        assertEquals(PowerUpFactoryImpl.TYPE_INVINCIBILITY, powerUpIncincibility.getType());
    }

    @Test
    void testCreatePowerUpSpeed() {
        // Create the powerUp and check if its not null
        final PowerUp powerUpSpeed = factory.createPowerUpSpeed(coordinatesPowerUp);
        assertNotNull(powerUpSpeed);
        // Checks on the valuers of powerUp
        assertEquals(coordinatesPowerUp.getPosition(), powerUpSpeed.getPosition().getPosition());
        assertEquals(PowerUpFactoryImpl.DURATION_SPEED, powerUpSpeed.getDuration());
        assertEquals(PowerUpFactoryImpl.POINTS_SPEED, powerUpSpeed.getPoints());
        assertEquals(PowerUpFactoryImpl.TYPE_SPEED, powerUpSpeed.getType());
    }

}
