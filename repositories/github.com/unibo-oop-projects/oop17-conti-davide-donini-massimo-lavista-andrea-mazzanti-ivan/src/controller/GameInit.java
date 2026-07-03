package controller;

import javafx.application.Application;
import view.ViewImpl;

/**
 * 
 * Provides methods to initialize the Game.
 *
 */
public final class GameInit {

    private GameInit() {
    }

    /**
     * 
     * The main of the application.
     * 
     * @param args
     *            arguments.
     */
    public static void main(final String[] args) {
        Application.launch(ViewImpl.class);
    }

}
