package it.unibo.jurassiko.core.api;

import it.unibo.jurassiko.model.player.api.Player;

/**
 * Manage the Game loop.
 */
public interface GameEngine {

    /**
     * Start game loop.
     */
    void startGameLoop();

    /**
     * Get the Phase of the game.
     * 
     * @return Phase of the Game
     */
    GamePhase.Phase getGamePhase();

    /**
     * Set the phase of the Game.
     * 
     * @param phase phase to set
     */
    void setGamePhase(GamePhase.Phase phase);

    /**
     * Get the Remaining dino to place.
     * 
     * @return the remaining amount of dino to place
     */
    int getRemainingDinoToPlace();

    /**
     * Get the current player Turn.
     * 
     * @return Current Player Turn
     */
    Player getCurrentPlayerTurn();

    /**
     * Get the first turn.
     * 
     * @return true if its the first turn, false otherwise
     */
    boolean isFirstTurn();

    /**
     * End the Turn of the Player.
     */
    void endTurn();

}
