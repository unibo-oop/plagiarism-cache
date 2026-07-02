package it.unibo.oop.crossline.io.mouse;

/**
 * Observer interface for Mouse button.
 */
public interface MouseObserver {
    /**
     * This method understand which key is pressed. And execute the main function of
     * is mapped action
     * 
     * @param x       the x coordinate
     * @param y       the y coordinate
     * @param dragger the dragger boolean
     * @param keyCode the keyCode
     * @return true if all is Ok, false when something is gone wrong
     */
    boolean update(int x, int y, boolean dragger, int keyCode);

    /**
     * This method understand when mouse change position.
     * 
     * @param x the x coordinate
     * @param y the y coordinate
     * @return true if all is Ok, false when something is gone wrong
     */
    boolean updatePosition(int x, int y);

    /**
     * Called each time the finger moves while in the down state.
     * 
     * @param x the x coordinate, origin is in the upper left corner
     * @param y the y coordinate, origin is in the upper left corner
     * @return true if all is Ok, false when something is gone wrong
     */
    boolean updatePositionDragged(int x, int y);

    /**
     * Called whean mouse has scrolled.
     * 
     * @param scrolledAmount the scrolledAmount
     * @return true if all is Ok, false when something is gone wrong
     */
    boolean updateScrolled(int scrolledAmount);
}
