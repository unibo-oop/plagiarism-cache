package laterunner.model.vehicle;

import laterunner.core.Dimensions;
import laterunner.model.collisions.BorderBoundingBox;
import laterunner.physics.P2d;
import laterunner.physics.S2d;

/**
 * Template of a vehicle.
 */
public abstract class Vehicle {

    private static final double UPDATING_CONSTANT = 0.001;

    private P2d pos;
    private S2d spd;
    private Vehicles type;

    /**
     * Vehicles' main constructor.
     * 
     * @param position
     *          vechicle's initial position
     * @param speed
     *          vehicle's initial speed
     * @param veh
     *          vehicle's type
     */
    public Vehicle(final P2d position, final S2d speed, final Vehicles veh) {
        if ((position.getX() < BorderBoundingBox.getBorderBoundingBox().getUpperLeft().getX()
                    && position.getX() + Dimensions.getDimensions().getVehicleWidth(veh)
                    > BorderBoundingBox.getBorderBoundingBox().getBelowRight().getX())
                || (veh == Vehicles.USER_CAR && speed.getY() != 0)
                || (veh != Vehicles.USER_CAR && speed.getX() != 0)) {
            throw new IllegalArgumentException();
        } else {
            this.pos = position;
            this.spd = speed;
            this.type = veh;
        }
    }

    /**
     * Sets vehicle's position.
     * 
     * @param position
     *          vehicle's new position
     */
    public void setPos(final P2d position) {
        if (position.getX() < BorderBoundingBox.getBorderBoundingBox().getUpperLeft().getX()
                    && position.getX() + Dimensions.getDimensions().getVehicleWidth(this.getType())
                    > BorderBoundingBox.getBorderBoundingBox().getBelowRight().getX()) {
            throw new IllegalArgumentException();
        } else {
            this.pos = position;
        }
    }

    /**
     * Recalls the checking setter.
     * 
     * @param speed
     *          unchecked vehicle's new speed
     */
    public abstract void setSpd(final S2d speed);

    /**
     * Sets the vehicles's speed after checking it.
     * 
     * @param speed
     *          checked vehicle's new speed
     */
    protected void setCheckedSpeed(final S2d speed) {
        this.spd = speed;
    }

    /**
     * Calculates new vehicle's position depending on the speed.
     * 
     * @param elapsed
     *          time elapsed between two frames
     */
    public void updateState(final int elapsed) {
        this.pos = this.pos.sum(spd.mul(UPDATING_CONSTANT * elapsed));
    }

    /**
     * Returns vehicle's current position.
     * 
     * @return
     *          vehicle's current position
     */
    public P2d getCurrentPos() {
        return this.pos;
    }

    /**
     * Returns vehicle's current speed.
     * 
     * @return
     *          vehicle's current speed
     */
    public S2d getCurrentSpd() {
        return this.spd;
    }

    /**
     * Returns vehicle's type.
     * 
     * @return
     *          vehicle's type
     */
    public Vehicles getType() {
        return this.type;
    }
}
