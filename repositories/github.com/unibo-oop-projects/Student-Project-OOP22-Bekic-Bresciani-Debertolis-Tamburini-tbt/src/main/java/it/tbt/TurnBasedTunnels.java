package it.tbt;

import it.tbt.engine.impl.JavaFxLauncher;
import javafx.application.Application;

/**
 * Application entry point class.
 */
public final class TurnBasedTunnels {

    /**
     * Entry-point class not instantiable.
     */
    private TurnBasedTunnels() { }

    /**
     * Application entry point.
     * @param args
     */

    public static void main(final String[] args) {
        Application.launch(JavaFxLauncher.class, args);
    }

}
