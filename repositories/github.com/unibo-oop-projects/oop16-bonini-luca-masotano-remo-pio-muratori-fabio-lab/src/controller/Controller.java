package controller;

import java.util.Set;

import model.utils.Direction;
import view.View;

/**
 * Handles almost every aspect that allows the game to run such as starting and
 * stopping the game loop, initializing view and model, saving existing game run
 * and load it, handling inputs and closing the application.
 */
public interface Controller {

    /**
     * Saves the current game.
     */
    void save();

    /**
     * Resumes the game loop.
     */
    void resumeGameLoop();

    /**
     * Pauses the loop.
     */
    void stopGameLoop();

    /**
     * Starts a new game.
     */
    void newGame();

    /**
     * Closes the application.
     */
    void exit();

    /**
     * Loads a previous game.
     */
    void loadGame();

    /**
     * Starts gameView.
     */
    void initView();

    /**
     * Get the instance of the View of the game.
     * 
     * @return the game view
     */
    View getGameView();

    /**
     * Get the input directions to move the player.
     * 
     * @return a set of player's movement directions
     */
    Set<Direction> getObserverMovements();

    /**
     * Get the input directions to allow the player to shoot.
     * 
     * @return a set of player's shoot directions
     */
    Set<Direction> getObserverShoot();

    /**
     * Tells the view the game is over.
     */
    void gameOver();

    /**
     * Adds a direction key to the input movement set.
     * 
     * @param d
     *            input movement key
     */
    void addMovements(Direction d);

    /**
     * Adds a direction key to the input shoot set.
     * 
     * @param d
     *            input shoot key
     */
    void addShoots(Direction d);

    /**
     * Removes a direction key to the input movement set.
     * 
     * @param d
     *            input movement key
     */
    void removeMovements(Direction d);

    /**
     * Removes a direction key to the input shoot set.
     * 
     * @param d
     *            input shoot key
     */
    void removeShoots(Direction d);

    /**
     * @return if exists a saved game returns true otherwise false
     */
    boolean isASavedGame();

}
