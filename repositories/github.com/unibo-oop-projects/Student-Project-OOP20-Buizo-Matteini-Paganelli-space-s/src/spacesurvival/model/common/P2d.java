package spacesurvival.model.common;

/**
 * 
 * 2-dimensional point.
 * objects are completely state-less
 *
 */
public class P2d implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * x and y represent the x and y of the vector respectively.
     */
    private double x, y;

    /**
     * Empty constructor, initialize x and y fields equals to zero.
     */
    public P2d() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * P2d constructor, initialize x and y fields equals to the passed x and y.
     *
     * @param x coordinate that will be set
     * @param y coordinate that will be set
     */
    public P2d(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Return the x coordinate of the point.
     * 
     * @return the x coordinate of the point
     */
    public double getX() {
        return this.x;
    }

    /**
     * Return the y coordinate of the point.
     * 
     * @return the y coordinate of the point
     */
    public double getY() {
        return this.y;
    }

    /**
     * Set the x coordinate of the point.
     * 
     * @param x coordinate that will be sette
     */
    public void setX(final double x) {
        this.x = x;
    }

    /**
     * Set the y coordinate of the point.
     * 
     * @param y coordinate that will be setted
     */
    public void setY(final double y) {
        this.y = y;
    }

    /**
     * Return a new P2d summing the passed V2d.
     * 
     * @param v V2d vector to add
     * @return a new P2d vector which is the sum of the current and the passed
     */
    public P2d sum(final V2d v) {
        return new P2d(this.x + v.getX(), this.y + v.getY());
    }

    /**
     * Return a new V2d subtracting the passed V2d.
     * 
     * @param v P2d vector to substract
     * @return a new V2d vector which is the substraction of the current and the passed
     */
    public V2d sub(final P2d v) {
        return new V2d(this.x - v.x, this.y - v.y);
    }

    /**
     * Return a new V2d subtracting the passed V2d.
     * 
     * @param otherPoint P2d other vector for which the distance will be calculated
     * @return the distance
     */
    public double distanceFrom(final P2d otherPoint) {
        return Math.sqrt(Math.pow(this.getX() - otherPoint.getX(), 2) + Math.pow(this.getY() - otherPoint.getY(), 2));
    }

    /**
     * @return a string describing the vector
     */
    @Override
    public String toString() {
        return "Point2D(" + this.x + "," + this.y + ")";
    }

}
