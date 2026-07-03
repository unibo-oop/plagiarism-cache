package hotelmaster.reservations;

import java.time.LocalDate;

import hotelmaster.exceptions.OccupiedRoomException;
import hotelmaster.structure.ModifiableHotel;
import hotelmaster.utility.time.FixedPeriod;

/**
 * The base stay manager, only allowing the alteration of the end-date.
 */
public class StayManager {

    private final Stay stay;
    private final StayState managedState;
    private boolean closed;

    StayManager(final Stay stay, final StayState managedState) throws IllegalArgumentException {
        if (stay == null) {
            throw new IllegalArgumentException("Invalid parameters for StayManager");
        }
        this.stay = stay;
        this.managedState = managedState;
    }

    /**
     * Returns the stay handled by this StayManager.
     * 
     * @return the stay
     */
    protected Stay getStay() {
        return this.stay;
    }

    /**
     * Removes all references of this stay in the hotel and closes the stay.
     */
    protected void closeStay() {
        if (this.closed) {
            return;
        }
        // TODO: complete
        ModifiableHotel.instance().getStays().close(this.stay);
        this.closed = true;
        // TODO: test this
    }

    /**
     * Checks if this stay's rooms are occupied during a different time period.
     * 
     * @param newDuration
     *            the new time period for this stay
     * @throws OccupiedRoomException
     *             the stay is occupied during the dates
     */
    protected void checkConflicts(final FixedPeriod newDuration) throws OccupiedRoomException {
        /*
         * 1. get the occupations of this stay 2. get the rooms for each
         * occupation 3. get the occupations for each room 4. get the other stay
         * (which might be conflicting) for each occupation 5. check if the
         * other stay's dates conflict with the new dates
         */
        if (this.stay.getOccupationsView().stream().map(occupation -> occupation.getRoom())
                .anyMatch(room -> room.isOccupiedDuring(newDuration))) {
            throw new OccupiedRoomException("One or more rooms are occupied during the new period of time");
        }
    }

    /**
     * Checks if this stay's state is the given parameter.
     * 
     * @throws IllegalStateException
     *             the stay is not in the expected state
     */
    protected void checkState() throws IllegalStateException {
        if (this.stay.getState() != this.managedState || this.closed) {
            throw new IllegalStateException("Invalid stay state");
        }
    }

    /**
     * Modifies the end date of a stay. If delayed, there must be no conflicts
     * with already-existing stays for rooms.
     * 
     * @param endDate
     *            the new end-date for the stay
     * @throws IllegalArgumentException
     *             the date is before LocalDate.now()
     * @throws OccupiedRoomException
     *             one or more rooms are occupied by another stay in the period
     *             of time between the old end date and the new end date of this
     *             stay
     */
    public void setEnd(final LocalDate endDate) throws IllegalArgumentException, OccupiedRoomException {
        checkConflicts(FixedPeriod.of(this.stay.getDates().getBeginning(), endDate));
    }

    /**
     * Returns the correct stay manager for the given stay.
     * 
     * @param stay
     *            the stay of whose stay manager will be returned
     * @return the stay manager
     * @throws IllegalArgumentException
     *             the stay is null
     */
    public static StayManager getStayManager(final Stay stay) throws IllegalArgumentException {
        switch (stay.getState()) {
        case ACTIVE:
            return new ActiveStayManager(stay);
        case INACTIVE:
            return new InactiveStayManager(stay);
        default:
            return null;
        }
    }
}
