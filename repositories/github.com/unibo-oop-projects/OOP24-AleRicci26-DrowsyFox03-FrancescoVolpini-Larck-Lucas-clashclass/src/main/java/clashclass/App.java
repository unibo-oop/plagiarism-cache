package clashclass;

import javafx.application.Application;

/** Main application entry-point's class. */

public final class App {
    private App() { }

    /**
     * Main application entry-point.
     *
     * @param args passed to JavaFX.
     */
    public static void main(final String[] args) {
        Application.launch(JavaFXApp.class, args);
    }
}

