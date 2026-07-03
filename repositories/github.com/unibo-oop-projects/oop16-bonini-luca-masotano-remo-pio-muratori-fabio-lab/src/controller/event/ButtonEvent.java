package controller.event;

/**
 * Represents the button pressed event.
 */
public interface ButtonEvent extends Event {

    /**
     * Get the view state.
     * 
     * @return view state when the event is generated
     */
    String getState();
}
