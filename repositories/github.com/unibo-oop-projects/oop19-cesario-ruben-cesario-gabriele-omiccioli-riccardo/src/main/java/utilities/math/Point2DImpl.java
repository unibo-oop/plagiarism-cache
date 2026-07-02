package utilities.math;

/**
 * A Point2D implementation.
 * @see Point2D
 */
public class Point2DImpl implements Point2D {

    private double x;
    private double y;

    public Point2DImpl() {
        this.x = 0;
        this.y = 0;
    }

    public Point2DImpl(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public Point2DImpl(final Point2D point) {
        this.x = point.getX();
        this.y = point.getY();
    }

    /**
     * hashCode method override.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /**
     * equals method override.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Point2DImpl other = (Point2DImpl) obj;
        if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x)) {
            return false;
        }
        return Double.doubleToLongBits(y) == Double.doubleToLongBits(other.y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getX() {
        return this.x;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getY() {
        return this.y;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setX(final double x) {
        this.x = x;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setY(final double y) {
        this.y = y;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Point2D translate(final Point2D translation) {
        return new Point2DImpl(this.getX() + translation.getX(), this.getY() + translation.getY());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double distance(final Point2D point) {
        return Math.sqrt(Math.pow(point.getX() - this.x, 2) + Math.pow(point.getY() - this.y, 2));
    }

    /**
     * Override of default toString.
     */
    @Override
    public String toString() {
        return "[" + this.x + " | " + this.y + "]";
    }

}
