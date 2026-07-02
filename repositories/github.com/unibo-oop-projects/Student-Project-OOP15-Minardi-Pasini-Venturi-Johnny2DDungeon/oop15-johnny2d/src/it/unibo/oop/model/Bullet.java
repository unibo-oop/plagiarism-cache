package it.unibo.oop.model;

import static it.unibo.oop.utilities.CharactersSettings.BULLET;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import it.unibo.oop.exceptions.CollisionHandlingException;
import it.unibo.oop.utilities.Position;
import it.unibo.oop.utilities.Vector2;

/**
 * Class representing the Bullet fired from an {@link Entity} that implements a
 * {@link Shooter}.
 */
public class Bullet extends MovableEntity implements Shot {

    private static final double BULLET_BASE = 500;
    private static final int BULLET_RANDOM = 500;

    private double remainingDistance = BULLET_BASE + new Random().nextInt(BULLET_RANDOM);

    /**
     * Constructor that takes the basic informations of the {@link Bullet}
     * @param Start X position
     * @param Start Y position
     * @param Inital movement vector
     */
    public Bullet(final double startingX, final double startingY, final Vector2 movementVector) {
        super(startingX, startingY, movementVector, BULLET.getSpeed());
        this.setMovement(movementVector);
    }

    /**
     * Constructor that takes the {@link Bullet} informations from the
     * {@link MainCharacter}
     * @param heroPosition the hero that shot the {@link Bullet}
     */
    public Bullet(final MainCharacter heroPosition) {
        this(heroPosition.getX(), heroPosition.getY(), heroPosition.getMovement());
        // Takes the hero position
        this.setMovement(heroPosition.getLastDirection().getVector2());
        // The movement vector is in the same Hero direction but in another
        // speed values
        this.getMovement().setLength(this.getVelocity().getMinVelocity());
    }

    /**
     * Function that checks if this {@link Entity} in the next position collides
     * with other ones. The {@link Bullet} kills the {@link Enemy} and smashes
     * in the {@link Wall}
     * 
     * @param newPosition The next position where the function has to check collisions
     */
    public void checkCollision(final Position newPosition) throws CollisionHandlingException {
        final Bullet tmpBullet = Factory.BulletFactory.createBullet(newPosition.getIntX(), newPosition.getIntY(),
                this.getMovement());
        // Counting how much walls it collides (Usually 1)
        if (!this.getEnvironment().getArena().isInside(tmpBullet)) {
            this.killEntity();
            throw new CollisionHandlingException("Next movement not inside the arena");
        }
        final long numWallCollisions = this.getEnvironment().getStableList().stream().filter(x -> x instanceof Wall)
                .filter(tmpBullet::intersecate).count();
        // Collects all the Enemies collided (usually 1)
        final List<AbstractEnemy> enemyCollisions = this.getEnvironment().getMovableList().stream()
                .filter(x -> x instanceof AbstractEnemy).filter(tmpBullet::intersecate).map(x -> (AbstractEnemy) x)
                .collect(Collectors.toList());
        // If collides a wall the bullet dies and gets removed
        if (numWallCollisions > 0) {
            this.killEntity();
            throw new CollisionHandlingException("Next movement collides a wall");
        }
        // If the bullet collides with an enemy both die
        if (!enemyCollisions.isEmpty()) {
            // Calculates the score obtained killing the monsters
            final int tmpScore = enemyCollisions.stream().map(x -> x.getScoreValue()).reduce((x, y) -> x + y).get();
            this.getEnvironment().getMainChar().get().getScore().increaseScore(tmpScore);

            // Removes the monsters from the envirnoment
            enemyCollisions.stream().forEach(x -> x.killEntity());
            this.killEntity();
            // Throws the exception avoiding the next movement
            throw new CollisionHandlingException("This bullet collided an enemy");
        }
    }

    /**
     * Accelerates the bullet and moves it to the next position every frame
     */
    public void update() {
        try {
            final double newLength = this.getVelocity().accelerate(this.getMovement().length());
            // Calculates the new movement vector
            final Vector2 newMovement = this.getMovement().setLength(newLength);
            // Check if there are collision in the new position
            this.checkCollision(this.getPosition().sumVector(newMovement));
            // moves if no exception
            this.setMovement(newMovement);
            this.move();
            this.remainingDistance -= newLength;
            if (this.getRemainingDistance() <= 0) {
                this.killEntity();
            }
        } catch (CollisionHandlingException e) {
            System.out.println("The bullet collided with something");
        }
    }

    /**
     * Gets the {@link Bullet} height
     * @return Entity height
     */
    protected int getEntityHeight() {
        return BULLET.getHeight();
    }

    /**
     * Gets the {@link Bullet} width
     * @return Entity width
     */
    protected int getEntityWidth() {
        return BULLET.getWidth();
    }

    /**
     * Gets the remaining distance of the {@link Bullet}
     * @return remaining distance
     */
    public double getRemainingDistance() {
        return this.remainingDistance;
    }
}
