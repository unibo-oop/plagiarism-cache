package hotelmaster.structure;

import java.time.LocalTime;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import hotelmaster.database.admin.DataFactory;
import hotelmaster.pricing.PriceCollection;
import hotelmaster.reservations.Client;
import hotelmaster.reservations.StayCollection;

/**
 * A modifiable Hotel. All fields are modifiable through getters and modifiable
 * sets.
 */
public interface ModifiableHotel extends Hotel {

    /**
     * Adds a certain amount of floors to the hotel.
     * 
     * @param amount
     *            the amount of floors to add
     * @return the amount of floors in the hotel
     */
    int addFloors(int amount);

    /**
     * Removes a certain amount of floors from the hotel.
     * 
     * @param amount
     *            the amount of floors to remove
     * @return the amount of floors in the hotel
     */
    int removeFloors(int amount);

    /**
     * Returns the floors in the hotel and the highest room number in each
     * floor.
     * 
     * @return the map representing the rooms on each floor
     */
    Map<Integer, Integer> getFloors();

    /**
     * Returns the number-on-floor integers of the non-existing rooms for the
     * given floor. This iterator is immutable and, as such, does not reflect
     * any changes on the data structure after it is instanced.
     * 
     * @param floor
     *            the floor to check for holes
     * @return the map which has the floors as keys and the sets of ranges as
     *         values
     * @throws IllegalArgumentException
     *             the given floor does not exist
     */
    Iterator<Integer> getFloorGaps(int floor) throws IllegalArgumentException;

    /**
     * Returns the {@link PriceCollection} of this hotel, containing all of this
     * hotel's prices.
     * 
     * @return the {@link PriceCollection}
     */
    PriceCollection getPriceManager();

    /**
     * Returns a modifiable list of all the rooms in the hotel. The list does
     * not allow duplicate or null elements.
     * 
     * @return the set of rooms
     */
    RoomCollection getRooms();

    /**
     * Returns a modifiable set of all the stays in the hotel. This is a
     * synchronized set, therefore iteration over this set should be in a
     * synchronized block or method.
     * 
     * @return the set of stays
     */
    StayCollection getStays();

    /**
     * Returns a modifiable set of all the clients in the hotel.
     * 
     * @return the set of clients
     */
    Set<Client> getClients();

    /**
     * Activates a stay cleanup with the given time.
     * 
     * @param callTime
     *            the time at which to clean the stays
     */
    void activateStayCleanupTimer(LocalTime callTime);

    /**
     * Returns the {@link DataFactory} used by the hotel.
     * 
     * @return the DataFactory
     */
    DataFactory getData();

    /**
     * Returns the singleton instance of ModifiableHotel. The instances of Hotel
     * and ModifiableHotel are the same.
     * 
     * @return the ModifiableHotel singleton
     */
    static ModifiableHotel instance() {
        return HotelImpl.instance();
    }
}
