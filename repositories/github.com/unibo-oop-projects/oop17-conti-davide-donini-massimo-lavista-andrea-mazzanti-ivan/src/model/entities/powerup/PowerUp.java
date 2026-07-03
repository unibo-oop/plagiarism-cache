package model.entities.powerup;

import model.entities.Entity;

/**
 * Representation of power up, an entity of the game that is generate sometimes
 * after enemies' dead. If spaceship take a power up, it will be upgraded.
 */
public interface PowerUp extends Entity {
    /**
     * 
     * @return the type of the power up
     */
    PowerUpType getType();

}
