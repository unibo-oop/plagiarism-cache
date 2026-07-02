package it.unibo.javajump.controller;

/**
 * Interface for the GameInitializer, which sets the main dependency injections and effectively starts the game loop.
 */
public interface GameInitializer {
    /**
     * Method to be called in "Main", used to link all parts of the game (model-view-controller) & start the game loop.
     */
    void initialize();
}
