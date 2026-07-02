package it.unibo.plantsfarm;

import it.unibo.plantsfarm.controller.loader.GameLoader;

/**
 * Main application entry point for PlantsFarm.
 */
public final class Main {

    private Main() {
        //Utility class constructor
    }

    /**
     * Starts the application.
     *
     * @param args Command line arguments.
     */
    public static void main(final String[] args) {
        final GameLoader gameLoader = new GameLoader();
        gameLoader.initializeGame();
    }
}
