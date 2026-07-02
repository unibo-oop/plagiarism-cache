package it.unibo.modularcheckers.util;

import it.unibo.modularcheckers.model.Coordinate;

/**
 * Create subsequent coordinates by using the indication in the method changeValues.
 */
public abstract class AbstractOrderedCoordinateFactory implements CoordinateFactory {

    // Values of the actual coordinate.
    private int xValue;
    private int yValue;

    /**
     * Constructor to initialize the factory.
     * @param xValue the starting value of the X parameter.
     * @param yValue the starting value of the Y parameter.
     */
    public AbstractOrderedCoordinateFactory(final int xValue, final int yValue) {
        this.xValue = xValue;
        this.yValue = yValue;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract boolean hasNext();

    /**
     * @return the Coordinate created.
     */
    @Override
    public Coordinate next() {
        final Coordinate coord = new Coordinate(this.xValue, this.yValue);
        this.changeValues();
        return coord;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void reset();

    /**
     * Change the values of xValue and yValue, depending on the method adopted. 
     */
    protected abstract void changeValues();

    /**
     * @return the xValue
     */

    // GETTERS AND SETTERS

    protected int getxValue() {
        return xValue;
    }

    /**
     * @return the yValue
     */
    protected int getyValue() {
        return yValue;
    }

    /**
     * @param xValue the xValue to set
     */
    protected void setxValue(final int xValue) {
        this.xValue = xValue;
    }

    /**
     * @param yValue the yValue to set
     */
    protected void setyValue(final int yValue) {
        this.yValue = yValue;
    }

}
