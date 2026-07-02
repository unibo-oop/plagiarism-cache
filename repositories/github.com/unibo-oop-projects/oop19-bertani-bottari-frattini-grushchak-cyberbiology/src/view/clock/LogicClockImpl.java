package view.clock;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 * Class that implements a clock starting from the instant of the selected instantiation 
 * and initializing at 0 seconds 0 minutes...
 *
 */
public class LogicClockImpl implements LogicClock {

    private LocalTime start;
    private static final int SECONDS_IN_MINUTE = 60;

    public LogicClockImpl() {
        start = LocalTime.now();
    }

    @Override
    public final String getSecond() {
        String s = String.format("%02d", getIntSecond() % SECONDS_IN_MINUTE);
        return s;
    }

    @Override
    public final String getMinute() {
        String s = String.format("%02d", getIntMinute());
        return s;
    }

    @Override
    public final int getIntSecond() {
       LocalTime current = LocalTime.now();
       return (int) ChronoUnit.SECONDS.between(start, current);
    }

    private int getIntMinute() {
        LocalTime current = LocalTime.now();
        return (int) ChronoUnit.MINUTES.between(start, current);
    }

}
