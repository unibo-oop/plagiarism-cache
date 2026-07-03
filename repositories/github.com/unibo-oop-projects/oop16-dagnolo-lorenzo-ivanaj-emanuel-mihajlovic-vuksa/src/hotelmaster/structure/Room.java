package hotelmaster.structure;

import java.util.Set;

import hotelmaster.pricing.RoomExtraPriceDescriber;
import hotelmaster.pricing.RoomTypePriceDescriber;
import hotelmaster.utility.time.FixedPeriod;

/**
 * A room identified by an ID. Contains its type, extras and currently reserved
 * stays through a set of Occupations. Comparison is done based on the ID.
 */
public interface Room extends Comparable<Room>, HotelEntity {

    /**
     * Returns the ID of the room.
     * 
     * @return the ID
     */
    RoomID getID();

    /**
     * Returns the type of the room.
     * 
     * @return the type
     */
    RoomTypePriceDescriber getType();

    /**
     * Returns the extras associated with the room.
     * 
     * @return the extras in a unmodifiable set
     */
    Set<RoomExtraPriceDescriber> getExtrasView();

    /**
     * Returns whether the room has any occupations.
     * 
     * @return the boolean
     */
    boolean hasOccupations();

    /**
     * Returns whether the room is occupied during the given period.
     * 
     * @param dates
     *            the period
     * @return the boolean
     */
    boolean isOccupiedDuring(FixedPeriod dates);
}
