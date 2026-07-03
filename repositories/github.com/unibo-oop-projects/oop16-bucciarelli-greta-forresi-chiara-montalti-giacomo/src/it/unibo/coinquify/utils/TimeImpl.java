package it.unibo.coinquify.utils;

import java.io.Serializable;

/**
 * Time class.
 */
public class TimeImpl implements Time, Serializable {
    private static final int ONE_DIGIT = 10;
    private static final int MIDNIGHT = 24;
    private static final int MINUTES_IN_HOUR = 60;
    // private static final int MIDNIGHT = 24;
    /**
     * 
     */
    private static final long serialVersionUID = -7324884520278924931L;
    private static final int MAX_HOUR = 23;
    private static final int MAX_MINUTES = 59;

    private int hour;
    private int minutes;

    /**
     * 
     * @param hour
     *            of time
     * @param minutes
     *            of time
     */
    public TimeImpl(final int hour, final int minutes) {
        if (hour < 0 || hour > MAX_HOUR || minutes < 0 || minutes > MAX_MINUTES) {
            throw new IllegalArgumentException();
        }
        this.hour = hour;
        this.minutes = minutes;
    }

    @Override
    public int getHour() {
        return this.hour;
    }

    @Override
    public int getMinutes() {
        return this.minutes;
    }

    @Override
    public void setHour(final int hour) {
        this.hour = hour;
    }

    @Override
    public void setMinutes(final int minutes) {
        this.minutes = minutes;
    }

    @Override
    public boolean before(final Time time) {
        return this.getAllMinutes() < time.getAllMinutes();
    }

    @Override
    public boolean after(final Time time) {
        return this.getAllMinutes() > time.getAllMinutes();
    }

    @Override
    public boolean equals(final Object obj) {
        return obj != null && this.getAllMinutes() == ((Time) obj).getAllMinutes();
    }

    @Override
    public int hashCode() {
        return this.getHour() * this.getMinutes() + 1;
    }

    @Override
    public String toString() {
        String ret = "";
        if (this.getHour() < ONE_DIGIT) {
            ret = "0";
        }
        ret += this.getHour();
        if (this.getMinutes() < ONE_DIGIT) {
            ret += ":0" + this.getMinutes();
        } else {
            ret += ":" + this.getMinutes();
        }
        return ret;
    }

    @Override
    public int getAllMinutes() {
        if (this.getHour() == 0 && this.getMinutes() == 0) {
            return TimeImpl.MIDNIGHT * TimeImpl.MINUTES_IN_HOUR;
        }
        return this.getHour() * TimeImpl.MINUTES_IN_HOUR + this.getMinutes();
    }
}
