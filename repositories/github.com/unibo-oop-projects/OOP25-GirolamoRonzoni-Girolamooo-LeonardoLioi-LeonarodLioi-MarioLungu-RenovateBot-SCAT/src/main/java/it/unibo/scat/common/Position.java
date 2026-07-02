package it.unibo.scat.common;

/**
 * This class is used to manage the position of an entity.
 */
public class Position {
    private int x;
    private int y;

    /**
     * Position constructor.
     * 
     * @param x the x coordinate of an entity.
     * @param y the y coordinate of an entity.
     */
    public Position(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * The x coordinate getter.
     * 
     * @return the x coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * The y coordinate getter.
     * 
     * @return the y coordinate.
     */
    public int getY() {
        return y;
    }

    /**
     * The x coordinate setter.
     * 
     * @param x coordinate.
     */
    public void setX(final int x) {
        this.x = x;
    }

    /**
     * The y coordinate setter.
     * 
     * @param y coordinate.
     */
    public void setY(final int y) {
        this.y = y;
    }

}
