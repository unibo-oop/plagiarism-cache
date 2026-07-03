package controller.event;

/**
 * Represents the keys pressed or released. This interface is meant to be a wrapper
 * of the KeyEvent generated in the View.
 */
public interface KeyEvent extends Event {

    /**
     * Get the type if the KeyEvent.
     * 
     * @return if the key is pressed or released
     */
    KeyEventType getType();

    /**
     * Get the view state.
     * 
     * @return view state when the event is generated
     */
    String getState();

    /**
     * The two different Types of event handled by an Observer.
     */
    enum KeyEventType {
        KEY_PRESSED, KEY_RELEASED;
    }
}
