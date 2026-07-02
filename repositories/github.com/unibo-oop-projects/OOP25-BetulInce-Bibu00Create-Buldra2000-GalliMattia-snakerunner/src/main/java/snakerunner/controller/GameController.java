package snakerunner.controller;

import snakerunner.graphics.hud.BaseHUD;

/**
 * Managing the game sessione lifecycle,
 * including starting, pausing, resuming, updating game loop,
 * loading level and configuring HUD components.
 */
public interface GameController {

    /**
     * Starts the game session, inizializing the timer, loading current level,
     * and starting game loop.
     */
    void start();

    /**
     * Pause the game if it's currently running, stop the gameloop timer.
     */
    void pause();

    /**
     * Set the HUD components for view.
     * 
     * @param timerView for visualization the timer.
     * @param scoreView for visualization the score.
     * @param levelView for visualization the level.
     * @param lifeView for visualization the lives.
     */
    void setHUD(BaseHUD timerView, BaseHUD scoreView, BaseHUD levelView, BaseHUD lifeView);

    /**
     * Resume game.
     */
    void resume();

    /**
     * Advances the game by one tick: updates the model,
     * decrements the timer, checks for game over and refreshes the view.
     */
    void updateGame();

    /**
     * Load level from file.
     * 
     * @param filepath path file levels.
     */
    void loadLevelFromFile(String filepath);

    /**
     * Moves the snake up.
     */
    void moveUp();

    /**
     * Moves the snake down.
     */
    void moveDown();

    /**
     * Moves the snake left.
     */
    void moveLeft();

    /**
     * Moves the snake right.
     */
    void moveRight();
}
