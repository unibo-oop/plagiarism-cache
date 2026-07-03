package oop.lit.util;

/**
 * An implemented bidimentional vector.
 */
public class Vector2DImpl implements Vector2D {
    /**
     * 
     */
    private static final long serialVersionUID = 2393266850478847722L;
    private static final double MARGIN = 10.0;
    private static final double CLOSE_MARGIN = 5.0;
    private final double x;
    private final double y;

    /**
     * Default constructor that set x and y to 0.
     */
    public Vector2DImpl() {
        super();
        this.x = 0;
        this.y = 0;
    }

    /**
     * Constructor.
     * 
     * @param x
     *            coordinates.
     * @param y
     *            coordinates.
     */
    public Vector2DImpl(final double x, final double y) {
        super();
        this.x = x;
        this.y = y;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public Vector2D invert() {
        return new Vector2DImpl(-this.getX(), -this.getY());
    }

    @Override
    public Vector2D invertWithDifferentOrigin(final Vector2D origin) {
        return this.sub(origin).invert().add(origin);
    }

    @Override
    public Vector2D add(final Vector2D v) {
        return new Vector2DImpl(x + v.getX(), y + v.getY());
    }

    @Override
    public Vector2D sub(final Vector2D v) {
        return this.add(v.invert());
    }

    @Override
    public Vector2D scale(final double scalar) {
        if (scalar < 0) {
            throw new IllegalArgumentException();
        }
        return new Vector2DImpl(this.x * scalar, this.y * scalar);
    }

    @Override
    public double getAngle(final Vector2D origin, final Vector2D destination) {
        /*
         * The formula below is : angle = arcos( (a^2 + b^2 - c^2) / 2ab ).
         * Where angle is the angle in front of c and a, b, c are the sides of
         * the triangle.
         */
        final double angle = Math.acos((Math.pow(getDistance(origin), 2) + Math.pow(getDistance(destination), 2) - Math
                .pow(origin.getDistance(destination), 2)) / (2 * getDistance(origin) * getDistance(destination)));

        if (isPositiveAngle(origin, destination)) {
            return Math.toDegrees(angle);
        } else {
            return Math.toDegrees(-angle);
        }
    }

    @Override
    public double getDistance(final Vector2D a) {
        return Math.sqrt(Math.pow(a.getX() - this.getX(), 2) + Math.pow(a.getY() - this.getY(), 2));
    }

    @Override
    public Boolean isPositiveAngle(final Vector2D origin, final Vector2D destination) {
        /*
         * "this" is the center of the system, origin is the starting point,
         * destination the ending point. Considering the orientated rect "this"
         * -> origin, if destination is on its right then the angle is positive,
         * otherwise the angle is negative.
         */
        final Vector2D realOrigin = origin.sub(this);
        final Vector2D realDestination = destination.sub(this);
        final double a = (-realOrigin.getY()) / realOrigin.getX();

        if (realOrigin.getX() > 0 && -realDestination.getY() < a * realDestination.getX()) {
            return true;
        }

        if (realOrigin.getX() < 0 && -realDestination.getY() > a * realDestination.getX()) {
            return true;
        }

        return false;
    }

    @Override
    public double getPointRectDistance(final Vector2D r, final double theta) {
        Vector2D p = this.sub(r);
        p = new Vector2DImpl(p.getX(), -p.getY());
        return Math.abs(Math.tan(theta) * p.getX() - p.getY()) / Math.sqrt(Math.pow(Math.tan(theta), 2) + 1);
    }

    @Override
    public boolean isNearby(final Vector2D point) {
        return getDistance(point) < MARGIN;
    }

    @Override
    public boolean isVeryNearby(final Vector2D point) {
        return getDistance(point) < CLOSE_MARGIN;
    }

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

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Vector2DImpl)) {
            return false;
        }
        final Vector2DImpl other = (Vector2DImpl) obj;
        return (Double.doubleToLongBits(x) == Double.doubleToLongBits(other.x))
                && (Double.doubleToLongBits(y) == Double.doubleToLongBits(other.y));
    }

    @Override
    public String toString() {
        return "Vector2D [x=" + x + ", y=" + y + "]";
    }
}
