package it.unibo.javajump.controller.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Interface for the input manager, that will manage the input of the user and pass it to the game model.
 */
public interface InputManager extends KeyListener {
    /**
     * {@inheritDoc}
     */
    @Override
    void keyPressed(KeyEvent e);

    /**
     * {@inheritDoc}
     */
    @Override
    void keyReleased(KeyEvent e);

    /**
     * {@inheritDoc}
     */
    @Override
    void keyTyped(KeyEvent e);

    /**
     * Getter for the queue of actions to be processed.
     *
     * @return the queue of actions to be processed
     */
    GameAction getAction();

    /**
     * Getter for the horizontal direction of the player.
     *
     * @return the current horizontal direction of the player based on the key pressed
     */
    int getHorizontalDirection();
}
