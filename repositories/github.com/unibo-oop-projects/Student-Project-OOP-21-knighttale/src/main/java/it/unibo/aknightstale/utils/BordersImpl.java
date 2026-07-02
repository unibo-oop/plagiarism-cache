package it.unibo.aknightstale.utils;

public class BordersImpl implements Borders {

    private final Point point;
    private final double width;
    private final double height;

    public BordersImpl(final double x, final double y, final double width, final double height) {
        super();
        this.point = new Point2D(x, y);
        this.width = width;
        this.height = height;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getX() {
        return this.point.getX();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getY() {
        return this.point.getY();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getWidth() {
        return this.width;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getHeight() {
        return this.height;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean intersects(final Borders b) {
        return intersects(b.getX(), b.getY(), b.getWidth(), b.getHeight());
    }

    private boolean intersects(final double x, final double y, final double w, final double h) {
        if (w < 0 || h < 0) {
            return false;
        }
        return x + w >= getX() && y + h >= getY() && x <= getX() + getWidth() && y <= getY() + getHeight();
    }

}
