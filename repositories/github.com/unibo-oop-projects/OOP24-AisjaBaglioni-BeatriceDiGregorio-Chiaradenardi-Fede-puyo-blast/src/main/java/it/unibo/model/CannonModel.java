package it.unibo.model;

import it.unibo.model.interfaces.CannonModelInterface;

/**
 * The CannonModel class represents the state and behavior of a cannon.
 * It manages its horizontal position and aiming angle.
 */
public class CannonModel implements CannonModelInterface {
    /**
     * The horizontal position of the cannon, ranging from 0 to 1.
     */
    private double x;
    /**
     * The aiming angle of the cannon, ranging from 0 to 1.
     */
    private double angle;
    /**
     * The step size for horizontal movement.
     */
    private static final double STEP = 0.02;
    /**
     * The step size for aiming adjustments.
     */
    private static final double ANGLE_STEP = 0.02;

    /**
     * Constructor initializing the cannon's position and angle to default values.
     */
    public CannonModel() {
        this.x = 0.5;
        this.angle = 0.5;
    }

    /**
     * Moves the cannon left within the allowed range.
     */
    @Override
    public void moveLeft() {
        this.x = Math.max(this.x - STEP, 0);
    }

    /**
     * Moves the cannon right within the allowed range.
     */
    @Override
    public void moveRight() {
        this.x = Math.min(this.x + STEP, 1);
    }

    /**
     * Gets the current horizontal position of the cannon.
     * 
     * @return the x-coordinate of the cannon.
     */
    @Override
    public double getX() {
        return this.x;
    }

    /**
     * Aims the cannon upward within the allowed range.
     */
    @Override
    public void aimUp() {
        this.angle = Math.min(this.angle + ANGLE_STEP, 1);
    }

    /**
     * Aims the cannon downward within the allowed range.
     */
    @Override
    public void aimDown() {
        this.angle = Math.max(this.angle - ANGLE_STEP, 0);
    }

    /**
     * Gets the current aiming angle of the cannon.
     * 
     * @return the angle of the cannon.
     */
    @Override
    public double getAngle() {
        return this.angle;
    }

}