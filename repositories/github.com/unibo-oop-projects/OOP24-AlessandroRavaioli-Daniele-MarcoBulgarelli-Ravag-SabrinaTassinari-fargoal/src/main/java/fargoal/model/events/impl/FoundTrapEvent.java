package fargoal.model.events.impl;

import fargoal.model.events.api.FloorEvent;
import fargoal.model.interactable.pickupable.inside_chest.api.ChestItem;

/**
 * FoundTrapEvent is a class that is called everytime
 * the player opens a chest and finds a trap. This class methods
 * are called then for printing to video what the player founds
 * in the chest.
 */
public class FoundTrapEvent implements FloorEvent {
    private final ChestItem chestItem;
    private final boolean mapLost;

    /**
     * Constructor that assigns the ChestItem found in the 
     * chest to the local chestItem and if the player lost or not
     * the map and then sees again only fog.
     * 
     * @param chestItem - the item found in the chest
     * @param mapLost - indicates if the player lost or not the map
     */
    public FoundTrapEvent(final ChestItem chestItem, final boolean mapLost) {
        this.chestItem = chestItem;
        this.mapLost = mapLost;
    }

    /**
     * Method that returns the Trap found in the chest.
     * 
     * @return - the Trap found
     */
    public ChestItem typeOfTrap() {
        return this.chestItem;
    }

    /**
     * Method that returns if the player lost or not
     * the map.
     * 
     * @return - true if the player lost the map, false otherwise
     */
    public boolean hasLostMap() {
        return this.mapLost;
    }
}
