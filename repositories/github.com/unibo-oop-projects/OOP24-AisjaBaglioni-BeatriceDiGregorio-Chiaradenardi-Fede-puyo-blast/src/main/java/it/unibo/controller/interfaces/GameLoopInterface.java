package it.unibo.controller.interfaces;

import it.unibo.view.GameView;

/**
 * This interface defines the structure of a game loop, which manages the execution of the game logic 
 * by continuously updating game components at a fixed rate.
 * It allows adding and removing tick listeners, starting and stopping the game loop, 
 * and associating a game view with the loop.
 */
public interface GameLoopInterface {
    
    /**
     * Starts the game loop. 
     * Once started, the loop continuously triggers updates (ticks) at a defined interval.
     */
    public void startGame();
    
    /**
     * Stops the game loop. 
     * Once stopped, no further game updates (ticks) will be processed until restarted.
     */
    public void stopGame();
    
    /**
     * Registers a tick listener that will be notified at every game tick.
     * Tick listeners typically contain logic that needs to be executed on each update cycle.
     *
     * @param tickListener the listener to be added to the game loop
     */
    public void addTickListener(TickListenerInterface tickListener);

    /**
     * Removes a previously registered tick listener. 
     * This prevents the listener from receiving further tick notifications.
     *
     * @param tickListener the listener to be removed from the game loop
     */
    public void removeTickListener(TickListenerInterface tickListener);
    
    /**
     * Associates a GameView instance with the game loop.
     * This allows the game loop to update the graphical representation of the game state.
     *
     * @param gameView the GameView instance to be linked with the game loop
     */
    public void setGameView(GameView gameView);

}
