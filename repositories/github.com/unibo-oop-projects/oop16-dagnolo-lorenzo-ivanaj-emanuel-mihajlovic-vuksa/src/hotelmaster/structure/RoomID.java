package hotelmaster.structure;

/**
 * The identifier of a room. A room is defined by the floor it is in and a
 * number greater than 1 which represents the room in its floor. This means that
 * across different floors there might be multiple rooms of number "1", but on
 * one single floor there may be only one.
 */
public interface RoomID extends Comparable<RoomID>, HotelEntity {
    /**
     * Returns the floor of the room of this identifier.
     * 
     * @return the floor as integer
     */
    int getFloor();

    /**
     * Returns the number of the room on its floor.
     * 
     * @return the number as integer
     */
    int getNumberOnFloor();

    /**
     * Returns the full ID of the room, made up by floor and number on floor. In
     * order to keep the length of the ID consistent across all the rooms, a
     * number of trailing zeros are added to both floor and floor-number, if
     * necessary. For example, in a hotel with 10 floors and 100 rooms on floor
     * 3, a certain room with number "27" on floor "1" will be identified by the
     * ID "03027". In order to preserve the trailing zeros, the ID is in string
     * format.
     * 
     * @return the full ID in string format.
     */
    String getFullID();

    /**
     * Instances a new RoomID with the given parameters.
     * 
     * @param floor
     *            the floor of the room
     * @param numberOnFloor
     *            the room's unique number on its floor
     * @return the new instance
     */
    static RoomID create(final int floor, final int numberOnFloor) {
        return new RoomIDImpl(floor, numberOnFloor);
    }
}
