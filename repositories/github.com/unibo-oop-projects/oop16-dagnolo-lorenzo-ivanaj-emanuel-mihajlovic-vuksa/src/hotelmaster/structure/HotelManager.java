package hotelmaster.structure;

import hotelmaster.exceptions.MissingEntityException;
import hotelmaster.exceptions.RoomRemovalException;
import hotelmaster.pricing.PriceDescriber;

/**
 * Allows adding/removing rooms, floors and prices from a {@link Hotel}.
 */
public interface HotelManager {

    /**
     * Adds a {@link PriceDescriber}, if it doesn't already exist.
     * 
     * @param <T>
     *            the type of the PriceDescriber to be added
     * @param priceDescriber
     *            the PriceDescriber to be added
     * @return whether the operation failed or succeeded
     */
    <T extends PriceDescriber> boolean addPriceDescriber(T priceDescriber);

    /**
     * Sets a price describer to a certain value, if it is not associated with a
     * stay.
     * 
     * @param <T>
     *            the type of the PriceDescriber to be set
     * @param priceDescriber
     *            the price describer to be set
     * @param value
     *            the new value of the price
     * @return whether the operation failed or succeeded
     */
    <T extends PriceDescriber> boolean setPriceDescriber(T priceDescriber, double value);

    /**
     * Removes a price describer from the hotel.
     * 
     * @param <T>
     *            the type of the PriceDescriber to be added
     * @param priceDescriber
     *            the priceDescriber already present in the hotel
     * @return whether the operation failed or succeeded
     */
    <T extends PriceDescriber> boolean removePriceDescriber(T priceDescriber);

    /**
     * Adds a certain amount of top floors to the hotel.
     * 
     * @param amount
     *            the amount of floors to add
     * 
     * @return the new top floor
     */
    int addFloors(int amount);

    /**
     * Removes the given floor from the hotel.
     * 
     * @param floorToRemove
     *            the floor to remove
     * @return the new top floor
     * @throws RoomRemovalException
     *             the floor has one or more rooms which are associated to a
     *             stay
     * @throws IllegalArgumentException
     *             the floor is not on the hotel
     */
    int removeFloor(int floorToRemove) throws RoomRemovalException, IllegalArgumentException;

    /**
     * Removes a certain amount of floors from the top of the hotel, along with
     * the rooms in them. This operation can fail if there is no top floor, or
     * if a room in the removed floor is associated with a stay.
     * 
     * @param amount
     *            the amount of floors to remove
     * @return the new top floor
     * @throws RoomRemovalException
     *             the floors have rooms which are associated to a stay in it
     * @throws IllegalArgumentException
     *             the amount is not a positive integer
     */
    int removeFloors(int amount) throws RoomRemovalException, IllegalArgumentException;

    /**
     * Adds an amount of rooms to a floor. All of the rooms will be instanced
     * such as roomTemplate, but with appropriate an RoomID for each room.
     * 
     * @param floor
     *            the floor on which to add rooms
     * @param amount
     *            the amount of rooms to add
     * @param template
     *            the template room
     * @throws MissingEntityException
     *             a PriceDescriber in the given RoomTemplate is not in the
     *             hotel
     * @throws IllegalArgumentException
     *             parameters floor and amount must be positive. floor cannot be
     *             larger than {@link Hotel#getFloorView()}.
     */
    void addRooms(int floor, int amount, RoomTemplate template) throws MissingEntityException, IllegalArgumentException;

    /**
     * Updates a room, setting its properties as the given {@link RoomTemplate}.
     * 
     * @param room
     *            the room to update
     * @param template
     *            the template of the room
     * @throws MissingEntityException
     *             a PriceDescriber in the given RoomTemplate is not in the
     *             hotel, or the room is not in this hotel
     */
    void updateRoom(Room room, RoomTemplate template) throws MissingEntityException;

    /**
     * Removes a room from the hotel. This operation can fail if the room has
     * stays associated to it.
     * 
     * @param room
     *            the room to be removed
     * @throws RoomRemovalException
     *             the room has one or more stays
     * @throws IllegalArgumentException
     *             the room does not exist in the hotel
     */
    void removeRoom(Room room) throws RoomRemovalException, IllegalArgumentException;

    /**
     * Instances a new {@link HotelManager}.
     * 
     * @return the new instance
     */
    static HotelManager create() {
        return new HotelManagerImpl();
    }
}
