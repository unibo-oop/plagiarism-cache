package fargoal.model.events.impl;

import fargoal.model.events.api.FloorEvent;
import fargoal.model.interactable.pickupable.inside_chest.api.ChestItem;

/**
 * PickUpNewItemevent is a class called everytime that the player 
 * decides to interact with a chest on the ground
 * and find an item to put in the inventory.
 */
public class PickUpNewItemEvent implements FloorEvent {
    private final ChestItem item;

    /**
     * Constructor that assigns to the local field item the
     * corresponding ChestItem found in the chest.
     * 
     * @param item - the picked up item
     */
    public PickUpNewItemEvent(final ChestItem item) {
        this.item = item;
    }

    /**
     * Method that returns the ChestItem found in the chest.
     * 
     * @return - the ChestItem found
     */
    public ChestItem pickedUpWhat() {
        return this.item;
    }
}
