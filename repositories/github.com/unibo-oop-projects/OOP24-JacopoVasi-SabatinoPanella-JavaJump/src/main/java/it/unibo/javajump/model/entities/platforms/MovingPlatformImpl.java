package it.unibo.javajump.model.entities.platforms;

import it.unibo.javajump.model.entities.objectstrategies.HorizontalOscillationMovement;
import it.unibo.javajump.model.entities.objectstrategies.MovementBehaviour;

import static it.unibo.javajump.utility.Constants.NULL_PLATFORM_VELOCITY;

/**
 * The MovingPlatformImpl class, an implementation of a moving platform.
 */
public final class MovingPlatformImpl extends PlatformImpl implements MovingPlatform {

    private final MovementBehaviour movementBehaviour;

    /**
     * Instantiates a new Moving platform. If the platform is out of bounds, it stops and changes direction,
     * oscillating according to the MovementBehaviour.
     *
     * @param xx          the xx position
     * @param y           the y position
     * @param width       the width of the platform
     * @param height      the height of the platform
     * @param range       the range in which the platform oscillates
     * @param screenWidth the screen width, to keep the platform in bounds
     * @param speed       the movement speed
     */
    public MovingPlatformImpl(final float xx, final float y, final float width, final float height,
                              final float range, final float screenWidth, final float speed) {

        super(xx, y, width, height);
        float x = xx;
        if (x < NULL_PLATFORM_VELOCITY) {
            x = NULL_PLATFORM_VELOCITY;
        }
        if (x > screenWidth - width) {
            x = screenWidth - width;
        }
        this.x = x;

        float potentialMin = x - range;
        float potentialMax = x + range;
        if (potentialMin < NULL_PLATFORM_VELOCITY) {
            potentialMin = NULL_PLATFORM_VELOCITY;
        }
        if (potentialMax > screenWidth - width) {
            potentialMax = screenWidth - width;
        }

        this.movementBehaviour = new HorizontalOscillationMovement(potentialMin, potentialMax, speed);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final float deltaTime) {
        super.update(deltaTime);

        movementBehaviour.update(this, deltaTime);
    }
}
