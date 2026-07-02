package tmw.controller.world;

import java.util.Observer;
import tmw.controller.entities.MilkController;

/**
 * This class allows to change room. Room is a piece a level so when a room is "clear"/"completed"
 * game should change room. This class is responsible to do that. Furthermore it extends {@link Observer}
 * so can listen to world events.
 */
public interface RoomSwitcherRoutine extends Observer {

    /**
     * This method loads a new room passing as parameter the same player reference.
     * Each room should have the same player instance. Note that room loaded is selected
     * in a random way. To check available rooms see {@link AvailableRoomsUtils}
     * 
     * @param playerController {@link MilkController} player controller reference
     */
    void loadNextRoom(MilkController playerController);
}
