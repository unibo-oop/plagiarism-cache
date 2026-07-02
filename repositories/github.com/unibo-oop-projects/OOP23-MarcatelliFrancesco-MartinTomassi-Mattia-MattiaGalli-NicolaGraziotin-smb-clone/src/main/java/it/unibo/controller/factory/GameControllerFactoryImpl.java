package it.unibo.controller.factory;

import it.unibo.controller.GameController;
import it.unibo.controller.GameControllerImpl;

/**
 * The GameControllerFactoryImpl class is an implementation of the GameControllerFactory interface.
 * It provides a concrete implementation of the factory method createGameController(), instantiating
 * GameController objects.
 */
public class GameControllerFactoryImpl implements GameControllerFactory {

    @Override
    public final GameController createGameController() {
        return new GameControllerImpl();
    }

}
