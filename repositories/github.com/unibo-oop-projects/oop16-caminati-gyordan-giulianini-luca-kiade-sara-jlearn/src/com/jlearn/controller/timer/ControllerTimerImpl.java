package com.jlearn.controller.timer;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.jlearn.controller.UpdatableUi;

/**
 * Directly control the {@link TimerAgent} and get update the seconds.
 */
public class ControllerTimerImpl implements ControllerTimer {

    private final TimerAgentImpl agentTimer;

    private static final Logger LOG = Logger.getLogger(ControllerTimerImpl.class);

    @Override
    public String toString() {
        return "ControllerTimerImpl [agentTimer=" + this.agentTimer + "]";
    }

    /**
     *
     * @param name
     *            The name for the thread.
     */
    public ControllerTimerImpl(final String name) {
        LOG.setLevel(Level.WARN);
        this.agentTimer = new TimerAgentImpl(name);
    }

    /**
     * Resume the {@link TimerAgentImpl}.
     */
    // void resumeTimer();

    /**
     * Reset the second in {@link TimerAgentImpl}.
     */
    // void resetTimer();

    @Override
    public void attacheController(final UpdatableUi controller) {
        this.agentTimer.attacheController(controller);
    }

    @Override
    public void pauseTimer() {
        this.agentTimer.pauseTimer();
    }

    @Override
    public void resumeTimer() {
        this.agentTimer.resumeTimer();
    }

    @Override
    public void startTimer() {
        this.agentTimer.pauseTimer();
        this.agentTimer.start();

    }

    @Override
    public int timeLeft() {
        return this.agentTimer.getTimerRemainingTime();
    }

    @Override
    public void resetTimer() {
        LOG.info("Agent timer reset");
        this.pauseTimer();
        this.agentTimer.resetTimer();
    }

    @Override
    public void setTimer(final int sec) {
        this.agentTimer.setStartTime(sec);
    }

}
