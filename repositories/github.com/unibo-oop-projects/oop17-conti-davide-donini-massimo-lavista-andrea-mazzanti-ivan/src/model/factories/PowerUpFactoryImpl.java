package model.factories;

import java.util.Random;

import javafx.scene.shape.Shape;
import javafx.scene.shape.Rectangle;
import model.entities.powerup.PowerUp;
import model.entities.powerup.PowerUpImpl;
import model.entities.powerup.PowerUpType;
import model.entities.properties.Position;
import model.entities.properties.Velocity;
import model.entities.properties.VelocityImpl;

/**
 * Implementation of PowerUpFactory.
 */
public class PowerUpFactoryImpl implements PowerUpFactory {

    private static final double POWER_UP_SIDE = 19.5;
    private static final Velocity POWER_UP_VELOCITY = new VelocityImpl(0.0, 250.0);

    @Override
    public final PowerUp createPowerUp(final PowerUpType type, final Position pos) {
        final Shape shape = new Rectangle(pos.getX(), pos.getY(), POWER_UP_SIDE, POWER_UP_SIDE);
        return new PowerUpImpl(type, POWER_UP_VELOCITY, shape);
    }

    @Override
    public final PowerUp createRandomPowerUp(final Position pos) {
        final int random = new Random().nextInt(PowerUpType.values().length);
        final PowerUpType randomType = PowerUpType.values()[random];
        return this.createPowerUp(randomType, pos);
    }

}
