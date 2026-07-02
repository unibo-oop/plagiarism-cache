package it.unibo.isaccoop.model.action;

import java.util.concurrent.ThreadLocalRandom;

import it.unibo.isaccoop.model.common.Point2D;
import it.unibo.isaccoop.model.common.Vector2D;
import it.unibo.isaccoop.model.enemy.AbstractEnemy;

/**
 * ShootingMovementStrategy class which is a MovementStrategy implementation.
 * */
public final class ShootingMovementStrategy implements MovementStrategy {

    private static final long TIME_INTERVAL = 500;
    private long elapsedNewVector;
    private Vector2D movementVector;

    /**
     * ShootingMovementStrategy Constructor.
     * */
    public ShootingMovementStrategy() {
        this.elapsedNewVector = System.currentTimeMillis();
        this.movementVector = this.getRandomVector();
    }

    @Override
    public Point2D move(final Point2D enemyPosition, final Point2D playerPosition) {
        if (System.currentTimeMillis() - this.elapsedNewVector > TIME_INTERVAL) {
            this.elapsedNewVector = System.currentTimeMillis();
            this.movementVector = this.getRandomVector();
        }
        return enemyPosition.sum(this.movementVector.getNormalized().mul(AbstractEnemy.getSpeed()));
    }

    /**
     * Get random movement vector.
     *
     * @return random movement vector
     * */
    private Vector2D getRandomVector() {
        return new Vector2D(ThreadLocalRandom.current().nextDouble(-1, 1), ThreadLocalRandom.current().nextDouble(-1, 1));
    }
}
