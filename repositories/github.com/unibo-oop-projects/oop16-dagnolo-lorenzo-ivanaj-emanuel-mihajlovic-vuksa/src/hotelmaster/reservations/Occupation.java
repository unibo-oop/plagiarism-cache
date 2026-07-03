package hotelmaster.reservations;

import java.util.Map;

import hotelmaster.pricing.PersonPriceDescriber;
import hotelmaster.structure.HotelEntity;
import hotelmaster.structure.Room;

/**
 * Represents a room's occupation, linked to a stay and containing a certain
 * amount of people.
 */
public interface Occupation extends Comparable<Occupation>, HotelEntity {

    /**
     * Returns the stay relevant to this occupation.
     * 
     * @return the stay
     */
    Stay getStay();

    /**
     * Returns the room relevant to this occupation.
     * 
     * @return the room
     */
    Room getRoom();

    /**
     * Returns all the people present in the room as an immutable map.
     * 
     * @return a Map linking a type of person to the amount of people of that
     *         type that are currently in the room.
     */
    Map<PersonPriceDescriber, Integer> getPeopleView();
}
