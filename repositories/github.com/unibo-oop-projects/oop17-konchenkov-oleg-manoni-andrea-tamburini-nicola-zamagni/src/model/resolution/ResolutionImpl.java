package model.resolution;

/**
 *
 * @author Andrea Manoni
 *
 */
public final class ResolutionImpl implements Resolution {

    private final String name;
    private final double x;
    private final double y;

    /**
     *
     * @param name
     *            name
     * @param x
     *            x
     * @param y
     *            y
     */
    public ResolutionImpl(final String name, final double x, final double y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }
}
