package it.unibo.modularcheckers.model;

import java.io.Serializable;

/**
 * Coordinate class.
 */
public class Coordinate extends Pair<Integer, Integer> implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -71262746078250424L;

    /**
     * Constant used for invert numbers.
     */
    private static final int INVERTER = -1;

    /**
     * default constructor.
     * 
     * @param x x value
     * @param y y value
     */
    public Coordinate(final Integer x, final Integer y) {
        super(x, y);
    }

    /**
     * Invert the Y element.
     */
    public void invertY() {
        this.setY(this.getY() * INVERTER);
    }

    /**
     * Translate this coordinate to another.
     * @param to coordinate to translate
     */
    public void translate(final Coordinate to) {
        this.setY(this.getY() + to.getY());
        this.setX(this.getX() + to.getX());
    }

    /**
     * Check if the coordinate is inside of the specified borders.
     * @param minX minimum x value
     * @param minY minimum y value
     * @param maxX maximum x value
     * @param maxY maximum y value
     * @return true if is inside, else false.
     */
    public boolean isInsideBorders(final int minX, final int minY, final int maxX, final int maxY) {
        return this.getX() >= minX && this.getX() <= maxX && this.getY() >= minY && this.getY() <= maxY;
    }
}
