package it.unibo.michelito.view.gameview.panel.api;

import java.awt.event.KeyListener;
import java.util.Set;

/**
 * Interface that models an InputHandler.
 */
public interface InputHandler extends KeyListener {
    /**
     * Method that returns the keys pressed.
     * @return the keys pressed.
     */
    Set<Integer> keysPressed();
}
