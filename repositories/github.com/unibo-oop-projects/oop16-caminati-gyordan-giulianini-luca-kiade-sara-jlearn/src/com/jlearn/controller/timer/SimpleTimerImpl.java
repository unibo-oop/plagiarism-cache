package com.jlearn.controller.timer;

import java.util.Set;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.jlearn.controller.UpdatableUi;

/**
 * Simple Timer second count down.
 *
 */
public class SimpleTimerImpl implements SimpleTimer {
    private int                    num;
    private final int              startNum;
    private final Set<UpdatableUi> controller;
    private static final Logger    LOG = Logger.getLogger(SimpleTimerImpl.class);

    /**
     *
     * @param start
     *            the {@link SimpleTimerImpl} starting value.
     * @param controller
     *            add the {@link UpdatableUi} to the listener.
     */

    public SimpleTimerImpl(final int start, final Set<UpdatableUi> controller) {
        LOG.setLevel(Level.WARN);
        this.num = start;
        this.startNum = start;
        this.controller = controller;
    }

    @Override
    public void dec() throws OutOfTimeException {

        if (this.num == 0) {
            throw new OutOfTimeException("Can't dec again!");
        } else {
            this.num--;
            this.notifyChange(new TimerEventImpl(this.num));
        }
    }

    private void notifyChange(final TimerEventImpl ev) {
        if ((this.controller != null) && !this.controller.isEmpty()) {
            this.controller.forEach((e) -> e.timerChanged(ev));
        } else {
            LOG.warn("No Uis ATTACHED!");
        }

    }

    @Override
    public int remainingTime() {
        return this.num;
    }

    @Override
    public void reset() {
        this.num = this.startNum;
        this.notifyChange(new TimerEventImpl(this.num));
    }

}
