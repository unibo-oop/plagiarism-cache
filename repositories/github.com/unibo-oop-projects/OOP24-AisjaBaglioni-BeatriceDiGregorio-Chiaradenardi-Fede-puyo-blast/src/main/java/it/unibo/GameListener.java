package it.unibo;

import java.util.EventListener;

/**
 * The GameListener interface allows different parts of the program, mostly
 * controllers, to listen for and respond to key game events like
 * starting a game or returning to the main menu. Classes implementing this
 * interface will define how to handle these events when they're fired.
 */
public interface GameListener extends EventListener {
    /**
     * This method is called when an event occurs that should trigger
     * the start of the game. Implementing classes will provide the specific logic
     * for starting the game when this event is received.
     * 
     * @param e event
     */
    void startGame(GameEvent e);

    /**
     * This method is intended to be called when an event occurs that should
     * transition the game back to the main menu.
     * 
     * @param e event
     */
    void goToMainMenu(GameEvent e);
}