package view.gamescreen.events;

import javafx.event.Event;
import javafx.event.EventType;

/**
 * 
 * Custom event fired when states are updated.
 *
 */
public class StateUpdatedEvent extends Event {

    private static final long serialVersionUID = 7032991840530504870L;

    /**
     * 
     * Event definition.
     * 
     */
    public static final EventType<StateUpdatedEvent> STATE_UPDATE = new EventType<>(Event.ANY, "STATE_UPDATE");

    /**
     * 
     * Default class constructor.
     * 
     */
    public StateUpdatedEvent() {
        super(STATE_UPDATE);
    }

}
