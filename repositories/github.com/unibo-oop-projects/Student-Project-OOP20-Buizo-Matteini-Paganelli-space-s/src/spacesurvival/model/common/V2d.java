/*
 *   V2d.java
 *
 * Copyright 2000-2001-2002  aliCE team at deis.unibo.it
 *
 * This software is the proprietary information of deis.unibo.it
 * Use is subject to license terms.
 *
 */
package spacesurvival.model.common;

/**
 *
 * 2-dimensional vector.
 * objects are completely state-less
 *
 */
public class V2d implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * x and y represent the x and y of the vector respectively.
     */
    private final double x, y;

    /**
     * Constructor for a new vector with x and y setted to zero.
     */
    public V2d() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * Constructor for a new vector with passed x and passed y.
     * 
     * @param x x coordinate of the vector
     * @param y y coordinate of the vector
     */
    public V2d(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructor for a new vector between two points.
     * 
     * @param to end point of the vector
     * @param from start point of the vector
     */
    public V2d(final P2d to, final P2d from) {
        this.x = to.getX() - from.getX();
        this.y = to.getY() - from.getY();
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
     * Return a new V2d summing the passed V2d.
     * 
     * @param v V2d vector to add
     * @return a new V2d vector which is the sum of the current and the passed
     */
    public V2d sum(final V2d v) {
        return new V2d(x + v.x, y + v.y);
    }

    /**
     * Return the module of the vector.
     * 
     * @return the module of the vector
     */
    public double module() {
        return Math.sqrt(x * x + y * y);
    }

    /**
     * Return the normalized vector of the current V2d.
     * 
     * @return the normalized vector
     */
    public V2d getNormalized() {
        final double module = Math.sqrt(x * x + y * y);
        return new V2d(x / module, y / module);
    }

    /**
     * Return a multiplied vector V2d.
     * 
     * @param fact multiplicative factor
     * @return a new V2d which is the multiplication of the current vector and the passed factor
     */
    public V2d mul(final double fact) {
        return new V2d(x * fact, y * fact);
    }

    /**
     * Return a string describing the vector.
     * 
     * @return a string describing the vector
     */
    @Override
    public String toString() {
        return "V2d(" + x + ", " + y + ")";
    }
}
