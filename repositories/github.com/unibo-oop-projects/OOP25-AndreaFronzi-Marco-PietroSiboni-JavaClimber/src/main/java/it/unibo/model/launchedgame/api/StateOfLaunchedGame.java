package it.unibo.model.launchedgame.api;

/**
 * Interface representing a specific state of a launched game.
 * States handle specific logic for different phases like initialization, running, pause, and end.
 */
public interface StateOfLaunchedGame {

    /**
     * Executes the logic specific to this state during the game loop.
     * 
     * @param dt The time delta since the last execution.
     */
    void execute(double dt);

    /**
     * Logic to be executed when the game enters this state.
     */
    void onEnter();
}
