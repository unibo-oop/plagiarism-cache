package hotelmaster.database.secretary;

import java.sql.SQLException;

import hotelmaster.pricing.StayExtraPriceDescriber;
import hotelmaster.reservations.ModifiableOccupation;
import hotelmaster.reservations.ModifiableStay;
/**
 * A reservation that can be modified.
 */
public interface ModifiableReservation {
    /**
     * Adds a stay extra for this reservation.
     * @param stay the stay 
     * @param extra the extra to be added
     * @throws SQLException
     */
    void addExtra(ModifiableStay stay, StayExtraPriceDescriber extra);
    /**
     * Remove a stay extra to an existing stay.
     * @param stay the stay
     * @param extra the extra to be removed
     */
    void removeExtra(ModifiableStay stay, StayExtraPriceDescriber extra);
    /**
     * Modify the stay type.
     * @param stay the stay with the new type
     */
    void modifyStayType(ModifiableStay stay);
    /**
     * Delete a reservation before it has been confirmed.
     * @param stay the stay to be removed
     */
    void deleteReservation(ModifiableStay stay);
    /**
     * Modify the dates of a reservation.
     * @param stay the stay with new dates.
     */
    void modifyDates(ModifiableStay stay);
    /**
     * Add a rooms for a reservation.
     * @param stay the stay
     * @param occ the new occupation
     */
    void addRoomOccupation(ModifiableStay stay, ModifiableOccupation occ);
    /**
     * Remove a  reserved room.
     * @param stay the stay 
     * @param occ the occupation to be removed
     */
    void removeRoomOccupation(ModifiableStay stay, ModifiableOccupation occ);
    /**
     * Modify the number and the type of people in a certain room.
     * @param stay the stay
     * @param newOccupation the new occupation in the room
     */
    void modifyPeople(ModifiableStay stay, ModifiableOccupation newOccupation);

}