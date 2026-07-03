package com.jlearn.controller.timer;

/**
 * The {@link TimerEventImpl}, contains the effective number of the {@link SimpleTimerImpl}.
 */
public class TimerEventImpl implements TimerEvent {

    private final int value;

    /**
     *
     * @param currValue
     *            the current value of {@link SimpleTimerImpl}.
     */
    public TimerEventImpl(final int currValue) {
        this.value = currValue;
    }

    @Override
    public int getValue() {
        return this.value;

    }

}
