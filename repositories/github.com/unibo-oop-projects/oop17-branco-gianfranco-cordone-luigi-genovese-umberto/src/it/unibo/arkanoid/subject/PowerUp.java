package it.unibo.arkanoid.subject;

import it.unibo.arkanoid.event.CollisionInfo;
import it.unibo.arkanoid.model.Level;
import it.unibo.arkanoid.utility.Vector2D;

/**
 * This class represent powerUp entity with bonus or malus effect.
 *
 */
public class PowerUp extends AbstractSubject {

    private final PowerUpType powerUpType;

    /**
     * 
     * Constructor for a Power Up entity. It extends {@link AbstractSubject}, and have {@link PowerUpType}.
     * 
     * @param x
     *            The X coordinate.
     * @param y
     *            The Y coordinate.
     * @param width
     *            The width in the game's space.
     * @param height
     *            The Height in the game's space.
     * @param velocity
     *            The speed of falling toward the paddle.
     * @param level
     *            The current level where the PowerUp lives.
     *
     * @param powerUpType
     *            The PowerUp type.
     */
    public PowerUp(final double x, final double y, final double width, final double height, final Vector2D velocity,
            final Level level, final PowerUpType powerUpType) {
        super(x, y, width, height, velocity, level);
        this.powerUpType = powerUpType;
    }

    /**
     * Getter for the PowerUpType.
     * 
     * @return The Type of this Power Up.
     */
    public PowerUpType getPowerUpType() {
        return this.powerUpType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void handleCollision(final CollisionInfo collision) {
        if (collision.getSubject().getSubjectType() == SubjectType.PADDLE) {
            this.powerUpType.action(this.getCurrentLevel());
            this.removeObject();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SubjectType getSubjectType() {
        return SubjectType.POWER_UP;
    }

}
