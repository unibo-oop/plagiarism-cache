package fargoal.model.events.impl;

import fargoal.model.commons.FloorElement;
import fargoal.model.events.api.FloorEvent;

/**
 * WalkOverEvent is a class called everytime that the player
 * walking on the map, steps on a FloorElement that is placed on 
 * the ground.
 */
public class WalkOverEvent implements FloorEvent {
    private final FloorElement element;

    /**
     * Constructor that assigns to the local field element the
     * corresponding FloorElement that the player has stepped on.
     * 
     * @param element - the FloorElement stepped on by the player
     */
    public WalkOverEvent(final FloorElement element) {
        this.element = element;
    }

    /**
     * Method that return which element has been stepped on
     * by the player.
     * 
     * @return - the FloorElement stepped on by the player
     */
    public FloorElement getOnWhat() {
        return this.element;
    }
}
