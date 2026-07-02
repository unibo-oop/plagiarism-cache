package frogger.model.interfaces;

import java.util.List;

import frogger.model.implementations.PickableObjectImpl;

/**
 * Interface for managing pickable objects and power-ups in the game.
 * Provides methods to check for active power-ups, add new pickable objects,
 * and retrieve the list of currently active power-ups.
 */
public interface PickableObjectManager {

    /**
     * Updates the state of all active power-ups.
     * This method should be called periodically to refresh power-up effects,
     * remove expired power-ups, and ensure the game state is consistent.
     */
    void checkPowerUps();

    /**
     * Adds a new pickable object to the game and activates it. 
     * If a power-up of the same type is already active, the new one will replace the existing effect.
     *
     * @param x the pickable object to be added and activated
     */
    void addPickableObject(PickableObjectImpl x);

    /**
     * Retrieves the list of currently active power-ups in the game.
     *
     * @return a list of active power-ups
     */
    List<PowerUp> getActivePowerUps();
}
