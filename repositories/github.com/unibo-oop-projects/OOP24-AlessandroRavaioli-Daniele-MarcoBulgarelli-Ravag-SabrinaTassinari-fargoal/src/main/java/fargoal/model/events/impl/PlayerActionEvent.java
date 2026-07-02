package fargoal.model.events.impl;

import fargoal.model.events.api.FloorEvent;
import fargoal.model.interactable.pickupable.inside_chest.api.ChestItem;

/**
 * PlayerActionEvent is a class called everytime that 
 * the player use a ChestItem from his inventory.
 */
public class PlayerActionEvent implements FloorEvent {
    private final ChestItem item;

    /**
     * Constructor that assigns to the local field item the
     * corresponding ChestItem that has been used.
     * 
     * @param item - the ChestItem that has been used
     */
    public PlayerActionEvent(final ChestItem item) {
        this.item = item;
    }

    /**
     * Method that returns the ChestItem that has been
     * used by the player.
     * 
     * @return - the ChestItem that has been used
     */
    public ChestItem whatPlayerUsed() {
        return this.item;
    }
}
