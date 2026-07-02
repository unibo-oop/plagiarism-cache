package dev.spaccabolle.entity;

/**
 * The Class DynamicObject.
 */
public abstract class DynamicObject extends Entity{
    
    /** The Constant DEFAULT_SPEED. */
    public static final int DEFAULT_SPEED = 10;
    
    /** The speed. */
    protected float speed;
    
    /** The y move. */
    protected double xMove, yMove;

    /**
     * Instantiates a new dynamic object.
     *
     * @param x the x
     * @param y the y
     * @param width the width
     * @param height the height
     */
    public DynamicObject(float x, float y, int width, int height) {
        super(x, y, width, height);
        this.speed = DynamicObject.DEFAULT_SPEED;
        this.xMove = 0;
        this.yMove = 0;
    }
    
    /**
     * Move.
     */
    public void move() {
        x += this.xMove;
        y += this.yMove;
    }

    /**
     * Gets the speed.
     *
     * @return the speed
     */
    public float getSpeed() {
        return this.speed;
    }

    /**
     * Sets the speed.
     *
     * @param speed the new speed
     */
    public void setSpeed(float speed) {
        this.speed = speed;
    }
    
    /**
     * Gets the x move.
     *
     * @return the x move
     */
    public double getxMove() {
        return this.xMove;
    }

    /**
     * Sets the x move.
     *
     * @param xMove the new x move
     */
    public void setxMove(double xMove) {
        this.xMove = xMove;
    }

    /**
     * Gets the y move.
     *
     * @return the y move
     */
    public double getyMove() {
        return this.yMove;
    }

    /**
     * Sets the y move.
     *
     * @param yMove the new y move
     */
    public void setyMove(double yMove) {
        this.yMove = yMove;
    }

}
