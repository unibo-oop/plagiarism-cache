package model.entities;

import java.util.Optional;

import model.entities.powerup.PowerUpType;

/**
 * 
 * Interface that represents an hostile character.
 *
 */

public interface Enemy extends Character {

    /**
     * @return the power up that may spawn after the enemy death.
     */
    Optional<PowerUpType> getPowerUp();

    /**
     * 
     * @return the score got after killing the enemy.
     */
    int getScore();
}
