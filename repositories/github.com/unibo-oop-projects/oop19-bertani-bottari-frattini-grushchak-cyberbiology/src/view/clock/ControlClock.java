package view.clock;

import controller.TurnOver;

/**
 * Control interface that manages the connection of the world, and of the clock.
 *
 */
public interface ControlClock {

    /**
     * method that starts the mode and the various components, synchronizing the interface and world 
     * updates at regular time intervals, is stopped when the stop button is pressed.
     * @param t the object that allows you to update the world
     */
    void start(TurnOver t);
}
