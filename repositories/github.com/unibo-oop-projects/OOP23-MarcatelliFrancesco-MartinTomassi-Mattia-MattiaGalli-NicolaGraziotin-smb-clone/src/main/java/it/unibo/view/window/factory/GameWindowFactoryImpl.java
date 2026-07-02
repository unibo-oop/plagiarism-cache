package it.unibo.view.window.factory;

import it.unibo.controller.GameController;
import it.unibo.view.window.GameWindow;
import it.unibo.view.window.GameWindowSwing;

/**
 * The GameWindowFactoryImpl class implements the GameWindowFactory interface
 * and provides methods to create instances of GameWindow.
 */
public class GameWindowFactoryImpl implements GameWindowFactory {

    @Override
    public final GameWindow createSwingGameWindow(final GameController controller) {
        return new GameWindowSwing(controller);
    }

}
