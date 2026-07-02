package model.enemy;

import model.entities.Entity;
import model.entities.Position;

/**
 * 
 * Interface of Abstract Enemy class.
 *
 */
public interface Enemy extends Entity {

    /**
     * Let the enemy come down when the stuntman rises.
     */
    void shiftDown();

    /**
     * 
     * Set the new enemy position.
     * 
     * @param position The new Enemy Position.
     * 
     */
    void setPosition(Position position);

    /**
     * @return True if the Enemy is in game, otherwise False.
     */
    boolean isInGame();

    /**
     * 
     * Set True if the Enemy is in game, otherwise false.
     * 
     * @param state The new State of Enemy.
     * 
     */
    void setInGame(boolean state);
}
