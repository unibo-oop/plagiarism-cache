package it.unibo.controller.factory;

import it.unibo.controller.GameController;

/**
 * The GameControllerFactory interface defines a contract for creating instances of GameController.
 */
public interface GameControllerFactory {

    /**
     * Creates a new instance of GameController.
     *
     * @return a new instance of GameController.
     */
    GameController createGameController();

}

