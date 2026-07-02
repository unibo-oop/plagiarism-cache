package it.unibo.oop.model;

import it.unibo.oop.utilities.Position;

/**
 * Interface for an enemy .
 */
public interface Enemy {

    /**
     * Attach a new {@link MovementBehavior}
     * @param enemyBehavior the behavior to attach
     */
    void attachBehavior(MovementBehavior enemyBehavior);

    /**
     * Uses the attached {@link MovementBehavior}
     * 
     * @param targetPosition the reference position used by the behavior
     */
    void useBehavior(Position targetPosition);

    /**
     * Gets the score value of the {@link Enemy}
     * 
     * @return the score that the {@link Enemy} gives
     */
    int getScoreValue();

    /**
     * Gets the damage value of the {@link Enemy}
     * 
     * @return the damage of the monster
     */
    int getDamage();
}