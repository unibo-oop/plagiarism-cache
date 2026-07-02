package it.unibo.javajump.model.physics;

import static it.unibo.javajump.utility.Constants.NULL_VELOCITY;

/**
 * The implementation of PhysicsUtils, implements useful static physics methods.
 */
public final class PhysicsUtilsImpl implements PhysicsUtils {

    /**
     * Method to calculate acceleration to right.
     *
     * @param vx           the current changing velocity
     * @param deltaTime    the delta time
     * @param acceleration the acceleration factor
     * @param maxSpeed     the max speed reachable
     *
     * @return the accelerated velocity
     */
    public static float accelerateToRight(final float vx, final float deltaTime, final float acceleration, final float maxSpeed) {
        final float vxx = vx + acceleration * deltaTime;
        return Math.min(vxx, maxSpeed);
    }


    /**
     * Method to calculate acceleration to right.
     *
     * @param vx           the current changing velocity
     * @param deltaTime    the delta time
     * @param acceleration the acceleration factor
     * @param maxSpeed     the max speed reachable
     *
     * @return the accelerated velocity
     */
    public static float accelerateToLeft(final float vx, final float deltaTime, final float acceleration, final float maxSpeed) {
        final float vxx = vx - acceleration * deltaTime;
        return Math.max(vxx, -maxSpeed);
    }


    /**
     * Decelerate float.
     *
     * @param vxx          the vxx
     * @param deltaTime    the delta time
     * @param deceleration the deceleration
     * @return the float
     */
    public static float decelerate(final float vxx, final float deltaTime, final float deceleration) {
        float vx = vxx;
        if (vx > NULL_VELOCITY) {
            vx -= deceleration * deltaTime;
            if (vx < NULL_VELOCITY) {
                vx = NULL_VELOCITY;
            }
        } else if (vx < NULL_VELOCITY) {
            vx += deceleration * deltaTime;
            if (vx > NULL_VELOCITY) {
                vx = NULL_VELOCITY;
            }
        }
        return vx;
    }

    /**
     * Private constructor, this class should not be instantiated.
     *
     * @throws AssertionError the assertion error thrown if instantiated
     */
    private PhysicsUtilsImpl() {
        throw new AssertionError("This is a utility class, it should not be instantiated!");
    }
}
