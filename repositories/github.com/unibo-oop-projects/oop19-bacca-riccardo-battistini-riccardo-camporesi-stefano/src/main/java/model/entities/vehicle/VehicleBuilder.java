package model.entities.vehicle;

import static constraints.VehicleConstraints.Status;
import constraints.DirOfMovement;
import model.environment.Point;

/**
 *
 */
public class VehicleBuilder {

    private int velocity;
    private Point departure;
    private Point destination;
    private int areaOfControl;
    private DirOfMovement sense;
    private Status status;
    private int maxVel;

    /**
     * 
     * @param maxVel max velocity that a vehicle can reach
     * @return new Vehicle
     */
    public final VehicleBuilder setMaxVel(final int maxVel) {
        this.maxVel = maxVel;
        return this;
    }

    /**
     *
     * @param sense of the vehicle when it's created
     * @return new Vehicle
     */
    public final VehicleBuilder setSense(final DirOfMovement sense) {
        this.sense = sense;
        return this;
    }

    /**
     *
     * @param status of the vehicle when it's created
     * @return new Vehicle
     */
    public VehicleBuilder setStatus(final Status status) {
        this.status = status;
        return this;
    }

    /**
     *
     * @param x coordinate x of the vehicle
     * @param y coordinate y of the vehicle
     * @return the starting position of the vehicle
     */
    public VehicleBuilder setDeparture(final int x, final int y) {
        this.departure = Point.of(x, y);
        return this;
    }

    /**
     *
     * @param x coordinate x of the vehicle
     * @param y coordinate y of the vehicle
     * @return the ending position of the vehicle
     */
    public VehicleBuilder setDestination(final int x, final int y) {
        this.destination = Point.of(x, y);
        return this;
    }

    /**
     *
     * @param vel the vehicle velocity
     * @return this velocity
     */
    public VehicleBuilder setVelocity(final int vel) {
        this.velocity = vel;
        return this;
    }

    /**
     *
     * @param areaOfC represents sight of the driver
     * @return this areaOfControl
     */
    public VehicleBuilder setAreaOfControl(final int areaOfC) {
        this.areaOfControl = areaOfC;
        return this;
    }

    /**
     *
     * @return a Vehicle
     */
    public Vehicle build() {
        return new VehicleImpl(this.velocity, this.areaOfControl, this.departure, this.destination, this.sense,
                this.status, this.maxVel);
    }

}
