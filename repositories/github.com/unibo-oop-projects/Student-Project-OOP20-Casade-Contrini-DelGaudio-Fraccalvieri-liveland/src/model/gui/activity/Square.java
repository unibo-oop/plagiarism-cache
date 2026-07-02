package model.gui.activity;

import java.awt.Color;

public class Square {
    private int width;
    private int height;
    private int x;
    private int y;
    private String name;
    private Color color;

    /**
     * 
     */
    public Square() {
        this.width = 0;
        this.height = 0;
        this.x = 0;
        this.y = 0;
        this.name = null;
        this.color = Color.BLACK;
    }

    /**
     * 
     * @return name  
     */
    public final String getName() {
        return name;
    }

    /**
     * 
     * @param name activity name
     */
    public final void setName(final String name) {
        this.name = name;
    }

    /**
     * 
     * @return width
     */
    public final int getWidth() {
        return width;
    }

    /**
     * 
     * @param width activity width 
     */
    public final void setWidth(final int width) {
        this.width = width;
    }

    /**
     * 
     * @return height
     */
    public final int getHeight() {
        return height;
    }

    /**
     * 
     * @param height activity height
     */
    public final void setHeight(final int height) {
        this.height = height;
    }

    /**
     * 
     * @return color
     */
    public final Color getColor() {
        return color;
    }

    /**
     * 
     * @param color activity color
     */
    public final void setColor(final Color color) {
        this.color = color;
    }

    /**
     * 
     * @return coordinate x
     */
    public final int getX() {
        return x;
    }

    /**
     * 
     * @param x coordinate x
     */
    public final void setX(final int x) {
        this.x = x;
    }

    /**
     * 
     * @return coordinate y
     */
    public final int getY() {
        return y;
    }

    /**
     * 
     * @param y coordinate y
     */
    public final void setY(final int y) {
        this.y = y;
    }

}
