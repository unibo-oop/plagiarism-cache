package view.gamescreen.events;

import javafx.event.Event;
import javafx.event.EventType;

/**
 * 
 * Custom event fired when player info are updated (e.g: when a turn ends).
 *
 */
public class InfoPlayerEvent extends Event {

 
    private static final long serialVersionUID = -3177314144866135817L;

    /**
     * Event definition.
     */
    public static final EventType<InfoPlayerEvent> PLAYER_INFO_UPDATE = new EventType<>(Event.ANY, "PLAYER_INFO_UPDATE");

    /**
     * 
     * Default class constructor.
     * 
     */
    public InfoPlayerEvent() {
        super(PLAYER_INFO_UPDATE);
        // TODO Auto-generated constructor stub
    }

}
