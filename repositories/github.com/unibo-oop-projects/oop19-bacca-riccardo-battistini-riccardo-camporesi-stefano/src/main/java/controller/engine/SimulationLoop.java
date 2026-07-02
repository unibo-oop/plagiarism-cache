package controller.engine;

public interface SimulationLoop {

    /**
     * Starts the game loop.
     */
    void startLoop();

    /**
     * Temporarily stops the loop until resume() is called.
     */
    void pauseLoop();

    /**
     * Makes the loop restart if it was previously paused.
     */
    void resumeLoop();

}
