package model.palace;

import model.entities.Position;

/**
 * 
 * Model a Window of a Floor.
 *
 */
public interface Window {

    /**
     * 
     * Explain the status of a Window.
     *
     */
    enum StatusOfWindow {
        CLOSE, PARTIAL_CLOSE, OPEN
    }

    /**
     * 
     * @return The current status of one window.
     * 
     */
    StatusOfWindow getStatus();

    /**
     * 
     * Change the status of a window with its next status.
     * 
     */
    void changeStatus();

    /**
     * 
     * @return The position of Window.
     * 
     */
    Position getPosition();

    /**
     * 
     * Set the status of another window when add a new floor.
     * @param windowStatus The status of one window.
     * 
     */
    void setStatus(StatusOfWindow windowStatus);
}
