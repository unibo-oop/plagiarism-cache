package model.den;

/**
 *
 */
public class DenImpl implements Den {

    private final double center;
    private boolean isFree;

    /**
     * Constructor.
     * @param center is the center of the den.
     */
    public DenImpl(final int center) {
        this.center = center;
        this.isFree = true;
    }

    /**
     *
     */
    @Override
    public boolean isDenFree() {
        return this.isFree;
    }

    /**
     *
     */
    @Override
    public void occupy() {
        this.isFree = false;
    }

    /**
     *
     */
    @Override
    public double getCenter() {
        return this.center;
    }

}
