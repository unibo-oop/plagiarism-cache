package controller.Room;

import java.util.List;

import model.room.Room;


public interface ControllerRoom {

    /**
     * 
     * @return The room's list
     */
    List<Room> getAll();

    /**
     * 
     * @param number of room
     * @return The specific room
     */
    Room getRoom(int number);

    /**
     * Check that the room number is present.
     * @param number
     * @return true if the room is present, otherwise false
     */
    boolean isPresent(int number);

}
