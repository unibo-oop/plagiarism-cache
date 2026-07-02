package it.unibo.core;

import it.unibo.view.impl.StartGameView;
import javafx.application.Application;

/**
 * Main class.
 */
public final class ReckItRalph {

    private ReckItRalph() {
    }

    /**
     * Main method.
     * 
     * @param args
     */
    public static void main(final String[] args) {
        Application.launch(StartGameView.class);
    }
}
