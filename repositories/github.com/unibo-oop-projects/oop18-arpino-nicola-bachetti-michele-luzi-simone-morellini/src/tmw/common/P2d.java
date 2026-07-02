package tmw.common;

/**
 * The P2d class defines a point representing a location in (x,y) coordinate
 * space.
 * 
 */
public class P2d implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private final double x, y;

    /**
     * @param x - the X coordinate of the location
     * @param y - the Y coordinate of the location
     */
    public P2d(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns a new P2d with coordinates given by the sum of the coordinates of
     * this point and the coordinates of the {@link V2d} passed.
     * 
     * @param v - the {@link V2d} which indicates the coordinates to sum to this
     *          point
     * @return a new {@link P2d} with coordinates given by the sum of the
     *         coordinates of this point.
     */
    public P2d sum(final V2d v) {
        return new P2d(x + v.getX(), y + v.getY());
    }

    /**
     * Returns a {@link V2d} representing the vector between this point and the one
     * passed.
     * 
     * @param p - the {@link P2d} representing the vector's tip to compute
     * 
     * @return the {@link V2d} representing the vector between this point and the
     *         one passed
     */
    public V2d sub(final P2d p) {
        return new V2d(x - p.x, y - p.y);
    }

    /**
     * Getters for the x coordinate of this point.
     * 
     * @return the x coordinate of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * Getters for the y coordinate of this point.
     * 
     * @return the y coordinate of this point
     */
    public double getY() {
        return this.y;
    }

    /**
     * Returns a {@link String} which describes this point.
     * 
     * @return the {@link String} that describes this point
     * 
     */
    public String toString() {
        return "P2d(" + x + "," + y + ")";
    }

    /**
     * Return the Hash Code of this P2d.
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
     * Check if this P2d and the one passed are the same point.
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
        final P2d other = (P2d) obj;
        return (!(this.getX() != other.getX() || this.getY() != other.getY()));
    }

}
