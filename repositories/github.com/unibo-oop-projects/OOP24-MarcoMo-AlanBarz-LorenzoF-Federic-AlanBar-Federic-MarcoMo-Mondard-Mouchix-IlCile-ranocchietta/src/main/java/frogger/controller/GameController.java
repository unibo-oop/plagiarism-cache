package frogger.controller;

import java.awt.event.KeyListener;

import frogger.common.input.InputController;
import frogger.model.interfaces.Game;

/**
 * Controller interface for managing the main game logic, player state, and input.
 * Extends {@link Controller} to provide additional game-specific methods.
 */
public interface GameController extends Controller {

    /**
     * Returns the input controller responsible for handling player commands.
     *
     * @return the input controller
     */
    InputController getInputController();

    /**
     * Returns the key listener for handling keyboard input.
     *
     * @return the key listener
     */
    KeyListener getKeyListener();

    /**
     * Returns the current game instance.
     *
     * @return the game
     */
    Game getGame();

    /**
     * Returns the number of coins the player currently has.
     *
     * @return the number of coins
     */
    int getCoins();

    /**
     * Sets the number of coins the player has.
     *
     * @param coins the new coin amount
     */
    void setCoins(int coins);

    /**
     * Starts a new game session, resetting all relevant state.
     */
    void newGame();

    /**
     * Returns the identifier or path of the currently equipped skin.
     *
     * @return the skin identifier
     */
    String getSkin();

    /**
     * Sets the currently equipped skin.
     *
     * @param skin the skin identifier or path
     */
    void setSkin(String skin);
}
