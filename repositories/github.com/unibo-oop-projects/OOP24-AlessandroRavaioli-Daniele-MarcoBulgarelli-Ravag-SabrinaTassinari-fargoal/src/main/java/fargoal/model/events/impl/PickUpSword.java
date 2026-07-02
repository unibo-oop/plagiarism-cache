package fargoal.model.events.impl;

import fargoal.model.commons.FloorElement;
import fargoal.model.events.api.FloorEvent;

/**
 * PickUpSword is a class called when the player reach the 
 * sword and takes that.
 */
public class PickUpSword implements FloorEvent {
    private final FloorElement sword;

    /**
     * Constructor that assigns to the local field sword the
     * corresponding FloorElement that is given.
     * 
     * @param sword - the sword that has been taken
     */
    public PickUpSword(final FloorElement sword) {
        this.sword = sword;
    }

    /**
     * Method that returns the FloorElement sword that
     * the player has taken.
     * 
     * @return - the sword that has been taken
     */
    public FloorElement pickedUp() {
        return this.sword;
    }
}
