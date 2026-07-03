package model.entities.powerup;

import javafx.scene.shape.Shape;
import model.entities.AbstractEntity;
import model.entities.properties.Velocity;

/**
 * Implementation of power up, an entity of the game that is generate sometimes
 * after enemies' dead. If spaceship take a power up, it will be upgraded.
 */
public final class PowerUpImpl extends AbstractEntity implements PowerUp {

    private final PowerUpType type;

    /**
     * 
     * @param type
     *            power up's type
     * @param velocity
     *            initial velocity
     * @param shape
     *            power up's shape
     */
    public PowerUpImpl(final PowerUpType type, final Velocity velocity, final Shape shape) {
        super(shape, velocity);
        this.type = type;
    }

    @Override
    public PowerUpType getType() {
        return this.type;
    }
}
