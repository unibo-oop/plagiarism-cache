package hotelmaster.reservations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.stream.Collectors;

import hotelmaster.structure.ModifiableHotel;

/**
 * Periodically cleans the inactive stays which are past their beginning date.
 * The cleanup happens the day after the stays' beginning. At the end of a
 * cleanup, the next cleanup should be automatically called.
 */
public interface StayCleanup {

    /**
     * Aborts this stay cleanup.
     */
    void abort();

    /**
     * Cleans the stays.
     */
    static void call() {
        final Collection<Stay> allStays = ModifiableHotel.instance().getStays();
        final Collection<Stay> toRemove = allStays.stream().filter(stay -> stay.getState() == StayState.INACTIVE
                && stay.getDates().getBeginning().isAfter(LocalDate.now())).collect(Collectors.toSet());
        for (final Stay stay : toRemove) {
            final StayManager manager = stay.getStayManager();
            if (manager instanceof InactiveStayManager) {
                ((InactiveStayManager) manager).cancel();
                allStays.remove(stay);
            }
        }
    }

    /**
     * Creates a new StayCleanup and sets it to run at the given time.
     * 
     * @param callTime
     *            the time at which to call the cleanup
     * @return the instance
     */
    static StayCleanup create(LocalTime callTime) {
        return new StayCleanupImpl(callTime);
    }
}
