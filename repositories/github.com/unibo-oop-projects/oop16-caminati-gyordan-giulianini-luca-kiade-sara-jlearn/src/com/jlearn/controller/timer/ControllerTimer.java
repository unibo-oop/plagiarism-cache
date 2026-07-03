package com.jlearn.controller.timer;

import com.jlearn.controller.UpdatableUi;

/**
 * Directly control the {@link TimerAgentImpl}.
 */
public interface ControllerTimer {

    /**
     * Pattern Observer.
     *
     * @param controller
     *            add the {@link UpdatableUi} to the listener ;
     */
    void attacheController(UpdatableUi controller);

    /**
     * Pause the {@link TimerAgentImpl}.
     */
    void pauseTimer();

    /**
     * Resume the {@link TimerAgentImpl}.
     */
    void resumeTimer();

    /**
     * Start {@link TimerAgentImpl} initialize and block it for resume.
     *
     */
    void startTimer();

    /**
     * Return the {@link TimerAgentImpl} time left.
     *
     * @return Return the time left.
     */
    int timeLeft();

    /**
     * Reset the second in {@link TimerAgentImpl}.
     */
    void resetTimer();

    /**
     * Set new time for {@link TimerAgentImpl}.
     *
     * @param sec
     *            The int to set.
     */
    void setTimer(int sec);

}