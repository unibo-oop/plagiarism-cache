package com.jlearn.controller.timer;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.jlearn.controller.UpdatableUi;

/**
 * The {@link Thread} {@link SimpleTimerImpl}, called {@link TimerAgentImpl}.
 */
public class TimerAgentImpl extends Thread implements TimerAgent {

    private static final Logger LOG = Logger.getLogger(TimerAgentImpl.class);
    // THREAD VARIABLES
    private volatile boolean suspended;
    private volatile boolean stopped;
    // VARIABLES
    private volatile SimpleTimerImpl timer;

    private volatile Set<UpdatableUi> controller;

    /**
     *
     * @param threadName
     *            the {@link String} name of the {@link Thread}.
     *
     */

    public TimerAgentImpl(final String threadName) {
        LOG.setLevel(Level.WARN);
        this.setName(threadName);
        this.controller = new HashSet<>();
        this.suspended = false;
        this.stopped = false;

    }

    @Override
    public String toString() {
        return "TimerAgentImpl [suspended=" + this.suspended + ", stopped=" + this.stopped + ", timer=" + this.timer
                + "]";
    }

    /**
     * Pattern Observer.
     *
     * @param controller
     *            add the {@link UpdatableUi} to the listener;
     */
    @Override
    public void attacheController(final UpdatableUi controller) {
        this.controller.add(controller);
    }

    @Override
    public void setStartTime(final int sec) {
        this.timer = new SimpleTimerImpl(sec, this.controller);

    }

    @Override
    public synchronized int getTimerRemainingTime() {
        return this.timer.remainingTime();
    }

    /**
     * Stop {@link TimerAgentImpl}.
     */
    @Override
    public synchronized void killTimer() {
        super.interrupt();
        this.stopped = false;
    }

    /**
     * Pause the {@link TimerAgentImpl}.
     */
    @Override
    public synchronized void pauseTimer() {
        this.suspended = true;
    }

    /**
     * Reset the {@link SimpleTimerImpl}, reuse last initial time.
     */
    @Override
    public synchronized void resetTimer() {
        this.timer.reset();
    }

    /**
     * Resume the {@link TimerAgentImpl}.
     */
    @Override
    public synchronized void resumeTimer() {
        this.suspended = false;
        this.notifyAll();
    }

    @Override
    public void run() {
        this.stopped = true;
        while (this.stopped) {
            try {
                this.timer.dec();
                super.sleep(1000);

                synchronized (this) {
                    while (this.suspended) {
                        this.wait();
                    }
                }

            } catch (final InterruptedException exception) {
                LOG.debug("Timer Agent  INTERRUPT");
            } catch (final OutOfTimeException exception) {
                LOG.debug("Timer Agent stopt : no more time");
                this.killTimer();
            } catch (final Exception exception) {

                LOG.debug("Strange ERROR! : " + exception.getMessage() + " N.Thread " + this.getName() + this.getId());
            }
        }
        LOG.debug("Timer Agent  died naturally");
    }
}
