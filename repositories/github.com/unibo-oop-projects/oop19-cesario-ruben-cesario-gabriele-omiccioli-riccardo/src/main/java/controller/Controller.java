package controller;

import model.entity.EntityID;

/**
 * Manages updates on the Model and the communication between Model and View.
 */
public interface Controller {

    /**
     * Starts the game within the stage, with the player using the specified name
     * and the specified model of spaceship to play with.
     * @param playerName : the specified name.
     * @param modelID : the specified model of ship.
     */
    void startStageGame(String playerName, EntityID modelID);

    /**
     * Returns true if the game has started. More specifically returns true if the
     * thread which handles the stage is running.
     * @return true if the game has started, false otherwise.
     */
    boolean isGameRunning();

    /**
     * Ends the current game.
     */
    void endGame();

    /**
     * Returns true if the game is paused.
     * @return true if the game is paused, false otherwise.
     */
    boolean isPaused();

    /**
     * Pauses the game.
     */
    void pauseGame();

    /**
     * Resumes the game.
     */
    void resumeGame();

}
