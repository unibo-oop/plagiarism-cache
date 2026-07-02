package it.unibo.modularcheckers.util;

/**
 * Factory that generates different coordinates every time the method next() is called.
 * The sequence is 0,0 -> 1,0 -> 1,1 -> 2,1 -> 2,2 -> ...
 * 
 */
public class InfiniteXCoordinateFactory extends AbstractOrderedCoordinateFactory {

    /**
     * Unique Constructor. Starting from 0.
     */
    public InfiniteXCoordinateFactory() {
        super(0, 0);
    }

    /**
     * Always true.
     */
    @Override
    public boolean hasNext() {
        return true;
    }

    /**
     * {@inheritDoc}
     * Starting from 0,0.
     */
    @Override
    public void reset() {
        setxValue(0);
        setyValue(0);
    }

    /**
     * Adds to 1 if x is <= y, else adds 1 to y.
     */
    @Override
    protected void changeValues() {
        if (getxValue() <= getyValue()) {
            this.setxValue(getxValue() + 1);
        } else {
            this.setyValue(getyValue() + 1);
        }
    }

}
