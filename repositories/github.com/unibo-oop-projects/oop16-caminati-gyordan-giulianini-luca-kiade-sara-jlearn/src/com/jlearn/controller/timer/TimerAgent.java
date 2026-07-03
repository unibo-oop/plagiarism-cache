package com.jlearn.controller.timer;

import com.jlearn.controller.UpdatableUi;

/**
 * Represent a simple count down Timer.
 */
public interface TimerAgent {

    /**
     * Pattern Observer.
     *
     * @param controller
     *            add the {@link UpdatableUi} to the listener;
     */
    void attacheController(UpdatableUi controller);

    /**
     * change and reset the {@link TimerAgent} timer.
     *
     * @param sec
     *            the new start timer.
     */
    void setStartTime(int sec);

    /**
     * @return the {@link SimpleTimer} remaining Time.
     */
    int getTimerRemainingTime();

    /**
     * Stop {@link TimerAgentImpl}.
     */
    void killTimer();

    /**
     * Pause the {@link TimerAgentImpl}.
     */
    void pauseTimer();

    /**
     * Reset the {@link SimpleTimerImpl}.
     */
    void resetTimer();

    /**
     * Resume the {@link TimerAgentImpl}.
     */
    void resumeTimer();

}
