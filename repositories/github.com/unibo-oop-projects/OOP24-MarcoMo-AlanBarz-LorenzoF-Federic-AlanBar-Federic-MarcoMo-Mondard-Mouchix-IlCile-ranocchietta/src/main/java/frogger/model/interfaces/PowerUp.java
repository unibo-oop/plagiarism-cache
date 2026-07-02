package frogger.model.interfaces;

import frogger.model.implementations.PowerUpType;

/**
 * Represents a power-up in the game, which can be picked up and used by the player.
 * A power-up can be activated to trigger a specific effect, deactivated to revert its effect,
 * and has a limited duration. Instantaneous power-ups are also supported, which do have 0 duration.
 * Each power-up has a specific type and can be queried for its current state and duration.
 */
public interface PowerUp extends PickableObject {

    /**
     * Activates the power-up, triggering its specific effect or behavior.
     */
    void activate();

    /**
     * Deactivates the power-up, reverting any effects it may have applied.
     */
    void deactivate();

    /**
     * Checks if the power-up is currently active. if the power-up isn't active, it will deactivate itself.
     * @return true if the power-up is active, false otherwise.
     */
    boolean isActive();

    /**
     * Returns the duration of the power-up effect in seconds.
     * @return the duration of the power-up effect
     */
    float getTimer();

    /**
     * @return the type of the power-up
     */
    PowerUpType getPowerUpType();

    /**
     * Called when the power-up is picked up by the player.
     * This method should handle any logic that occurs when the power-up is collected.
     */
    void applyEffect();

    /**
     * Called when the power-up effect is removed, either when the duration expires or when the power-up is deactivated.
     * This method should handle any logic that occurs when the power-up effect is no longer active.
     */
    void removeEffect();

}
