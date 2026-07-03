package zengine.geometry;

import zengine.constants.ZengineColor;
import zengine.core.Zengine;
import zengine.interfaces.GameEngine;

/**
 * This class represents a point in a 2D space. A point has no dimensions and
 * two points are never touching.
 */
public class HitboxPoint implements Hitbox {
    // x and y coordinates of the point
    private final GameEngine ze = Zengine.getEngine();
    private double x;
    private double y;

    /**
     * builds a new point.
     * 
     * @param x
     *            x coordinate of the point
     * @param y
     *            y coordinate of the point
     */
    public HitboxPoint(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public void setX(final double x) {
        this.x = x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void setY(final double y) {
        this.y = y;
    }

    @Override
    public boolean isTouching(final HitboxPoint other) {
        // two points never touch
        return false;
    }

    @Override
    public boolean isTouching(final HitboxCircle other) {
        return ze.pointInCircle(getX(), getY(), other.getX(), other.getY(), other.getRadius());
    }

    @Override
    public boolean isTouching(final HitboxRectangle other) {
        return ze.pointInRectangle(getX(), getY(), other.getX(), other.getY(), other.getX() + other.getWidth(),
                other.getY() + other.getHeight());
    }

    @Override
    public boolean isTouching(final HitboxMultishape other) {
        return other.isTouching(this);
    }

    @Override
    public void setRotation(final double angle) {
        // points cannot rotate
    }

    @Override
    public double getRotation() {
        // points cannot rotate
        return 0;
    }

    @Override
    public void drawSelf(final ZengineColor color, final boolean filled) {
        ze.drawPoint(getX(), getY(), color);
    }

}
