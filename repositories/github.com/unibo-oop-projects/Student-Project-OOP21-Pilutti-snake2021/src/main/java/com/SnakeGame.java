package main.java.com;

import main.java.com.controller.GameController;

/**
 * This is the class used to launch the game.
 *
 */
public final class SnakeGame {

    private SnakeGame() {
    }

    /**
     * The main method that starts the game.
     * @param args no expected arguments.
     */
    public static void main(final String[] args) {
        new GameController().mainLoop();
    }
}
