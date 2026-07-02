package model.gui.person;

import java.awt.Color;
import java.awt.Point;

/**
 * 
 * Implementation of the Circle class by passing it the various parameters and getter and setter.
 *
 */
public class CircleImpl implements Circle {

    private static final long serialVersionUID = 1L;
    private double radius;
    private Color color;
    private int x;
    private int y;


    public CircleImpl() {
        this.radius = 2;
        this.color = color;
        this.x = 0;
        this.y = 0;
    }

    /**
     * {@inheritDoc}
     */
    public final double getRadius() {
        return radius;
    }

    /**
     * {@inheritDoc}
     */
    public final Color getColor() {
        return color;
    }

    /**
     * {@inheritDoc}
     */
    public final void setRadius(final double r) {
        this.radius = r;
    }

    /**
     * {@inheritDoc}
     */
    public final void setColor(final Color color) {
        this.color = color;
    }

    /**
     * {@inheritDoc}
     */
    public final void setX(final int x) {
        this.x = x;
    }

    /**
     * {@inheritDoc}
     */
    public final void setY(final int y) {
        this.y = y;

    }

    /**
     * {@inheritDoc}
     * 
     */
    public final int getX() {
        return x;
    }

    /**
     * {@inheritDoc}
     * 
     */
    public final int getY() {
        return y;
    }

}
