package oop.raccoonhome.home;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 *
 */
public final class House implements Serializable {

    /**
     * For saving the configuration.
     */
    private static final long serialVersionUID = 000000001L;

    private final ArrayList<Room> rooms = new ArrayList<Room>();

    /**
     * @param room
     *            room to add at house
     */
    public void addRoom(final Room room) {
        rooms.add(room);
    }

    /**
     * @param rooms
     *            list of rooms that we want add
     * @return true if argument list is added at the room list
     */
    public boolean addRooms(final List<Room> rooms) {
        return this.rooms.addAll(rooms);
    }

    /**
     * This class return a unmodifiable copy of list.
     * @return a copy of room list
     */
    public List<Room> getRooms() {
        return Collections.unmodifiableList(rooms);
    }

    /**
     * This class return a no secure copy of list.
     * @param roomType
     *            type of room that you want found in list
     * @return All room whit room type equals roomType that found in the list.
     *         Return empty list if rooms not found
     */
    public List<Room> getRoom(final ERoom roomType) {
        List<Room> temp = new ArrayList<Room>();
        for (final Room room : rooms) {
            if (room.getRoom().equals(roomType)) {
                temp.add(room);
            }
        }
        return temp;
    }
}
