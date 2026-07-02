package frogger.model.implementations;

import java.util.List;

import frogger.common.Pair;
import frogger.common.Position;

/**
 * The {@code FreezePowerUp} class represents a power-up that, when applied,
 * temporarily freezes all related moving entities by setting their speed to zero.
 * Upon removal of the effect, the original speeds of the entities are restored.
 * <p>
 * This power-up is typically associated with obstacles and is identified by the
 * {@link PowerUpType#FREEZE} type.
 * </p>
 *
 * <p>
 * Usage:
 * <ul>
 *   <li>Call {@link #applyEffect()} to freeze all related {@link MovingObjectImpl} entities.</li>
 *   <li>Call {@link #removeEffect()} to restore their original speeds.</li>
 * </ul>
 * </p>
 *
 * <p>
 * The original speeds are stored internally in a float array for restoration.
 * </p>
 *
 * @see PowerUpImpl
 * @see MovingObjectImpl
 * @see PowerUpType
 */
public class FreezePowerUp extends PowerUpImpl {

    private float[] copyEntitiesSpeed = new float[0];

    /**
     * Constructs a new {@code FreezePowerUp} at the specified position with the given dimensions and duration.
     *
     * @param pos        the position of the power-up
     * @param dimension  the dimensions of the power-up
     * @param duration   the duration for which the power-up effect lasts
     */
    public FreezePowerUp(final Position pos, final Pair dimension, final int duration) {
        super(pos, dimension, duration);
        super.setImage("freezePowerup.png");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void applyEffect() {
        if (super.getRelatedEntity() instanceof List<?> entities) {
            final float[] entitiesSpeed = new float[entities.size()]; // Initialize the array to store original speeds
            int i = 0; // Index for storing speeds
            for (final Object obj : entities) {
                if (obj instanceof MovingObjectImpl movingObjectImpl) {
                    entitiesSpeed[i++] = movingObjectImpl.getSpeed(); // Store the original speed
                    movingObjectImpl.setSpeed(0); // Stop the entity
                }
            }
            setEntitiesSpeed(entitiesSpeed);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeEffect() {
        if (super.getRelatedEntity() instanceof List<?> entities) {
            int i = 0;
            for (final Object obj : entities) {
                if (obj instanceof MovingObjectImpl movingObjectImpl) {
                    movingObjectImpl.setSpeed(this.copyEntitiesSpeed[i++]); // Restore the original speed
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PickableObjectDependency getRequiredDependencies() {
        return PickableObjectDependency.OBSTACLE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PowerUpType getPowerUpType() {
        return PowerUpType.FREEZE;
    }

    /**
     * Gets the current speeds of the entities affected by this power-up.
     *
     * @return an array of floats representing the speeds of the entities
     */
    public float[] getEntitiesSpeed() {
        return this.copyEntitiesSpeed.clone();
    }

    /**
     * Sets the speeds of the entities affected by this power-up.
     *
     * @param copyES an array of floats representing the speeds to be set for the entities
     */
    public void setEntitiesSpeed(final float[] copyES) {
        this.copyEntitiesSpeed = copyES.clone();
    }
}
