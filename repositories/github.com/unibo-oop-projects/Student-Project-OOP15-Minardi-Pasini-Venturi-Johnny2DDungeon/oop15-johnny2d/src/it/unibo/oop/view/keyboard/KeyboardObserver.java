package it.unibo.oop.view.keyboard;

/**
 * Interface for objects used in response to a keyboard event.
 */
public interface KeyboardObserver {

    /**
     * @param keyCode
     *            VK_Code of the key.
     * @param eventID
     *            id that identifies if key has been Pressed/Released or Typed.
     */
    void keyAction(int keyCode, int eventID);
}
