package it.unibo.isaccoop.model.common;

/**
 * Vector2D class to model a vector into 2d space.
 * */
public class Vector2D {

    private final double x;
    private final double y;

    /**
     * Constructor for {@link Vector2D}.
     *
     * @param x x coordinate
     * @param y y coordinate
     * */
    public Vector2D(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructor for {@link Vector2D} using the difference between two {@link Point2D}.
     *
     * @param to destination point
     * @param from origin point
     * */
    public Vector2D(final Point2D to, final Point2D from) {
        this.x = to.getX() - from.getX();
        this.y = to.getY() - from.getY();
    }

    /**
     * Method to sum two {@link Vector2D}.
     *
     * @param v vector to sum
     * @return sum between two {@link Vector2D}
     * */
    public Vector2D sum(final Vector2D v) {
        return new Vector2D(this.x + v.x, this.y + v.y);
    }

    /**
     * Method to compute the module of this {@link Vector2D}.
     *
     * @return module of this vector
     * */
    public double module() {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    /**
     * Method to normalize this {@link Vector2D}.
     *
     * @return normalized vector
     * */
    public Vector2D getNormalized() {
        final double module = Math.sqrt(this.x * this.x + this.y * this.y);
        return new Vector2D(this.x / module, this.y / module);
    }

    /**
     * Method for multiplying the vector by a scalar number.
     *
     * @param fact scalar number
     * @return computed vector result
     * */
    public Vector2D mul(final double fact) {
        return new Vector2D(this.x * fact, this.y * fact);
    }

    /**
     * Get vector x.
     *
     * @return vector x
     * */
    public double getX() {
        return this.x;
    }

    /**
     * Get vector y.
     *
     * @return vector y
     * */
    public double getY() {
        return this.y;
    }

}
