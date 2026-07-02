package it.unibo.oop.model;

import java.awt.Point;
import java.awt.Rectangle;

import it.unibo.oop.utilities.Position;
import it.unibo.oop.utilities.Vector2;

/**
 * {@link MovementBehavior} of a monster that if you go into his visibility
 * {@link Rectangle} starts following you
 */
public class InvisibleEnemyBehavior implements MovementBehavior {

    private final InvisibleMonster playerPosition;

    /**
     * Gets the {@link InvisibleMonster} to calculate the next move with and
     * creates the behavior
     * @param player the invisible monster associated to the behavior
     */
    public InvisibleEnemyBehavior(final InvisibleMonster player) {
        this.playerPosition = player;
    }

    /**
     * Returns a vector that indicates where the {@link Enemy} should go to
     * follow the {@link MainCharacter}.
     * @param targetPosition the position to reach
     * @return the movement necessary to reache the targetPosition
     */
    public Vector2 getNextMove(final Position targetPosition) {
        Position destination;
        if (playerPosition.getActionRadius().contains(new Point(targetPosition.getIntX(), targetPosition.getIntY()))) {
            destination = targetPosition;
            playerPosition.setVisible(true);
        } else {
            destination = new Position(playerPosition.getActionRadius().getCenterX(),
                    playerPosition.getActionRadius().getCenterY());
            playerPosition.setVisible(false);
        }
        final double distanceX = destination.getX() - playerPosition.getX();
        final double distanceY = destination.getY() - playerPosition.getY();
        return new Vector2(distanceX, distanceY).setLength(playerPosition.getVelocity().getMaxVelocity());
    }
}