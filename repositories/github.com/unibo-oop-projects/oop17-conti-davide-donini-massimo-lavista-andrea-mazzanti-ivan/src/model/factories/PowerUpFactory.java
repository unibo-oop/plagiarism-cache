package model.factories;

import model.entities.powerup.PowerUp;
import model.entities.powerup.PowerUpType;
import model.entities.properties.Position;

/**
 * The factory that creates power up.
 */
public interface PowerUpFactory {

    /**
     * @param type
     *            type of the power up
     * @param position
     *            position to assign to the power up
     * @return a power up which type is random
     */
    PowerUp createPowerUp(PowerUpType type, Position position);

    /**
     * 
     * @param position
     *            position to assign to the power up
     * @return a power up which type is random
     */
    PowerUp createRandomPowerUp(Position position);
}
