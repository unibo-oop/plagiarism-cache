package it.unibo.javajump.view;

import it.unibo.javajump.controller.input.InputManager;


/**
 * The interface that describes a Game frame.
 */
public interface GameFrame {
    /**
     * Sets up the game frame with the necessary information and dependencies.
     *
     * @param inputManager the input manager
     * @param height       the height
     * @param width        the width
     * @param view         the view
     * @param title        the title
     */
    void setUp(InputManager inputManager, int height, int width, MainGameView view, String title);

    /**
     * Closes the game safely.
     */
    void closeGame();
}
