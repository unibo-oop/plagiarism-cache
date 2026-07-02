package zombieversity.model.entities.zombie;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import zombieversity.model.collisions.CollisionsUtils;
import zombieversity.model.entities.Player;

/**
 * Simple AI for zombies, which makes them move straight to the player.
 * Implementation of {@link ZombieAI}.
 *
 */
public class ZombieAIImpl implements ZombieAI {

    /**
     * Angle to check at hard left/right.
     */
    private static final int HARD_SIDE_ANGLE = 90;

    /**
     * Angle to check at soft left/right.
     */
    private static final int SOFT_SIDE_ANGLE = 30;

    /**
     * Angle to check back.
     */
    private static final int BACK_ANGLE = 180;

    /**
     * Max distance to check collision.
     */
    private static final int DISTANCE = 5;

    private Set<BoundingBox> obstacles;

    /**
     * {@inheritDoc}
     */
    @Override
    public final void computeNextMovement(final Set<Zombie> zombies, final Player player) {
        zombies.stream().forEach(z -> {
            this.computePositionWithCollisions(z, zombies, player);
            this.checkCollisionWithPlayer(z, player);
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setObstacles(final Set<BoundingBox> obstacles) {
        this.obstacles = obstacles;

    }

    /**
     * Computes next position.
     * 
     * @param position starting position;
     * @param angle    angle;
     * @param distance distance to travel.
     * @return a Point2D representing next position.
     */
    private Point2D computeNextPosition(final Point2D position, final double angle, final double distance) {
        final double nextPosX = position.getX() + (Math.cos(Math.toRadians(angle)) * distance);
        final double nextPosY = position.getY() + (Math.sin(Math.toRadians(angle)) * distance);
        return new Point2D(nextPosX, nextPosY);
    }

    /**
     * Computes next position of given zombie, after checking collisions.
     * 
     * @param zombie  to move.
     * @param zombies horde.
     * @param player
     */
    private void computePositionWithCollisions(final Zombie zombie, final Set<Zombie> zombies, final Player player) {
        double currentAngle = zombie.getDirection();
        final Set<BoundingBox> entities = zombies.stream().filter(z -> !z.equals(zombie)).map(z -> z.getBBox())
                .collect(Collectors.toSet());
        entities.addAll(this.obstacles);

        final Set<Directions> collisions = this.checkCollisions(zombie, entities, player);

        if (!collisions.contains(Directions.TO_PLAYER)) {
            currentAngle = getAngleToPlayer(zombie, player);
        } else {
            if (collisions.contains(Directions.FRONT)) {
                if (!collisions.contains(Directions.LEFT)) {
                    currentAngle -= HARD_SIDE_ANGLE;
                } else if (!collisions.contains(Directions.RIGHT)) {
                    currentAngle += HARD_SIDE_ANGLE;
                } else if (!collisions.contains(Directions.BACK)) {
                    currentAngle += BACK_ANGLE;
                }
            }
        }
        zombie.setDirection(currentAngle);
        zombie.setPosition(this.computeNextPosition(zombie.getPosition(), currentAngle, zombie.getVelocity().getX()));
    }

    /**
     * Checks if next position is occupied by an other entity.
     * 
     * @param zombie to move
     * @param entity to check.
     * @param angle  to check.
     * @return true if the position is occupied, false otherwise.
     */
    private boolean isOccupied(final Zombie zombie, final BoundingBox entity, final double angle) {
        final Point2D nextPos = computeNextPosition(zombie.getPosition(), angle, DISTANCE);
        return CollisionsUtils.isColliding(
                new BoundingBox(nextPos.getX(), nextPos.getY(), zombie.getWidth(), zombie.getHeight()), entity);
    }

    /**
     * Computes collisions in all directions.
     * 
     * @param zombie   to move.
     * @param entities Set of entities to check collisions with.
     * @param player
     * @return Set of collisions directions.
     */
    private Set<Directions> checkCollisions(final Zombie zombie, final Set<BoundingBox> entities, final Player player) {
        final Set<Directions> directions = new HashSet<>();
        final double currentAngle = zombie.getDirection();
        entities.stream().forEach(e -> {
            if (this.isOccupied(zombie, e, currentAngle)) {
                directions.add(Directions.FRONT);
            }
            if (this.isOccupied(zombie, e, currentAngle - HARD_SIDE_ANGLE)
                    || this.isOccupied(zombie, e, currentAngle - SOFT_SIDE_ANGLE)) {
                directions.add(Directions.LEFT);
            }
            if (this.isOccupied(zombie, e, currentAngle + HARD_SIDE_ANGLE)
                    || this.isOccupied(zombie, e, currentAngle + SOFT_SIDE_ANGLE)) {
                directions.add(Directions.RIGHT);
            }
            if (this.isOccupied(zombie, e, currentAngle + BACK_ANGLE)) {
                directions.add(Directions.BACK);
            }
            if (this.isOccupied(zombie, e, ZombieAIImpl.getAngleToPlayer(zombie, player))) {
                directions.add(Directions.TO_PLAYER);
            }
        });
        return directions;
    }

    /**
     * Checks if zombie is colliding with player and in case damages it.
     * @param zombie to check.
     * @param player
     */
    private void checkCollisionWithPlayer(final Zombie zombie, final Player player) {
        if (CollisionsUtils.isColliding(zombie.getBBox(), player.getBBox())) {
            player.hitPlayer(zombie.getDamage());
        }
    }

    /**
     * 
     * @param zombie to move.
     * @param player
     * @return angle (in degrees) formed by zombie position, (0,0) and player.
     */
    public static final double getAngleToPlayer(final Zombie zombie, final Player player) {
        final double distanceX = player.getPosition().getX() - (zombie.getPosition().getX());
        final double distanceY = player.getPosition().getY() - (zombie.getPosition().getY());
        return Math.toDegrees(Math.atan2(distanceY, distanceX));
    }

    /**
     * Enum representing the four directions, plus direction pointing to player.
     *
     */
    private enum Directions {
        FRONT, LEFT, RIGHT, BACK, TO_PLAYER;
    }
}
