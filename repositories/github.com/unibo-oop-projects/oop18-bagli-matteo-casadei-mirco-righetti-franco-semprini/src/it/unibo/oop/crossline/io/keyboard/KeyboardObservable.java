package it.unibo.oop.crossline.io.keyboard;

/**
 * Observable interface for keyboard input handle.
 */
public interface KeyboardObservable {
    /**
     * Add observer to observers list.
     * 
     * @param observer the observer
     * @return true if all is Ok, false when something is gone wrong
     */
    boolean registerObserver(KeyboardObserver observer);

    /**
     * Notify all observers in observers list.
     * 
     * @return true if all is Ok, false when something is gone wrong
     */
    boolean notifyObserver();

    /**
     * Remove observer to observers list.
     * 
     * @param observer the observer
     * @return true if all is Ok, false when something is gone wrong
     */
    boolean removeObserver(KeyboardObserver observer);

    /**
     * Update observers with keyCode.
     * 
     * @param keyCode the keyCode
     * @return true if all is Ok, false when something is gone wrong
     */
    boolean updateKeyPressed(int keyCode);
}
