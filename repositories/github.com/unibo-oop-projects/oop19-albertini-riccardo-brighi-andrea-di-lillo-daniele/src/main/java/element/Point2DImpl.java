package element;

public class Point2DImpl implements Point2D {

    private final double x;
    private final double y;

    public Point2DImpl(final double x, final double y) {
        final int precision = 6;
        this.x = Elements.round(x, precision);
        this.y = Elements.round(y, precision);
    }

    @Override
    public double getX() {
        return this.x;
    }

    @Override
    public double getY() {
        return this.y;
    }

    @Override
    public Point2D sum(final Vector2D v) {
        return new Point2DImpl(this.getX() + v.getXComponent(), this.getY() + v.getYComponent());
    }

    @Override
    public Vector2D subtraction(final Point2D p) {
        return new Vector2DImpl(this.getX() - p.getX(), this.getY() - p.getY());
    }

    @Override
    public double distance(final Point2D p) {
        return this.subtraction(p).getModulus();
    }

    @Override
    public String toString() {
        return "Point2D ( " + this.x + ", " + this.y + " )";
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Point2DImpl)) {
            return false;
        }

        final Point2DImpl point2D = (Point2DImpl) o;

        if (Double.compare(point2D.getX(), this.getX()) != 0) {
            return false;
        }
        return Double.compare(point2D.getY(), this.getY()) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(this.getX());
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(this.getY());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
