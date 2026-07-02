package it.unibo.oop.model;

import it.unibo.oop.utilities.Position;
import it.unibo.oop.utilities.Vector2;

/**
 * {@link Enemy} behavior that follows the {@link MainCharacter}.
 */
public class BasicEnemyBehavior implements MovementBehavior {

    private final BasicMonster playerPosition;

    /**
     * The constructor that gets the {@link BasicMonster} to move
     * 
     * @param player
     */
    public BasicEnemyBehavior(final BasicMonster player) {
        this.playerPosition = player;
    }

    /**
     * Returns a {@link Vector2} that indicates where the {@link Enemy} should
     * go to follow the {@link MainCharacter}.
     * @return The next movement vector
     */
    public Vector2 getNextMove(final Position targetPosition) {
        final double distanceX = targetPosition.getX() - playerPosition.getX();
        final double distanceY = targetPosition.getY() - playerPosition.getY();
        return new Vector2(distanceX, distanceY).setLength(playerPosition.getVelocity().getMaxVelocity());
    }
}
