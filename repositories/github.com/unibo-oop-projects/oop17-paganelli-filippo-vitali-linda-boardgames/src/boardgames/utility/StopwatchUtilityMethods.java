package boardgames.utility;

/**
 * This class implements some utility methods
 * to generate a Stopwatch for the match. It's 
 * final to avoid any instance from outside.
 */
public final class StopwatchUtilityMethods {

    private static final int MAX_SECONDS_AND_MINUTES = 60;

    /**
     * Constructor for the class.
     */
    private StopwatchUtilityMethods() {
    }
    
    /**
     * This method controls whether seconds and minutes 
     * respect the standard limits that are given.
     * 
     * @param i
     * @return true, if limits are respected
     */
    public static boolean timeLimitsSecondsMinutes(final int i) {
        return (i >= 0 && i <= MAX_SECONDS_AND_MINUTES);
    }
}
