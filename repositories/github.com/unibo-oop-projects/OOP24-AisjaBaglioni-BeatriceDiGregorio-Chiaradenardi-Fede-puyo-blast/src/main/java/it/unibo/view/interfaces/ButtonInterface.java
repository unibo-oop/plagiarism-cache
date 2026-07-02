package it.unibo.view.interfaces;

import it.unibo.model.Rectangle;

/**
 * This interface is used to find the area occupied
 * by a button on the screen, approximizing it as a {@link Rectangle}.
 */
public interface ButtonInterface {
    /**
     * A method to get the screen area of the button.
     * 
     * @return a {@link Rectangle}
     */
    Rectangle getArea();
}
