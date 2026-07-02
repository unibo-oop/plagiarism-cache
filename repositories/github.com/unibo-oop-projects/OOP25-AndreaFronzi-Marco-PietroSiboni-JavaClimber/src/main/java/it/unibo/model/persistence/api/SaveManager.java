package it.unibo.model.persistence.api;

import java.util.Optional;

/**
 * Manage the saving and loading of the entire game state.
 */
public interface SaveManager {

    /**
     * Saves the current game state.
     * 
     * @param state the state to save
     */
    void save(SaveState state);

    /**
     * Load the game state.
     * 
     * @return an Optional containing the saved file if it exists
     */
    Optional<SaveState> load();
}
