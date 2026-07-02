package model.entities.vehicle;

import constraints.DirOfMovement;
import model.environment.Point;
import static constraints.VehicleConstraints.Status;

/**
 *
 */
public class VehicleImpl implements Vehicle {

    private int velocity;
    private final int areaOfControl;
    private Point position;
    private final Point departure;
    private final Point destination;
    private final DirOfMovement sense;
    private Status status;
    private int maxVel;

    /**
     * Constructor to create a {@link Vehicle}.
     *
     * @param velocity      -the vehicle velocity.
     * @param acceleration  -the vehicle acceleration.
     * @param areaOfControl - the sight of the driver
     */
    // package-private visibility
    VehicleImpl(final int vel, final int areaOfC, final Point start, final Point end, final DirOfMovement sense,
            final Status status, final int maxVel) {
        this.velocity = vel;
        this.areaOfControl = areaOfC;
        this.departure = start;
        this.position = start;
        this.destination = end;
        this.sense = sense;
        this.status = status;
        this.maxVel = maxVel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getVelocity() {

        return this.velocity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getAreaOfControl() {
        return this.areaOfControl;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Point getPosition() {
        return this.position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPosition(final Point pos) {
        this.position = pos;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public Point getDeparture() {
        return this.departure;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public Point getDestination() {
        return this.destination;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setVelocity(final int velocity) {
        this.velocity = velocity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DirOfMovement getSense() {
        return this.sense;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Status getStatus() {
        return this.status;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setStatus(final Status status) {
        this.status = status;
    }

    /**
     *
     */
    @Override
    public String toString() {
        return "vel = " + this.getVelocity() + "     pos = " + this.getPosition() + "    senso = " + this.getSense();

    }

    /**
     * @return the maxVel
     */
    public int getMaxVel() {
        return maxVel;
    }

    /**
     * @param maxVel the maxVel to set
     */
    public void setMaxVel(final int maxVel) {
        this.maxVel = maxVel;
    }

}
