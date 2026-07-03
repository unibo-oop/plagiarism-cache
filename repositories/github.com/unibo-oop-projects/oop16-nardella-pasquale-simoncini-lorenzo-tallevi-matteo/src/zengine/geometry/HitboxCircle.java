package zengine.geometry;

import zengine.constants.ZengineColor;
import zengine.core.Zengine;
import zengine.interfaces.GameEngine;

/**
 * This class represents a circle described through his center and his radius.
 * Rotation is not supported by this shape.
 */
public class HitboxCircle implements Hitbox {

    private final GameEngine ze = Zengine.getEngine();
    private double x;
    private double y;
    private double radius;

    /**
     * builds a new circle hitbox.
     * 
     * @param x
     *            x coordinate of the center
     * @param y
     *            y coordinate of the center
     * @param radius
     *            radius of the circle
     */
    public HitboxCircle(final double x, final double y, final double radius) {
        this.x = x;
        this.y = y;
        this.radius = ze.abs(radius);
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

    /**
     * returns the radius of this circle.
     * 
     * @return the radius of the circle
     */
    public double getRadius() {
        return radius;
    }

    /**
     * sets the radius of this circle to the given length.
     * 
     * @param radius
     *            new length of the radius
     */
    public void setRadius(final double radius) {
        this.radius = ze.abs(radius);
    }

    @Override
    public boolean isTouching(final HitboxPoint other) {
        return ze.pointInCircle(other.getX(), other.getY(), getX(), getY(), getRadius());
    }

    @Override
    public boolean isTouching(final HitboxCircle other) {
        return ze.pointDistance(getX(), getY(), other.getX(), other.getY()) <= getRadius() + other.getRadius();
    }

    @Override
    public boolean isTouching(final HitboxRectangle other) {
        return ze.rectangleOverlapsCircle(other.getX(), other.getY(), other.getX() + other.getWidth(),
                other.getY() + other.getHeight(), getX(), getY(), getRadius());
    }

    @Override
    public boolean isTouching(final HitboxMultishape other) {
        return other.isTouching(this);
    }

    @Override
    public void setRotation(final double angle) {
        // circles cannot be rotated
    }

    @Override
    public double getRotation() {
        // circles cannot be rotated
        return 0;
    }

    @Override
    public void drawSelf(final ZengineColor color, final boolean filled) {
        ze.drawCircle(getX(), getY(), getRadius(), color, filled);
    }

}
