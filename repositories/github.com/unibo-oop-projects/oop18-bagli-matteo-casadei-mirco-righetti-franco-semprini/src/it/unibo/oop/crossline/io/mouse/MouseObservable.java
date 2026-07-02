package it.unibo.oop.crossline.io.mouse;

import it.unibo.oop.crossline.io.mouse.MouseHandler.MODE;

/**
 * Observable interface for keyboard input handle.
 */
public interface MouseObservable {

    /**
     * Add observer to observers list.
     * 
     * @param observer the observer
     * @return true if all is Ok, false when something is gone wrong
     */
    boolean registerObserver(MouseObserver observer);

    /**
     * Notify all observers in observers list.
     * 
     * @param mode the mode we want to use
     * @return true if all is Ok, false when something is gone wrong
     */
    boolean notifyObserver(MODE mode);

    /**
     * Remove observer to observers list.
     * 
     * @param observer the observer
     * @return true if all is Ok, false when something is gone wrong
     */
    boolean removeObserver(MouseObserver observer);

    /**
     * Update observers with keyCode.
     * 
     * @param x the x coordinate
     * @param y the y coordinate
     * @param dragger the dragger boolean
     * @param keyCode the keyCode
     * @return true if all is Ok, false when something is gone wrong
     */
    boolean updateButtonPressed(int x, int y, boolean dragger, int keyCode);

    /**
     * Update observers with movement coordinates.
     * 
     * @param x the x coordinate
     * @param y the y coordinate
     * @return true if all is Ok, false when something is gone wrong
     */
    boolean updateMoved(int x, int y);

    /**
     * Update observers with movement coordinates (dragger).
     * 
     * @param x the x coordinate
     * @param y the y coordinate
     * @return true if all is Ok, false when something is gone wrong
     */
    boolean updateMovedDragger(int x, int y);

    /**
     * Called whean mouse has scrolled.
     * 
     * @param scrolledAmount the scrolledAmount
     * @return true if all is Ok, false when something is gone wrong
     */
    boolean updateScrolled(int scrolledAmount);
}
