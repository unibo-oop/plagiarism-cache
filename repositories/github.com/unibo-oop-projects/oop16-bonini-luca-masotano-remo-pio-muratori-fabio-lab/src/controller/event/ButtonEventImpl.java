package controller.event;

/**
 * Defines the wrapper of the ButtonEvent generated in the View.
 */
public class ButtonEventImpl extends StandardEvent implements ButtonEvent {

    private final String state;

    /**
     * Constructs a new instance of ButtonEventImpl.
     * 
     * @param message
     *            which button is pressed
     * @param state
     *            the view's state
     */
    public ButtonEventImpl(final String message, final String state) {
        super(message);
        this.state = state;
    }

    @Override
    public String getState() {
        return state;
    }
}
