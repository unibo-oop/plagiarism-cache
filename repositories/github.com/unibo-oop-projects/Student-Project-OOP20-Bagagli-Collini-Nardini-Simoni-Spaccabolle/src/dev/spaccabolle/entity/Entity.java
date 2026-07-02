package dev.spaccabolle.entity;

import java.awt.Graphics;
import java.io.IOException;

/**
 * The Class Entity.
 */
public abstract class Entity {
    
    /** The x. */
    public float x;

    /** The y. */
	public float y;
    
    /** The height. */
    protected int width, height;

    /**
     * Instantiates a new entity.
     *
     * @param x the x
     * @param y the y
     * @param width the width
     * @param height the height
     */
    public Entity(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    /**
     * Gets the x.
     *
     * @return the x
     */
    public float getX() {
        return this.x;
    }

    /**
     * Sets the x.
     *
     * @param x the new x
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * Gets the y.
     *
     * @return the y
     */
    public float getY() {
        return this.y;
    }

    /**
     * Sets the y.
     *
     * @param y the new y
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * Gets the width.
     *
     * @return the width
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Sets the width.
     *
     * @param width the new width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Gets the height.
     *
     * @return the height
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Sets the height.
     *
     * @param height the new height
     */
    public void setHeight(int height) {
        this.height = height;
    }
    

    /**
     * Tick.
     */
    public abstract void tick();
    
    /**
     * Render.
     *
     * @param g the g
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public abstract void render(Graphics g) throws IOException;

}

