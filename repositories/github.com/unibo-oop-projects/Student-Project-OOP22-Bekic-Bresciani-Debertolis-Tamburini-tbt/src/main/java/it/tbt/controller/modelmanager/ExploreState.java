package it.tbt.controller.modelmanager;


import it.tbt.model.statechange.InventoryTrigger;
import it.tbt.model.statechange.PauseTrigger;
import it.tbt.model.world.api.Room;
import it.tbt.model.party.IParty;

/**
 * Wrapper for Explore GameState.
 */
public interface ExploreState extends ModelState {
    /**
     * @return the Party used by the player
     */
    IParty getParty();
    /**
     * @return the current room where the player finds himself in
     */
    Room getRoom();
    /**
     * @return an Object which represent the possibility to switch to the Pause GameState.
     */
    PauseTrigger getTriggerPause();
    /**
     * @return an Object which represent the possibility to switch to the Inventory GameState.
     */
    InventoryTrigger getTriggerInventory();



}
