package model.entity;

import utilities.math.Point2D;
import utilities.math.Vector2D;
import utilities.math.Vector2DImpl;

/**
 * A MovingEntity implementation.
 */
public class MovingEntityImpl extends RotatingEntityImpl implements MovingEntity {

    private static final Vector2D DEFAULT_SPEED = new Vector2DImpl(0, 0);

    private Vector2D speed;

    public MovingEntityImpl() {
        super();
        this.speed = DEFAULT_SPEED;
    }
    public MovingEntityImpl(final Point2D position, final double radius, final double radiantAngle) {
        this(position, radius, radiantAngle, DEFAULT_SPEED);
    }
    public MovingEntityImpl(final Point2D position, final double radius, final double radiantAngle, final Vector2D speed) {
        super(position, radius, radiantAngle);
        this.speed = speed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2D getSpeed() {
        return speed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void move() {
        this.resetPosition(this.getPosition().translate(this.speed.components()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetSpeed(final Vector2D newSpeed) {
        this.speed = newSpeed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accelerate(final Vector2D acceleration) {
        this.speed = speed.add(acceleration);
    }

    /*EQUALS & TOSTRING------------------------------------------------*/
    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((speed == null) ? 0 : speed.hashCode());
        return result;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MovingEntityImpl other = (MovingEntityImpl) obj;
        if (speed == null) {
            if (other.speed != null) {
                return false;
            }
        } else if (!speed.equals(other.speed)) {
            return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "MovingEntityImpl [ID=" + this.getID() + ", position=" + getPosition()
               + ", radius=" + this.getRadialHitbox() + ", radiantAngle=" + getRotation()
               + ", speed=" + speed + "]";
    }
    /*-----------------------------------------------------------------*/

}
