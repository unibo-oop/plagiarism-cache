package frogger.model.implementations;

import frogger.common.Pair;
import frogger.common.Position;
import frogger.model.interfaces.PowerUp;

/**
 * Abstract implementation of the {@link PowerUp} interface representing a pickable power-up object in the game.
 * <p>
 * This class manages the activation, duration, and deactivation of power-ups, as well as the application and removal
 * of their effects. Subclasses must implement the specific effect logic.
 * </p>
 */
public abstract class PowerUpImpl extends PickableObjectImpl implements PowerUp {
    // Duration in seconds
    private final int duration;
    // Indicates if the power-up is currently active
    private boolean active;
    private long startTime;

    /**
     * Constructs a PowerUpImpl with the specified position, dimensions, and duration.
     *
     * @param pos the position of the power-up
     * @param dimension the dimensions of the power-up
     * @param duration the duration of the power-up effect in seconds
     */
    public PowerUpImpl(final Position pos, final Pair dimension, final int duration) {
        super(pos, dimension);
        this.duration = duration;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onPick() {
        activate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void activate() {
        active = true;
        startTime = System.currentTimeMillis();
        applyEffect();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deactivate() {
        active = false;
        removeEffect();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isActive() {
        if (active && this.getTimer() <= 0) {
            deactivate();
        }
        return active;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getTimer() {
        final float elapsedTime = (System.currentTimeMillis() - startTime) / 1000f;
        return duration - elapsedTime;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract PickableObjectDependency getRequiredDependencies();

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract PowerUpType getPowerUpType();

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void applyEffect();

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void removeEffect();
}
