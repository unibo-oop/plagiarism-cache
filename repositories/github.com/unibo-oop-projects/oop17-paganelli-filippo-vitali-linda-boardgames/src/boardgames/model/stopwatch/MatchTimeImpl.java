package boardgames.model.stopwatch;

import boardgames.utility.StopwatchUtilityMethods;

/**
 * This class implements an interface with different
 * basic methods to model time in the Stopwatch class.
 */
public class MatchTimeImpl implements MatchTime {
    
    private static final int LIMIT_ZERO = 10;
    private static final int LIMIT_SECONDS_MINUTES = 60;
    
    private int seconds;
    private int minutes;
    private int hours;
    
    /**
     * Constructor for the class.
     * 
     * @param h, hours
     * @param m, minutes
     * @param s, seconds
     */
    public MatchTimeImpl(final int h, final int m, final int s) {
        this.seconds = s;
        this.minutes = m;
        this.hours = h;
        checkMatchTime(s, m);
    }

    @Override
    public int getSeconds() {
        return this.seconds;
    }

    @Override
    public int getMinutes() {
        return this.minutes;
    }

    @Override
    public int getHours() {
        return this.hours;
    }
    
    @Override
    public void checkMatchTime(final int s, final int m) {
        if (!StopwatchUtilityMethods.timeLimitsSecondsMinutes(s) || !StopwatchUtilityMethods.timeLimitsSecondsMinutes(m)) {
            throw new IllegalArgumentException();
        }
    }
    
    @Override
    public String getCurrentMatchTime() {
        if (checkSecondsMinutes60(this.seconds)) {
            this.seconds = 0;
            this.minutes++;
        } 
        if (checkSecondsMinutes60(this.minutes)) {
            this.minutes = 0;
            this.hours++;
        } 
        return checkStringZero(this.hours) + this.hours + ":" + checkStringZero(this.minutes) + this.minutes + ":" + checkStringZero(this.seconds) + this.seconds;
    }

    
    /**
     * This method checks wheter seconds, minutes 
     * or hours are < 10 of >= 10.
     * 
     * @param i, the number to check
     * @return 0 if the number is < 10
     */
    private String checkStringZero(final int i) {
        return i < LIMIT_ZERO ? "0" : "";
    }
    
    /**
     * This method checks wheter seconds or minutes 
     * are equal to 60 or still < 60.
     * 
     * @param i, the number to check
     * @return true, if i < 60
     */
    private boolean checkSecondsMinutes60(final int i) {
        return i == LIMIT_SECONDS_MINUTES ? true : false;
    }
}
