package it.unibo.the100dayswar.commons.utilities.impl;

import java.util.Objects;

import it.unibo.the100dayswar.commons.utilities.api.Position;
/**
 * Class that model the concept of the postion.
 */
public class PositionImpl implements Position {
    private static final long serialVersionUID = 1L;

    private int x;
    private int y;

    /**
     * Construct from x,y coords.
     * @param x coord
     * @param y coord
     */
    public PositionImpl(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Construct from given position's coords.
     * @param position to copy from
     */
    public PositionImpl(final Position position) {
        this.x = position.getX();
        this.y = position.getY();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getX() {
        return x;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getY() {
        return y;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setX(final int x) {
        this.x = x;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setY(final int y) {
        this.y = y;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(final Position position) {
        setX(getX() + position.getX());
        setY(getY() + position.getY());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object o) {
        if (o instanceof PositionImpl) {
            final PositionImpl positionObject = (PositionImpl) o;
            return this.x == positionObject.x
                && this.y == positionObject.y;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "(" + this.getX() + ", " + this.getY() + ")";
    }

     /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    } 
}
