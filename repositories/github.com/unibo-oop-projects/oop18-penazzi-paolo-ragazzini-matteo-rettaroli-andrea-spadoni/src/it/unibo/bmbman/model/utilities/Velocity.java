package it.unibo.bmbman.model.utilities;

import it.unibo.bmbman.view.utilities.ScreenToolUtils;

/**
 * Models the concept of velocity of a living entity.
 * It's a vector with x e y component.
 *
 */
public class Velocity {
    /**
     * Velocity is zero, the entity is not moving.
     */
    public static final Velocity ZERO = new Velocity(0, 0);
    /**
     * Constant value for velocity.
     */
    public static final int SPEED = 2 * ScreenToolUtils.SCALE;
    private Pair<Integer, Integer> vel;
    /**
     * Construct the vector of velocity.
     * @param x components
     * @param y components
     */
    public Velocity(final int x, final int y) {
        vel = new Pair<>(x, y);
    }
    /**
     * Used to get the x component of velocity.
     * @return x component
     */
    public int getXcomponent() {
        return vel.getX();
    }
    /**
     * Used to get the y component of velocity.
     * @return y component
     */
    public int getYcomponent() {
        return vel.getY();
    }
    /**
     * Used to set x component of velocity.
     * @param x the value of new x component
     */
    public void setXcomponent(final int x) {
        vel = new Pair<>(x, this.vel.getY());
    }
    /**
     * Used to set y component of velocity.
     * @param y the value of new y component
     */
    public void setYcomponent(final int y) {
        vel = new Pair<>(this.vel.getX(), y);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Velocity [x=" + vel.getX() + "y=" + vel.getY() + "]";
    }
}

