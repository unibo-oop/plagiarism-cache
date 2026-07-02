package model.components;

import com.google.gson.annotations.Expose;

/**
 * 
 * Models the timer for this game, which increases every tenth of second,
 * and pauses for a two second every time a set of words is finished.
 * The format is 24 hours and it stops at 23 : 59 : 59 :09.
 * 
 *
 */
public final class GameTimerImpl implements GameTimer {

    private static final int ONE_TENTH_PAUSE = 100;
    private static final int MAX_HOURS = 23;
    private static final int MAX_MINUTES = 59;
    private static final int MAX_SECONDS = 59;
    private static final int MAX_TENTHS = 9;
    private static final String STANDARD_FORMAT = "%02d";
    private volatile boolean isPaused;
    @Expose
    private int hours;
    @Expose
    private int minutes;
    @Expose
    private int seconds;
    @Expose
    private int tenths;

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHours() {
        return this.hours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMinutes() {
        return this.minutes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSeconds() {
        return this.seconds;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getTenths() {
        return this.tenths;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        this.hours = 0;
        this.minutes = 0;
        this.seconds = 0;
        this.tenths = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setWordSetPause(final boolean pauseStatus) {
        this.isPaused = pauseStatus;
    }

    /**
     * Sleeps for a tenth of second and then updates the timer.
     * If the current set of words has ended, it pauses for a two seconds.
     */
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            this.sleepForX(ONE_TENTH_PAUSE);
            this.checkTenths();
            this.checkWordSetPause(this.isPaused);
            this.setWordSetPause(false); //bisogna notificare il timer che la pausa è finita
        }
    }

    @Override
    public int compareTo(final GameTimer t) {
        return (this.hours != t.getHours()) ? this.hours - t.getHours() : this.compareMin(t);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + hours;
        result = prime * result + minutes;
        result = prime * result + seconds;
        result = prime * result + tenths;
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GameTimerImpl other = (GameTimerImpl) obj;
        return hours == other.hours && minutes == other.minutes && seconds == other.seconds && tenths == other.tenths;
    }

    @Override
    public String toString() {
        return String.format(STANDARD_FORMAT, this.hours) + " : " + String.format(STANDARD_FORMAT, this.minutes)
                + " : " + String.format(STANDARD_FORMAT, this.seconds) + " : " + String.format(STANDARD_FORMAT, this.tenths);
    }

    private void checkHours() {
        if (this.hours == MAX_HOURS) {
            this.reset();
        } else {
            this.hours++;
        }
    }

    private void checkMinutes() {
        if (this.minutes == MAX_MINUTES) {
            this.minutes = 0;
            this.checkHours();
        } else {
            this.minutes++;
        }
    }

    private void checkSeconds() {
        if (this.seconds == MAX_SECONDS) {
            this.seconds = 0;
            this.checkMinutes();
        } else {
            this.seconds++;
        }
    }

    private void checkTenths() {
        if (this.tenths == MAX_TENTHS) {
            this.tenths = 0;
            this.checkSeconds();
        } else {
            this.tenths++;
        }
    }

    private int compareSec(final GameTimer t) {
        return (this.seconds != t.getSeconds()) ? this.seconds - t.getSeconds() : this.tenths - t.getTenths();
    }

    private int compareMin(final GameTimer t) {
        return (this.minutes != t.getMinutes()) ? this.minutes - t.getMinutes() : this.compareSec(t);
    }
}
