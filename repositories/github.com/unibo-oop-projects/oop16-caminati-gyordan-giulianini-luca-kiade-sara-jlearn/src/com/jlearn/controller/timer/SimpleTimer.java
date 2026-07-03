package com.jlearn.controller.timer;

/**
 * Simple Timer.
 *
 */
public interface SimpleTimer {

    /**
     * Decrease by 1 the timer.
     *
     * @throws OutOfTimeException
     *             the {@link OutOfTimeException}.
     */
    void dec() throws OutOfTimeException;

    /**
     * Return the {@link SimpleTimerImpl} remaining time.
     * 
     * @return the {@link SimpleTimerImpl} remaining time.
     */
    int remainingTime();

    /**
     * Reset the {@link SimpleTimerImpl}.
     */
    void reset();

}
