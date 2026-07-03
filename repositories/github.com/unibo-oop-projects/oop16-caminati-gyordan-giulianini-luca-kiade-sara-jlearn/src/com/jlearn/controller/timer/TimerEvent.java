package com.jlearn.controller.timer;

/**
 * Represent a single event, contains the seconds of {@link SimpleTimerImpl}.
 */
public interface TimerEvent {

    /**
     *
     * @return value of the {@link SimpleTimerImpl}.
     */
    int getValue();

}
