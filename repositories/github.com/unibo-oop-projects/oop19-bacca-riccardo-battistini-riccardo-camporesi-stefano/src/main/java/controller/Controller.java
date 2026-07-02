package controller;

/**
 * Interface describing the Controller of the application.
 */
public interface Controller {

    /**
     * Loads the simulation taking parameters from ViewParameters and *Data classes.
     */
    void loadSimulation();

    /**
     * Starts the simulation, thus launching the thread of the main loop.
     */
    void startSimulation();

    /**
     * Pauses the simulation, interrupting the thread of the main loop.
     */
    void pauseSimulation();

}
