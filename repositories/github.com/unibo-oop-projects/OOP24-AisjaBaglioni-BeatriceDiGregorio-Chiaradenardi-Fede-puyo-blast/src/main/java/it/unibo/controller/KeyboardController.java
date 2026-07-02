package it.unibo.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import it.unibo.model.KeyboardModel;

/**
 * The KeyboardController class translates player inputs (keyboard keys) into
 * action.
 * It listens for key events and updates the KeyboardModel accordingly.
 */
public class KeyboardController implements KeyListener {
    /**
     * The model managing keyboard input states.
     */
    private final KeyboardModel keyboardModel;

    /**
     * Constructor for initializing the KeyboardController.
     * 
     * @param keyboardModel the model handling keyboard input.
     */
    public KeyboardController(KeyboardModel keyboardModel) {
        this.keyboardModel = keyboardModel;
    }

    /**
     * Handles key press events by registering the key as pressed.
     * 
     * @param e the KeyEvent containing the key code.
     */
    @Override
    final public void keyPressed(KeyEvent e) {
        this.keyboardModel.keyDown(e.getKeyCode());

    }

    /**
     * Handles key release events by registering the key as released.
     * 
     * @param e the KeyEvent containing the key code.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        this.keyboardModel.keyUp(e.getKeyCode());
    }

    /**
     * Handles typed key events (not used in this implementation).
     * 
     * @param e the KeyEvent.
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }
}