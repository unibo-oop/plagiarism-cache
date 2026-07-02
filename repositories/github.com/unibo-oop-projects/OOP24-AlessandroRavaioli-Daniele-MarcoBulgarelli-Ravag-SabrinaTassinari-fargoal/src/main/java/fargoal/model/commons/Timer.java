package fargoal.model.commons;

/**
 * A class that is used for the purpose of setting a timer.
 */
public class Timer {

    private long time;

    /**
     * Constructor that sets the initial time to 0.
     */
    public Timer() {
        this.time = 0;
    }

    /**
     * A method to set the time the timer has to check.
     * @param time - how long is this time interval
     */
    public void setTime(final long time) {
        this.time = time;
    }

    /**
     * A method that calculates the remaining time given the time that has passed.
     * 
     * @param elapsed - the time that has passed
     * @return - the remaining time left
     */
    public long updateTime(final long elapsed) {
        this.time = this.time - elapsed;
        if (this.time < 0) {
            this.time = 0;
        }
        return this.time;
    }
}
