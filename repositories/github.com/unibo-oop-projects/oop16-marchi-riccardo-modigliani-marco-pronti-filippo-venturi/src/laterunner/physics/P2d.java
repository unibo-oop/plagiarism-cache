package laterunner.physics;

/**
 * Position on screen.
 */
public class P2d implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private double x;
    private double y;

    /**
     * 
     * @param nX
     *          param to set x
     * @param nY
     *          param to set y
     */
    public P2d(final double nX, final double nY) {
        this.x = nX;
        this.y = nY;
    }

    /**
     * Translate the point.
     * 
     * @param v
     *          the vector to translate the point
     * @return
     *          the point after the translation
     */
    public P2d sum(final S2d v) {
        return new P2d(x + v.getX(), y + v.getY());
    }

    /**
     * Calculate a speed by a subtraction of two point.
     * 
     * @param v
     *          the point to subtract
     * @return
     *          the speed given by the subtraction
     */
    public S2d sub(final P2d v) {
        return new S2d(x - v.x, y - v.y);
    }

    @Override
    public String toString() {
        return "P2d(" + x + "," + y + ")";
    }

    /**
     * Get the x component.
     * 
     * @return
     *          the x component
     */
    public double getX() {
        return x;
    }

    /**
     * Set the x component.
     * 
     * @param nX
     *          the new x component
     */
    public void setX(final double nX) {
        this.x = nX;
    }

    /**
     * Get the y component.
     * 
     * @return
     *          the y component
     */
    public double getY() {
        return y;
    }

    /**
     * Set the y component.
     * 
     * @param nY
     *          the new y component
     */
    public void setY(final double nY) {
        this.y = nY;
    }

}

