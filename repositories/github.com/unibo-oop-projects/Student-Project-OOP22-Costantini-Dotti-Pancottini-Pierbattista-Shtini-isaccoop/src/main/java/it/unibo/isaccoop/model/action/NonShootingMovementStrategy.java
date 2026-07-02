package it.unibo.isaccoop.model.action;

import it.unibo.isaccoop.model.common.Point2D;
import it.unibo.isaccoop.model.common.Vector2D;
import it.unibo.isaccoop.model.enemy.NonShootingEnemy;

/**
 * NonShootingMovementStrategy class which is an HitStrategy implementation.
 * */
public final class NonShootingMovementStrategy implements MovementStrategy {

    @Override
    public Point2D move(final Point2D enemyPosition, final Point2D playerPosition) {
        final Vector2D movementVector = playerPosition.sub(enemyPosition).getNormalized().mul(NonShootingEnemy.getSpeed());
        return enemyPosition.sum(movementVector);
    }
}
