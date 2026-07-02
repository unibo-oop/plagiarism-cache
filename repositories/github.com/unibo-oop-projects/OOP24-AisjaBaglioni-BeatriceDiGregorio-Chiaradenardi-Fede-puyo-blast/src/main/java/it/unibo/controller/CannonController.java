package it.unibo.controller;

import it.unibo.controller.interfaces.TickListenerInterface;
import it.unibo.model.CannonModel;
import it.unibo.model.KeyboardModel;

import java.awt.event.KeyEvent;

/**
 * The CannonController class is responsible for handling user input
 * to control the cannon's movement and aiming direction.
 * It listens for keyboard events and updates the cannon model accordingly.
 */

public class CannonController implements TickListenerInterface {
    /**
     * The model representing the cannon.
     */
    private final CannonModel cannonModel;
    /**
     * The model handling keyboard input.
     */
    private final KeyboardModel keyboardModel;

    /**
     * Constructor for initializing the CannonController.
     * 
     * @param cannonModel   the model representing the cannon.
     * @param keyboardModel the model handling keyboard input.
     * @param progressBar   the progress bar controller, it is not directly used in
     *                      this class,
     *                      but it is linked to the cannon.
     */
    public CannonController(CannonModel cannonModel, KeyboardModel keyboardModel, ProgressBarController progressBar) {
        this.cannonModel = cannonModel;
        this.keyboardModel = keyboardModel;
    }

    /**
     * Called on each game tick to process user input and update the cannon's state.
     * Moves the cannon left or right and adjusts its aim based on key presses.
     */
    @Override
    public void onTick() {
        /**
         * Move cannon left or right based on arrow key input.
         */
        if (this.keyboardModel.isKeyPressed(KeyEvent.VK_RIGHT)) {
            cannonModel.moveRight();
        } else if (this.keyboardModel.isKeyPressed(KeyEvent.VK_LEFT)) {
            cannonModel.moveLeft();
        }
        /**
         * Adjust cannon aim up o down based on arrow key input.
         */
        if (this.keyboardModel.isKeyPressed(KeyEvent.VK_UP)) {
            cannonModel.aimUp();
        } else if (this.keyboardModel.isKeyPressed(KeyEvent.VK_DOWN)) {
            cannonModel.aimDown();
        }
    }
}
