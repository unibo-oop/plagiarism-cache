package hotelmaster.database.secretary;

import hotelmaster.exceptions.GuestException;
import hotelmaster.reservations.ModifiableStay;
/**
 * A stay that cannot be modified.
 */
public interface Reservation {
    /**
     * Register a new booking.
     * @param stay the new stay to be registered
     * @throws GuestException if a clients books twice.
     */
    void registerStay(ModifiableStay stay) throws GuestException;
    /**
     * Confirm a stay.
     * @param stay the stay to be registered
     */
    void confirmStay(ModifiableStay stay);
    /**
     * Close a finished stay.
     * @param stay the stay to be closed
     */
    void closeStay(ModifiableStay stay);

}