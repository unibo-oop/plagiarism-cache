package it.unibo.model;

import it.unibo.model.interfaces.BulletModelInterface;

/**
 * Manages the bullets shot in the game.
 */

public class BulletModel implements BulletModelInterface {
    /**
     * Source position in (x,y) coordinates
     */
    private Point2D source;
    /**
     * Target position in (x,y) coordinates
     */
    private Point2D target;
    /**
     * Indicates whether the bullet is still in the game.
     */
    private boolean active;
    /**
     * Lifetime of the bullet since it was shot.
     */
    private int ticks;
    /**
     * Total lifetime of the bullet (same as animation)
     */
    private static final int ANIMATIONTIME = 15;

    /**
     * Constructs a new inactive bullet
     */
    public BulletModel() {
        this.source = null;
        this.target = null;
        this.active = false;
        this.ticks = 0;
    }

    /**
     * Initializes the bullet and shoots it towards the grid
     * 
     * @param source The source position of the bullet
     * @param target The target position of the bullet
     */
    @Override
    public void shootBullet(Point2D source, Point2D target) {
        this.source = source;
        this.target = target;
        this.active = true;
        this.ticks = 0;
    }

    /**
     * Checks if the bullet's animation has ended.
     * 
     * @return {@code true} if the bullet is still in motion, {@code false}
     *         otherwise.
     */
    @Override
    public boolean targetReached() {
        if (this.ticks >= ANIMATIONTIME) {
            return true;
        }
        return false;
    }

    /**
     * Updates the bullet's lifetime if it is still active.
     * 
     * @return {@code true} if the bullet can still update its position,
     *         {@code false} otherwise.
     */
    @Override
    public boolean updatePosition() {
        if (isActive()) {
            this.ticks++;
            if (this.ticks > ANIMATIONTIME) {
                this.active = false;
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Checks if the bullet is still active and thus being animated.
     * 
     * @return {@code true} if the bullet is active, {@code false} otherwise.
     */
    @Override
    public boolean isActive() {
        return this.active;
    }

    /**
     * Calculates the current position of the projectile
     * along its trajectory from the source to the target, based on elapsed time.
     * It uses a linear interpolation with the alpha value:
     * alpha = ticks / ANIMATIONTIME which varies from 0 (start) to 1 (finish).
     * 
     * @return the current position in {@link Point2D}
     */
    @Override
    public Point2D getCurrentPosition() {
        double alpha = ((double) this.ticks) / ((double) ANIMATIONTIME);
        Point2D left = Point2D.mul(source, 1.0 - alpha);
        Point2D right = Point2D.mul(target, alpha);
        return Point2D.add(left, right);
    }
}