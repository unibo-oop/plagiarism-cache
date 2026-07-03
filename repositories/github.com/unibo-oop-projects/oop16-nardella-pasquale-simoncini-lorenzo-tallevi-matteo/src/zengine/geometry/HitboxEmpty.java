package zengine.geometry;

import zengine.constants.ZengineColor;

/**
 * This class represents an empty shape. It does not interact with any other
 * shape, and does not move or rotate
 */
public class HitboxEmpty implements Hitbox {

    // immobile and ethereal

    @Override
    public double getX() {
        return 0;
    }

    @Override
    public double getY() {
        return 0;
    }

    @Override
    public void setX(final double x) {
    }

    @Override
    public void setY(final double y) {
    }

    @Override
    public void setRotation(final double angle) {
    }

    @Override
    public double getRotation() {
        return 0;
    }

    @Override
    public boolean isTouching(final HitboxPoint other) {
        return false;
    }

    @Override
    public boolean isTouching(final HitboxCircle other) {
        return false;
    }

    @Override
    public boolean isTouching(final HitboxRectangle other) {
        return false;
    }

    @Override
    public boolean isTouching(final HitboxMultishape other) {
        return false;
    }

    @Override
    public void drawSelf(final ZengineColor color, final boolean filled) {
    }

}
