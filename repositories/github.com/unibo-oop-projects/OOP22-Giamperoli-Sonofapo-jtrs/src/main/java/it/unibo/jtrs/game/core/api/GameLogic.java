package it.unibo.jtrs.game.core.api;

import it.unibo.jtrs.model.api.GameModel.GameState;

/**
 * An interface modelling how the game should respond to human interaction.
 * The game should be able to run forever until the match is lost and change
 * its state based on how much time has passed.
 */
public interface GameLogic {

    /**
     * Performs operation based on the flow of time.
     */
    void timeUpdate();

    /**
     * Requests an interrupt to the game logic. This should be used to manage
     * tasks like pause, resume or similar.
     */
    void requestInterrupt();

    /**
     * Returns the current game state.
     * 
     * @return the game state
     */
    GameState getState();

}
