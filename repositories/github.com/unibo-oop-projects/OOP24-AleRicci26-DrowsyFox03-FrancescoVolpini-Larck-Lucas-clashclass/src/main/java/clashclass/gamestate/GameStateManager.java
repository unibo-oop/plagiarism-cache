package clashclass.gamestate;

import clashclass.engine.GameEngine;

/**
 * Represents the game state controller.
 */
public interface GameStateManager {
    /**
     * Sets the current active game state to "Player Village".
     */
    void setStatePlayerVillage();

    /**
     * Sets the current active game state to "Battle".
     */
    void setStateBattle();

    /**
     * Gets the game engine.
     *
     * @return the game engine
     */
    GameEngine getGameEngine();

    /**
     * Starts the game engine.
     */
    void startEngine();

    /**
     * Stops the game engine.
     */
    void stopEngine();
}
