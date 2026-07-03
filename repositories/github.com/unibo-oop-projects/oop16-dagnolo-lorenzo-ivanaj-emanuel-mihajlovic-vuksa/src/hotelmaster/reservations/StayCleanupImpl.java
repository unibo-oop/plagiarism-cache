package hotelmaster.reservations;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import hotelmaster.structure.ModifiableHotel;

/**
 * A basic implementation of StayCleanup.
 */
public class StayCleanupImpl implements StayCleanup {

    private final ScheduledExecutorService scheduler;

    StayCleanupImpl(final LocalTime callTime) {
        final long delay = ChronoUnit.MINUTES.between(LocalTime.now(), callTime);
        scheduler = Executors.newScheduledThreadPool(1);
        scheduler.schedule(() -> {
            StayCleanup.call();
            ModifiableHotel.instance().activateStayCleanupTimer(callTime);
        }, delay, TimeUnit.MINUTES);
    }

    @Override
    public void abort() {
        scheduler.shutdown();
    }
}
