package fargoal.model.events.impl;

import fargoal.model.events.api.FloorEvent;

/**
 * TurnLightOffEvent is a classed called everytime the light
 * of the spell is turned on.
 */
public class TurnLightOnEvent implements FloorEvent {

    /**
     * Method that returns the string that appears on the screen 
     * when the light of the spell is turned on.
     * @return the string for the light off.
     */
    public String getTurnLightOn() {
        return "You turned the light on";
    }
}
