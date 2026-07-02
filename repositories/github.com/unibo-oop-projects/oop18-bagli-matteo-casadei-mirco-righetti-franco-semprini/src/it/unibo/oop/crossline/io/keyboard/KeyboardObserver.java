package it.unibo.oop.crossline.io.keyboard;

/**
 * Observer interface for Keyboard button.
 */
public interface KeyboardObserver {
    /**
     * This method understand which key is pressed. And execute the main function of
     * is mapped action
     * 
     * @param keyCode the keyCode
     * @return true if all is Ok, false when something is gone wrong
     */
    boolean update(int keyCode);
}
