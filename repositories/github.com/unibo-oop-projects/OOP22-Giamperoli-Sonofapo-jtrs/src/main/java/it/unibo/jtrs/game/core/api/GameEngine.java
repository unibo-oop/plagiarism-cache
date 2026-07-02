package it.unibo.jtrs.game.core.api;

/**
 * An interface modelling a game engine. The engine must create a game loop and run
 * until the application has finished its operations.
 */
public interface GameEngine {

    /**
     * Starts the engine gameloop.
     */
    void mainLoop();
}
