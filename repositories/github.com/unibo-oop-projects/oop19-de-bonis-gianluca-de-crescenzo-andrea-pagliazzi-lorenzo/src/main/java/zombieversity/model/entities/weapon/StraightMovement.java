package zombieversity.model.entities.weapon;

import javafx.geometry.Point2D;

/**
 * Representation of a movement along an infinite line.
 */
public class StraightMovement implements Movement {

    private static final double STANDARD_VELOCITY = 5;

    private double velocity;
    private double angle;
    private Point2D position;
    private Point2D positionVariation;

    /**
     * @param from
     *          The starting point in which the movement will begin.
     * @param towards
     *          The point towards the movement will go.
     */
    public StraightMovement(final Point2D from, final Point2D towards) {
        this.position = from;
        this.setVelocity(STANDARD_VELOCITY);
        this.calculateStep(from, towards);
    }

    /**
     * Used to calculate the variables that will allow to update the path.
     * @param from
     *          Initial point.
     * @param towards
     *          Point that defines the starting direction.
     */
    private void calculateStep(final Point2D from, final Point2D towards) {
        final double xStep;
        final double yStep;
        final double distanceX = towards.getX() - (from.getX());
        final double distanceY = towards.getY() - (from.getY());
        this.angle = Math.atan2(distanceY, distanceX);
        xStep = Math.cos(this.angle) * this.velocity;
        yStep = Math.sin(this.angle) * this.velocity;
        this.positionVariation = new Point2D(xStep, yStep);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void update() {
        this.position = this.position.add(this.positionVariation);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Point2D getActualPosition() {
        return this.position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final double getAngle() {
        return this.angle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean hasEnded() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final double getVelocity() {
        return this.velocity;
    }

    public final void setVelocity(final double velocityFactor) {
        this.velocity = velocityFactor;
    }

}
