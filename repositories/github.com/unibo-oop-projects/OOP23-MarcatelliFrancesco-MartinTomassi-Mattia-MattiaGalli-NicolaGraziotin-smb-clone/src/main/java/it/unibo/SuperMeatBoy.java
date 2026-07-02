package it.unibo;

import it.unibo.controller.factory.GameControllerFactoryImpl;

/**
 * The main class for the Super Meat Boy game.
 */
public final class SuperMeatBoy {

    /**
     * Private constructor to prevent instantiation from outside the class.
     */
    private SuperMeatBoy() {
    }

    /**
     * The main method to start the game.
     * 
     * @param args command-line arguments
     */
    public static void main(final String[] args) {
        new GameControllerFactoryImpl()
            .createGameController()
            .startWindow();
    }

}
