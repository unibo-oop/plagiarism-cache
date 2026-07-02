package it.unibo.isaccoop.model.action;

import it.unibo.isaccoop.model.common.Point2D;

/**
 * MovementStrategy interface.
 * */
public interface MovementStrategy {

    /**
     * Get new position by movement strategy.
     * @param enemyPosition the position of the enemy
     * @param playerPosition the position of the player
     * @return the new position to move
     * */
    Point2D move(Point2D enemyPosition, Point2D playerPosition);
}
