package it.tbt.engine.impl;

import it.tbt.view.api.GameViewFactory;
import it.tbt.view.javafx.JavaFxViewFactory;
import it.tbt.engine.api.Game;
import javafx.stage.Stage;

/**
 * Factory for Game objects, difference to be in the graphical framework it is used.
 */

public final class GameFactory {

    /**
     * Utility class, must not be instantiated.
     */
    private GameFactory() { }

    /**
     * @param stage
     * @return an instance of the Game interface, where all the object have been created by their default values.
     * In JavaFx graphical framework.
     */
    public static Game createJavaFxGame(final Stage stage) {
        final GameViewFactory viewFactory = new JavaFxViewFactory(stage);
        return new GameImpl(viewFactory);
    }

    /**
     * @return the Game object with a Java Swing graphical framework.
     */
    public static Game createJavaSwingGame() {
        return null;
    }

}
