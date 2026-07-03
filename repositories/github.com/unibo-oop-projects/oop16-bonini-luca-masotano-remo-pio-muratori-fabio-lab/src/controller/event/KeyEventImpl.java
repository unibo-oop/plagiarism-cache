package controller.event;

/**
 * Defines the wrapper of the KeyEvent generated in the View.
 */
public class KeyEventImpl extends StandardEvent implements KeyEvent {

    private final KeyEventType type;
    private final String state;

    /**
     * Constructs a new instance of KeyEventImpl.
     * 
     * @param message
     *            which key is pressed
     * @param type
     *            key pressed or released
     * @param state
     *            the view's state
     */
    public KeyEventImpl(final String message, final KeyEventType type, final String state) {
        super(message);
        this.type = type;
        this.state = state;
    }

    @Override
    public KeyEventType getType() {
        return type;
    }

    @Override
    public String getState() {
        return state;
    }
}
