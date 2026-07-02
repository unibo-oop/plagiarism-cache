package fargoal.model.events.impl;

import fargoal.model.events.api.FloorEvent;

/**
 * BlessedEvent is a class called everytime that the player
 * is blessed in the temple.
 */
public class BlessedEvent implements FloorEvent {

    /**
     * This method returns the string that appears on screen when the player
     * is blessed in the temple.
     * @return the string for blessing.
     */
    public String getBlessed() {
        return "You are blessed!";
    }
}
