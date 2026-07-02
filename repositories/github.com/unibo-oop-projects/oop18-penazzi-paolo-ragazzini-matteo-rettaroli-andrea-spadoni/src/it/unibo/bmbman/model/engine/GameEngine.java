package it.unibo.bmbman.model.engine;

/**
 * 
 * This interface represents the Game Engine. 
 */
public interface GameEngine {
    /**
     * Starts the Game Loop.
     */
    void startEngine();
    /**
     * Stops the Game.
     */
    void stopEngine();
    /**
     * Used to put in pause the game loop.
     * @param inPause the pause status 
     */
    void setPause(boolean inPause);
}

