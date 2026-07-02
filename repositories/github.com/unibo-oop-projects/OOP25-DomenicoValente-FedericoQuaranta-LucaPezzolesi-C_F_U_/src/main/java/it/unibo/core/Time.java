package it.unibo.core;

/**
 * updated deltaTime provider
 */
public class Time {

    private static double dt;

    /** private constructor */
    private Time() {}

    /**
     * updates current deltaTime value
     * @param deltaTimeInSeconds new deltaTime
     */
    public static void updateDeltaTime(final double deltaTimeInSeconds) {
        dt = deltaTimeInSeconds;
    }

    /**
     * gets the current deltaTime
     * @return the current deltaTime value
     */
    public static double deltaTime() {
        return dt;
    }

}
