package pvz;

import pvz.controller.maincontroller.impl.MainControllerImpl;

/**
 * Entry point for Plants vs Zombies OOP game.
 */
public final class PvZ {
    /**
     * Private constructor to prevent instantiation.
     */
    private PvZ() { }

    /**
     * Main method. Starts the application.
     *
     * @param args command-line arguments (unused)
     */
    public static void main(final String[] args) {
        new MainControllerImpl().start();
    }
}
