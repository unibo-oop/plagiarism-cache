package it.unibo.controller.interfaces;

/**
 * The {@code TickListenerInterface} is used to define an object that listens to game ticks.
 * Implementing this interface allows objects to perform actions on each tick of the game loop.
 * The {@code onTick} method is called at regular intervals during the game loop.
 */
public interface TickListenerInterface {

    /**
     * This method is called on every tick of the game loop.
     * Implementing classes should define the actions to be executed each time the game is updated.
     */
    public void onTick();
}
