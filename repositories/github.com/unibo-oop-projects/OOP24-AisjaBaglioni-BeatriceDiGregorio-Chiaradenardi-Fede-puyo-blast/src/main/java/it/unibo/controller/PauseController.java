package it.unibo.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import it.unibo.model.PauseModel;
import it.unibo.controller.interfaces.PauseControllerInterface;

/**
 * The {@code PauseController} class listens for key events and toggles the game's pause state 
 * when the appropriate key is pressed.
 * It implements both {@link KeyListener} to capture keyboard events and 
 * {@link PauseControllerInterface} to manage the game's pause functionality.
 * 
 * This controller is responsible for checking if the player has pressed the 'P' key 
 * to pause or unpause the game.
 */
public class PauseController implements KeyListener, PauseControllerInterface {

    /** The model that stores the current pause state of the game. */
    private final PauseModel model;

    /**
     * Constructs a {@code PauseController} instance that controls the given {@code PauseModel}.
     * 
     * @param model the {@code PauseModel} instance used to manage the pause state of the game
     */
    public PauseController(PauseModel model) {
        this.model = model;
    }

    /**
     * Toggles the pause state of the game.
     * This method is called when the 'P' key is pressed by the user.
     * If the game is currently paused, it will be unpaused, and vice versa.
     */
    @Override
    public void togglePause() {
        this.model.togglePause();
    }

    /**
     * Called when a key is typed. This method does not perform any action in this implementation.
     * 
     * @param e the {@code KeyEvent} containing information about the key typed
     */
    @Override
    public void keyTyped(KeyEvent e) {
        /* No action taken on key typed*/
    }

    /**
     * Called when a key is pressed. It listens for the 'P' key press and toggles the pause state.
     * 
     * @param e the {@code KeyEvent} containing information about the key pressed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        /* If the 'P' key is pressed, toggle the pause state*/
        if (e.getKeyCode() == KeyEvent.VK_P) {
            togglePause();
        }
    }

    /**
     * Called when a key is released. This method does not perform any action in this implementation.
     * 
     * @param e the {@code KeyEvent} containing information about the key released
     */
    @Override
    public void keyReleased(KeyEvent e) {
        /*No action taken on key released*/
    }
}
