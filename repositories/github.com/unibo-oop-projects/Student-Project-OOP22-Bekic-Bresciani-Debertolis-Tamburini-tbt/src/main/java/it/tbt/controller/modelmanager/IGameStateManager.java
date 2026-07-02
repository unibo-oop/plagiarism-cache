package it.tbt.controller.modelmanager;

import it.tbt.model.GameState;

/**
 * Interface for the Model manager, which encapsulates all the operations
 * which are requested it can receive.
 */

public interface IGameStateManager {

    /**
     * @return the current ModelState.
     */
    ModelState getStateModel();
    /**
     * @return the current GameState.
     */
    GameState getState();
    /**
     * @return true if the GameState has changed.
     * False otherwise.
     */
    Boolean hasStateChanged();

}
