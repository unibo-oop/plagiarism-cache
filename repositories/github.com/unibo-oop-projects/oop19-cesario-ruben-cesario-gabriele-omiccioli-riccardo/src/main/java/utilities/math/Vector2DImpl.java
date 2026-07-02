package utilities.math;

/**
 * A Vector2D implementation.
 * @see Vector2D
 */
public class Vector2DImpl implements Vector2D {

    private double xComponent;
    private double yComponent;

    public Vector2DImpl(final double module, final double angle) {
        this.xComponent = module * Math.cos(angle);
        this.yComponent = module * Math.sin(angle);
    }

    public Vector2DImpl(final Point2D pointA, final Point2D pointB) {
        this.xComponent = pointB.getX() - pointA.getX();
        this.yComponent = pointB.getY() - pointA.getY();
    }

    public Vector2DImpl(final Point2D components) {
        this.xComponent = components.getX();
        this.yComponent = components.getY();
    }

    public Vector2DImpl() {
        this(new Point2DImpl(0, 0));
    }

    /**
     * hashCode method override.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(xComponent);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(yComponent);
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
        final Vector2DImpl other = (Vector2DImpl) obj;
        if (Double.doubleToLongBits(xComponent) != Double.doubleToLongBits(other.xComponent)) {
            return false;
        }
        return Double.doubleToLongBits(yComponent) == Double.doubleToLongBits(other.yComponent);
    }

    /**
     * @return The X component of this vector
     */
    public double getXComponent() {
        return this.xComponent;
    }

    /** 
     * @return The Y component of this vector
     */
    public double getYComponent() {
        return this.yComponent;
    }

    /**
     * Sets the X component.
     * @param x X component
     */
    public void setXComponent(final double x) {
        this.xComponent = x;
    } 

    /** 
     * Sets the Y component.
     * @param y Y component
     */
    public void setYComponent(final double y) {
        this.yComponent = y;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Point2D components() {
        return new Point2DImpl(this.xComponent, this.yComponent);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double module() {
        return Math.sqrt(Math.pow(xComponent, 2) + Math.pow(yComponent, 2));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2D opposite() {
        return new Vector2DImpl(new Point2DImpl(-this.xComponent, -this.yComponent));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2D add(final Vector2D vector) {
        return new Vector2DImpl(this.components().translate(vector.components()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2D multiplyByScalar(final double scalar) {
        return new Vector2DImpl(new Point2DImpl(this.xComponent * scalar, this.yComponent * scalar));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double multiply(final Vector2D vector) {
        return this.xComponent * vector.components().getX() + this.yComponent * vector.components().getY();
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public void distance(final Point2D pointA, final Point2D pointB) {
        this.xComponent = pointB.getX() - pointA.getX();
        this.yComponent = pointB.getY() - pointA.getY();
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public double angle(final Vector2D vector) {
        return Math.acos(multiply(vector) / (this.module() * vector.module()));
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public final double angleOfThisVector() {
        // Special cases when at least one component is 0
        if (this.xComponent == 0) {
            return this.yComponent > 0 ? 0 
                    : this.yComponent == 0 ? 0 
                    : Math.PI;
        } else if (this.yComponent == 0) {
            return this.xComponent > 0 ? Math.PI / 2 
                    : Math.PI * 3 / 2;
        }
        // When both components are not 0
        double res = Math.atan(this.yComponent / this.xComponent);
        if (this.xComponent < 0) {
            res = res + Math.PI;
        } else {
            res = res + Math.PI * 2;
        }
        return res % (Math.PI * 2);
    }

    /**
     * Override of default toString.
     */
    @Override
    public String toString() {
        return "[" + this.xComponent + " | " + this.yComponent + "]";
    }

}
