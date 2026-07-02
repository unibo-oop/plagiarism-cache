package it.unibo.jtrs.utils;

/**
 * A simple chronometer class that measures elapsed time in milliseconds.
 */
public class Chronometer {

    private long start;

    /**
     * Constructor.
     */
    public Chronometer() {
        this.start = System.currentTimeMillis();
    }

    /**
     * Returns how many millisecond have passed since chronometer creation.
     * 
     * @return the millisecond elapsed
     */
    public long elapsed() {
        return System.currentTimeMillis() - this.start;
    }

    /**
     * Resets the chronometer and start counting over from 0.
     */
    public void reset() {
        this.start = System.currentTimeMillis();
    }
}
