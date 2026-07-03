package zengine.geometry;

import zengine.constants.ZengineColor;
import zengine.core.Zengine;
import zengine.interfaces.GameEngine;

/**
 * This class represents a rectangle, described by its top-left corner position,
 * width and height. This kind of rectangle cannot rotate
 */
public class HitboxRectangle implements Hitbox {

    private final GameEngine ze = Zengine.getEngine();
    private double x;
    private double y;
    private double width;
    private double height;

    /**
     * builds a new rectangle.
     * 
     * @param x
     *            x coordinate of the top-left corner
     * @param y
     *            y coordinate of the top-left corner
     * @param width
     *            width of the rectangle
     * @param height
     *            height of the rectangle
     */
    public HitboxRectangle(final double x, final double y, final double width, final double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        normalizeRectangle();
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
     * returns the width of this rectangle.
     * 
     * @return the width of the rectangle
     */
    public double getWidth() {
        return width;
    }

    /**
     * sets the width of this rectangle to the specified value.
     * 
     * @param width
     *            new width of the rectangle
     */
    public void setWidth(final double width) {
        this.width = width;
        normalizeRectangle();
    }

    /**
     * returns the height of this rectangle.
     * 
     * @return the height of the rectangle
     */
    public double getHeight() {
        return height;
    }

    /**
     * sets the height of this rectangle to the specified value.
     * 
     * @param height
     *            new height of the rectangle
     */
    public void setHeight(final double height) {
        this.height = height;
        normalizeRectangle();
    }

    @Override
    public boolean isTouching(final HitboxPoint other) {
        return ze.pointInRectangle(other.getX(), other.getY(), getX(), getY(), getX() + getWidth(), getY() + getHeight());
    }

    @Override
    public boolean isTouching(final HitboxCircle other) {
        return ze.rectangleOverlapsCircle(getX(), getY(), getX() + getWidth(), getY() + getHeight(), other.getX(), other.getY(),
                other.getRadius());
    }

    @Override
    public boolean isTouching(final HitboxRectangle other) {
        return ze.rectangleOverlaps(getX(), getY(), getX() + getWidth(), getY() + getHeight(), other.getX(), other.getY(),
                other.getX() + other.getWidth(), other.getY() + other.getHeight());
    }

    @Override
    public boolean isTouching(final HitboxMultishape other) {
        return other.isTouching(this);
    }

    @Override
    public void setRotation(final double angle) {
        // rectangles cannot rotate

    }

    @Override
    public double getRotation() {
        // rectangles cannot rotate
        return 0;
    }

    private void normalizeRectangle() {
        // forces positive values only
        if (width < 0) {
            x = x + width;
            width = ze.abs(width);
        }

        if (height < 0) {
            y = y + height;
            height = ze.abs(height);
        }
    }

    @Override
    public void drawSelf(final ZengineColor color, final boolean filled) {
        ze.drawRectangle(getX(), getY(), getWidth(), getHeight(), color, filled);
    }

}
