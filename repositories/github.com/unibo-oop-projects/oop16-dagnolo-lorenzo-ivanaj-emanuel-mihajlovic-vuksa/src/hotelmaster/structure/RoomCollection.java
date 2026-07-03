package hotelmaster.structure;

import hotelmaster.pricing.RoomExtraPriceDescriber;
import hotelmaster.pricing.RoomTypePriceDescriber;
import hotelmaster.utility.collections.TriggeringSet;

/**
 * A collection of rooms.
 */
public interface RoomCollection extends HotelCollection<Room>, TriggeringSet<Room> {

    /**
     * Sets the type of the room to a given value.
     * 
     * @param room
     *            the room to be updated
     * @param type
     *            the type of the room
     */
    void setType(Room room, RoomTypePriceDescriber type);

    /**
     * Adds an extra to the room.
     * 
     * @param room
     *            the room
     * @param extra
     *            the extra to be added
     * @return true if the room has been updated
     */
    boolean addExtra(Room room, RoomExtraPriceDescriber extra);

    /**
     * Removes an extra from the room.
     * 
     * @param room
     *            the room
     * @param extra
     *            the extra to be removed
     * @return true if the room has been updated
     */
    boolean removeExtra(Room room, RoomExtraPriceDescriber extra);

    /**
     * Removes all extras from a room.
     * 
     * @param room
     *            the room
     */
    void clearExtras(Room room);
}
