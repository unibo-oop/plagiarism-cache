package it.unibo.view.window.factory;

import it.unibo.controller.GameController;
import it.unibo.view.window.GameWindow;

/**
 * The GameWindowFactory interface is a Factory interface for creating instances of GameWindow.
 * It provides methods to create both Swing and JavaFX implementations of Game Windows.
 */
public interface GameWindowFactory {

    /**
     * Creates a new instance of GameWindow with Swing implementation.
     *
     * @param controller The GameController associated with the game window.
     * @return A new instance of GameWindow using Swing.
     */
    GameWindow createSwingGameWindow(GameController controller);

}

