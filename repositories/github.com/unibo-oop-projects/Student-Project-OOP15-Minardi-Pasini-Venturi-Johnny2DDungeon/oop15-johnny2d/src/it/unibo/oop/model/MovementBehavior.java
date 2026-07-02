package it.unibo.oop.model;

import it.unibo.oop.utilities.Position;
import it.unibo.oop.utilities.Vector2;

/**
 * Interface for a common AI movement calculated with a specific algorithm.
 */
public interface MovementBehavior {

    /**
     * 
     * @param targetPosition
     *            the {@link Position} where the {@link MovementBehavior} should
     *            guide you
     * @return A {@link Vector2} that represents the direction to follow
     */
    Vector2 getNextMove(Position targetPosition);
}