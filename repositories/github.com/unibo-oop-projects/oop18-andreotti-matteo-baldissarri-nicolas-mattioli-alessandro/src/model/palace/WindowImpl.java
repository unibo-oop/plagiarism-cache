package model.palace;

import java.util.Random;

import model.entities.Position;

/**
 * 
 * Implements the Window.
 *
 */
public class WindowImpl implements Window {

    private StatusOfWindow status = StatusOfWindow.OPEN;

    private Position windowPosition;

    /**
     * 
     * Windows builder.
     * 
     * @param p The position of a Window.
     * 
     */
    public WindowImpl(final Position p) {
        this.windowPosition = p;
    }

    private StatusOfWindow nextStatus() {
        return status.equals(StatusOfWindow.OPEN) ? StatusOfWindow.PARTIAL_CLOSE
                : status.equals(StatusOfWindow.PARTIAL_CLOSE) ? StatusOfWindow.CLOSE : StatusOfWindow.OPEN;
    }

    /**
     * 
     * Change the status of a window with its next status.
     * 
     */
    public void changeStatus() {
        this.status = this.nextStatus();
    }

    /**
     * 
     * @return The current status of one window.
     * 
     */
    public StatusOfWindow getStatus() {
        return this.status;
    }

    /**
     * 
     * Set the status of another window when add a new floor.
     * @param windowStatus The status of one window.
     * 
     */
    public void setStatus(final StatusOfWindow windowStatus) {
        this.status = windowStatus;
    }

    /**
     * 
     * @return The position of Window.
     * 
     */
    public Position getPosition() {
        return this.windowPosition;
    }

    /**
     * 
     * @return A random status of windows for last floor created.
     * 
     */
    public static StatusOfWindow getRandomStatus() {
        Random r = new Random();
        switch (r.nextInt(3) + 1) {
        case 1: 
        return StatusOfWindow.OPEN;
        case 2: 
        return StatusOfWindow.PARTIAL_CLOSE;
        case 3: 
        return StatusOfWindow.CLOSE;
        default:
        return null;
        }
    }
}
