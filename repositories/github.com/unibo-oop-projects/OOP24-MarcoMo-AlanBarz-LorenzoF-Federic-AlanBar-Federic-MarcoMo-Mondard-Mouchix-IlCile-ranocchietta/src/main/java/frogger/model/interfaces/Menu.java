package frogger.model.interfaces;

import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Represents a menu interface in the application, providing methods for
 * interacting with menu buttons and handling mouse events.
 */
public interface Menu {

    /**
     * Gets the list of buttons in the menu.
     *
     * @return a list of buttons
     */
    List<Button> getButtonList();

    /**
     * Updates the state of the menu.
     */
    void update();

    /**
     * Handles mousePressed events on the menu.
     *
     * @param e the mouse event
     */
    void mousePressed(MouseEvent e);

    /**
     * Handles mouseReleased events on the menu.
     *
     * @param e the mouse event
     */
    void mouseReleased(MouseEvent e);

    /**
     * Handles mouse movement events over the menu.
     *
     * @param e the mouse event
     */
    void mouseMoved(MouseEvent e);

    /**
     * Checks if the mouse event occurred within the bounds of a specific button.
     *
     * @param e      the mouse event
     * @param button the button to check
     * @return true if the mouse event is within the button's bounds, false otherwise
     */
    boolean isIn(MouseEvent e, Button button);
}
