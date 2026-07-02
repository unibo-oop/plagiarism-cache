package it.unibo.arkanoid.subject;

import it.unibo.arkanoid.event.CollisionInfo;
import it.unibo.arkanoid.model.Level;
import it.unibo.arkanoid.utility.Vector2D;

/**
 * Represent the object used by player to destroy {@link Brick}.
 * 
 */
public class Ball extends AbstractSubject {

    private static final double RADIUS = 1.9;
    private static final double OVER = -3;
    private static final double MAX_PADDLE_BOUNCE_ANGLE = Math.toRadians(70);

    /**
     * 
     * @param x
     *            coordinate
     * @param y
     *            coordinate
     * @param velocity
     *            ball velocity
     * @param level
     *            the level where the ball acts
     */
    public Ball(final double x, final double y, final Vector2D velocity, final Level level) {
        super(x, y, RADIUS * 2, RADIUS * 2, velocity, level);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void handleCollision(final CollisionInfo collision) {
        final Vector2D velocity = this.getVelocity();

        if (collision.getSideOfCollision().equals(Vector2D.UP)) {
            this.setVelocity(new Vector2D(velocity.getX(), -Math.abs(velocity.getY())));
        }

        if (collision.getSideOfCollision().equals(Vector2D.DOWN)) {
            if (collision.getSubject().getSubjectType() == SubjectType.PADDLE) {
                final Subject paddle = collision.getSubject();
                final double relativeX = (paddle.getPosition().getX() - this.getPosition().getX()) / (paddle.getWidth() / 2);
                this.setVelocity(Vector2D.UP.multiply(this.getVelocity().getLength())
                        .rotate(relativeX * MAX_PADDLE_BOUNCE_ANGLE));
            } else {
                this.setVelocity(new Vector2D(velocity.getX(), Math.abs(velocity.getY())));
            }
        }

        if (collision.getSideOfCollision().equals(Vector2D.RIGHT)) {
            this.setVelocity(new Vector2D(-Math.abs(velocity.getX()), velocity.getY()));
        }

        if (collision.getSideOfCollision().equals(Vector2D.LEFT)) {
            this.setVelocity(new Vector2D(Math.abs(velocity.getX()), velocity.getY()));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SubjectType getSubjectType() {
        return SubjectType.BALL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final double deltaTime) {
        super.update(deltaTime);
        if (this.getPosition().getY() < OVER) {
            this.removeObject();
        }
    }

}
