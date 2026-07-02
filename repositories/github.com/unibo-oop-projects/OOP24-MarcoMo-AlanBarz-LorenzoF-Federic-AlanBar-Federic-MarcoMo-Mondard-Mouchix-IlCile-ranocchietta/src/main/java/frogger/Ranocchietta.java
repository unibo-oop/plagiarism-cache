package frogger;

import frogger.controller.MainControllerImpl;

/**
 * Entry point for the Ranocchietta game application.
 */
public final class Ranocchietta {

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private Ranocchietta() { }

    /**
     * Main method. Launches the application by creating the main controller and showing the initial panel.
     *
     * @param args the command-line arguments (not used)
     */
    public static void main(final String[] args) {
        final MainControllerImpl mainController = new MainControllerImpl();
        mainController.mainLoop();
    }
}
