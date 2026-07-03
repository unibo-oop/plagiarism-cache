package hotelmaster.database.utilities;

import java.util.Map;
import java.util.Set;

import hotelmaster.pricing.PersonPriceDescriber;
import hotelmaster.pricing.RoomExtraPriceDescriber;
import hotelmaster.pricing.StayExtraPriceDescriber;
import hotelmaster.pricing.StayTypePriceDescriber;
import hotelmaster.reservations.Client;
import hotelmaster.structure.ModifiableRoom;
import hotelmaster.structure.Room;
import hotelmaster.utility.time.FixedPeriod;

/**
 * Utilities for a reservation.
 */
public interface BasicReservationUtilities {

    /**
     * Load all the stays into the Hotel.
     */
    void loadStays();

    /**
     * Get all the clients with a reservation.
     * 
     * @return a Set of Clients
     */
    Set<Client> getClients();

    /**
     * Get all the people with a reservation in a certain room.
     * 
     * @param c
     *            the the client with a reserved stay
     * @param r
     *            the room
     * @return a {@link Map} of {@link PersonPriceDescriber} and the number of
     *         people of this type
     */
    Map<PersonPriceDescriber, Integer> getPeople(Client c, Room r);

    /**
     * Get all the rooms booked by a client.
     * 
     * @param c
     *            the client
     * @return a {@link Set} of rooms
     */
    Set<ModifiableRoom> getRooms(Client c);

    /**
     * Get the extras of a room.
     * 
     * @param r
     *            the room
     * @return a {@link Set} of {@link RoomExtraPriceDescriber}
     */
    Set<RoomExtraPriceDescriber> getRoomExtras(Room r);

    /**
     * Get the dates of a reservation.
     * 
     * @param c
     *            the client with a booking
     * @return the arrival date and the departure date
     */
    FixedPeriod getDates(Client c);

    /**
     * Get all the stay extras linked with a client.
     * 
     * @param c
     *            the client
     * @return a {@link Set} of {@link StayExtraPriceDescriber}
     */
    Set<StayExtraPriceDescriber> getExtras(Client c);

    /**
     * Get the type of a reservation.
     * 
     * @param c
     *            the client with a booking
     * @return The {@link StayTypePriceDescriber}
     */
    StayTypePriceDescriber getType(Client c);

}