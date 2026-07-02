package model.entities;

import org.dyn4j.geometry.Vector2;

/**
 * Defines entities position.
 */
public final class Position extends Vector2 {

    /**
     * @param x The x-axis
     * @param y The y-axis
     */
    public Position(final double x, final double y) {
        super(x, y);
    }

    /**
     * Empty constructor.
     */
    public Position() {
    }

    /**
     * @return The horizontal position
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return The vertical position
     */
    public double getY() {
        return this.y;
    }
}
