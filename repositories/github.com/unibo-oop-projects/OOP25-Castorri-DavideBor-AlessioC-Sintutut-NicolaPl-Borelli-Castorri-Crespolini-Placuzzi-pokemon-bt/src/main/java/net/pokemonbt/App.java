package net.pokemonbt;

import javafx.application.Application;
import net.pokemonbt.controller.resources.LoadoutManager;
import net.pokemonbt.view.ViewManager;

/**
 *
 */
public final class App {
    private App() { }

    /**
     * Main application entry-point.
     * Should be started with "Launcher" class.
     *
     * @param args passed to JavaFX.
     */
    public static void main(final String[] args) {
        LoadoutManager.loadSettings();

        /* Calling init methods, then initialize app cycle */
        Application.launch(ViewManager.class, args);
    }
}
