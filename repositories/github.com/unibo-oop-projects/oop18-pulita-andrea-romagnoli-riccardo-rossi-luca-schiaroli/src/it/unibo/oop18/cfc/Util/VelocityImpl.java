package it.unibo.oop18.cfc.Util;

import it.unibo.oop18.cfc.Physics.Direction;

/**
 * This class implements the {@link Velocity} interface.
 */
public class VelocityImpl implements Velocity {

    private double spaceX;
    private double spaceY;
    private Direction direction;

    /**
     * Creates a new {@link Velocity}.
     */
    public VelocityImpl() {
        this.spaceX = 0;
        this.spaceY = 0;
        this.direction = Direction.STOP;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getSpaceX() {
        return this.spaceX;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSpaceX(final double spaceX) {
        this.spaceX = spaceX;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getSpaceY() {
        return this.spaceY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSpaceY(final double spaceY) {
        this.spaceY = spaceY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Direction getDirection() {
        return this.direction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDirection(final Direction direction) {
        this.direction = direction;
    }

}
