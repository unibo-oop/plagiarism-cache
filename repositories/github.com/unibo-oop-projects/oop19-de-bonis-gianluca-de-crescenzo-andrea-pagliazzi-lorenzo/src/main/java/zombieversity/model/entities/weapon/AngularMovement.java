package zombieversity.model.entities.weapon;

import javafx.geometry.Point2D;

/**
 * Representation of an angular movement that moves on a circular path.
 */
public class AngularMovement implements Movement {

    private static final double STANDARD_VELOCITY = 20;

    private static final double DEG_TOTAL_ANGLE = 120;
    private static final double RAD_TOTAL_ANGLE = Math.toRadians(DEG_TOTAL_ANGLE);
    private final Point2D pivot;
    private Point2D position;
    private double angle;
    private double da;
    private final double angularLength;
    private double end;
    private final double height;
    private boolean isOver;
    private double frequency;

    /**
     * @param from
     *          The pivot around which the movement will trace its path.
     * @param towards
     *          The initial point towards which the movement begins.
     * @param height
     *          The radius of the circle traced by the motion.
     */
    public AngularMovement(final Point2D from, final Point2D towards, final double height) {
        this.pivot = from;
        this.height = height;
        this.angularLength = RAD_TOTAL_ANGLE;
        this.isOver = false;
        this.setFrequency(STANDARD_VELOCITY);
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
        final double distanceX = towards.getX() - (from.getX());
        final double distanceY = towards.getY() - (from.getY());
        this.angle = Math.atan2(distanceY, distanceX) - this.angularLength / 2;
        this.da = this.angularLength * this.frequency;
        this.end = this.angle + this.angularLength;
        this.position = this.pivot.add(new Point2D(height * Math.cos(angle), height * Math.sin(angle)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void update() {
        if (this.angle < this.end) {
            this.angle += da;
            this.position = this.pivot.add(new Point2D(height * Math.cos(angle), height * Math.sin(angle)));
        } else {
            this.isOver = true;
        }
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
        return this.isOver;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final double getVelocity() {
        return this.frequency;
    }

    private void setFrequency(final double steps) {
        this.frequency = 1 / steps;
    }

}
