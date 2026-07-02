package model.environment;

/**
 * A point representing a position in {@code (x,y)} coordinate space, specified
 * in integer precision.
 */
public final class Point implements PointInterface {

    private final int x;
    private final int y;

    private Point(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns an instance of Point2D.
     *
     * @param x the X coordinate
     * @param y the Y coordinate
     * @return sad
     */
    public static Point of(final int x, final int y) {
        return new Point(x, y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getX() {
        return this.x;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Point translateX(final int x) {
        return Point.of(this.x + x, this.y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getY() {
        return this.y;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Point translateY(final int y) {
        return Point.of(this.x, this.y + y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Point getPosition() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Point translatePosition(final int x, final int y) {
        return Point.of(this.x + x, this.y + y);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.x;
        result = prime * result + this.y;
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final Point other = (Point) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Point [x=" + this.x + ", y=" + this.y + "]";
    }

}
