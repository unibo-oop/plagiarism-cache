package javawulf.model.map.factory;

import javawulf.model.map.Room;

/**
 * Used to obtain default rooms.
 */
public interface RoomFactory {
    /**
     * 
     * @return a 5x5 (standard size) room.
     */
    Room getSquaredRoom();
}
