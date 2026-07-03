package com.jlearn.controller;

import com.jlearn.controller.timer.TimerAgentImpl;
import com.jlearn.controller.timer.TimerEventImpl;

/**
 * Pattern Observer for the {@link TimerAgentImpl}.
 *
 */
public interface UpdatableUi {
    /**
     * Notify changed time.
     *
     * @param ev
     *            the {@link TimerEventImpl}.
     */
    void timerChanged(TimerEventImpl ev);
}
