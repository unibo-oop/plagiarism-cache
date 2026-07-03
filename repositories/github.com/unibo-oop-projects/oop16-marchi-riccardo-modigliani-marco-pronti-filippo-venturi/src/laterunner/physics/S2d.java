package laterunner.physics;

/**
 * The class in witch is implemented the concept of speed of the Vehicles.
 *
 */
public class S2d implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private double x;
    private double y;

    /**
     * 
     * @param nX
     *          the X component of speed
     * @param nY
     *          the y component of speed
     */
    public S2d(final double nX, final double nY) {
        this.x = nX;
        this.y = nY;
    }

    /**
     * 
     * @param to
     *          the start point
     * @param from
     *          the end point
     */
    public S2d(final P2d to, final P2d from) {
        this.x = to.getX() - from.getX();
        this.y = to.getY() - from.getY();
    }
 
    /**
     *  Add at speed v.
     * 
     * @param v
     *          the speed to sum at the actual speed
     * @return
     *          the new speed
     */
    public S2d sum(final S2d v) {
        return new S2d(x + v.x, y + v.y);
    }
    /**
     * Get the speed's module.
     * 
     * @return
     *          the speed's module
     */
    public double module() {
        return (double) Math.sqrt(x * x + y * y);
    }

    /**
     * Normalize the speed.
     * 
     * @return
     *          the speed normalized
     */
    public S2d getNormalized() {
        double module = (double) Math.sqrt(x * x + y * y);
        return new S2d(x / module, y / module);
    }

    /**
     * Mul the speed by fact.
     * 
     * @param fact
     *          param to mul speed
     * @return
     *          speed * fact
     */
    public S2d mul(final double fact) {
        return new S2d(x * fact, y * fact);
    }

    @Override
    public String toString() {
        return "S2d(" + x + "," + y + ")";
    }
    /**
     * Get the X component.
     * 
     * @return
     *          the X component
     */
    public double getX() {
        return x;
    }

    /**
     * Set the x component.
     * 
     * @param nX
     *          param to set x
     */
    public void setX(final double nX) {
        this.x = nX;
    }

    /**
     * Get the Y component.
     * 
     * @return
     *          the Y component
     */
    public double getY() {
        return y;
    }

    /**
     * Set the y component.
     * 
     * @param nY
     *          param to set y
     */
    public void setY(final double nY) {
        this.y = nY;
    }
}