package utils;

import org.jbox2d.common.Vec2;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/**
 * Utility class to work with the physic engine.
 */
public final class Box2DUtils {

    private Box2DUtils() {
    }

    /**
     * Converts a {@link Vec2} to a {@link Point2D}.
     * 
     * @param vector The Vec2
     * @return The Point2D
     */
    public static <C extends Vec2> Point2D vecToPoint(final C vector) {
        if (!vector.isValid()) {
            return Point2D.ZERO;
        }
        return new Point2D(vector.x, vector.y);
    }

    /**
     * Converts a {@link Point2D} to a {@link Vec2}.
     * 
     * @param point The Point2D
     * @return The Vec2
     */
    public static Vec2 pointToVec(final Point2D point) {
        return new Vec2((float) point.getX(), (float) point.getY());
    }

    /**
     * Convert a {@link Vec2} to a {@link Dimension2D}
     * 
     * @param vector the vector to convert
     * @return the dimension
     */
    public static Dimension2D vecToDim(final Vec2 vector) {
        return new Dimension2D(vector.x, vector.y);
    }
}
