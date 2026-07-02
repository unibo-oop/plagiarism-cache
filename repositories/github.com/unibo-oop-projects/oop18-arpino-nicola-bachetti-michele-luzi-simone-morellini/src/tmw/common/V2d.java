package tmw.common;

/**
 * This class describes a vector that represents the movement direction for an
 * entity.
 * 
 */
public class V2d implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private final double x, y;

    /**
     * 
     * @param x - the vector's tip x coordinate
     * @param y - the vector's tip y coordinate
     */
    public V2d(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Construct a V2d that link the two {@link P2d} passed as parameters.
     * 
     * @param from - the {@link P2d} that represents the start of the vector
     * @param to   - the {@link P2d} that represents the end of the vector
     */
    public V2d(final P2d from, final P2d to) {
        this.x = to.getX() - from.getX();
        this.y = to.getY() - from.getY();
    }

    /**
     * Returns a new V2d which is the sum of this V2d and the one passed as
     * parameter.
     * 
     * @param v - the V2d to sum to this V2d
     * @return a new V2d that is the sum of the one passed and this one
     */
    public V2d sum(final V2d v) {
        return new V2d(this.x + v.x, this.y + v.y);
    }

    /**
     * Returns the module of this vector in double precision.
     * 
     * @return the module of this vector
     */
    public double module() {
        return (double) Math.sqrt(x * x + y * y);
    }

    /**
     * Returns a new V2d which is the normalized for of this V2d.
     * 
     * @return this V2d normalized
     */
    public V2d getNormalized() {
        final double module = this.module();
        return new V2d(x / module, y / module);
    }

    /**
     * Multiply this vector for the parameter a returns the results as a new V2d.
     * 
     * @param fact - the factor used for the multiplication
     * @return a V2d which is this V2d multiplied for the parameter
     */
    public V2d mul(final double fact) {
        return new V2d(x * fact, y * fact);
    }

    /**
     * Getters for the x tip's coordinate.
     * 
     * @return the x tip's coordinate
     */
    public double getX() {
        return this.x;
    }

    /**
     * Getters for the y tip's coordinate.
     * 
     * @return the y tip's coordinate
     */
    public double getY() {
        return this.y;
    }

    /**
     * Returns a {@link String} which describes this vector.
     * 
     * @return the {@link String} that describes this vector
     * 
     */
    public String toString() {
        return "V2d(" + x + "," + y + ")";
    }

    /**
     * Return the Hash Code of this V2d.
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
     * Check if this V2d and the one passed are the same vector.
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
        final V2d other = (V2d) obj;
        return (!(this.getX() != other.getX() || this.getY() != other.getY()));
    }
}
