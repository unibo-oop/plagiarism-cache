package it.unibo.aknightstale;

import it.unibo.aknightstale.views.JavaFXApp;
import javafx.application.Application;

/**
 * Main application entry-point.
 */
public final class App {
    /** App name. */
    public static final String APP_NAME = "A Knight's Tale";
    /** App version. */
    public static final String APP_VERSION = "0.1.0";
    /** App package. */
    public static final String APP_PACKAGE = "it.unibo.aknightstale";

    private App() {
        // Main entry-point.
    }

    public static void main(final String[] args) {
        Application.launch(JavaFXApp.class, args);
    }
}
