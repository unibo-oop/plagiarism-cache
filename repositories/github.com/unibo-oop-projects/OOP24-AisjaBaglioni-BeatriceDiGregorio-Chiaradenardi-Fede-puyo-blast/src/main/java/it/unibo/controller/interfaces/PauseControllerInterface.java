package it.unibo.controller.interfaces;

/**
 * The {@code PauseControllerInterface} defines the method for controlling the pause state of the game.
 * It allows the implementation of a controller that can toggle the game's pause state.
 */
public interface PauseControllerInterface {

    /**
     * Toggles the pause state of the game.
     * If the game is currently paused, it will be unpaused, and if it is unpaused, it will be paused.
     */
    void togglePause();
}
