package utilities;

/**
 * This is a simple timer.
 */
public class GameTimer {

    private static final long SECONDS_IN_MINUTE = 60;
    private long initTime;
    private long finalTime;
    private long delay;
    private long startStop;
    private boolean stopped;

    /**
     * Constructor.
     */
    public GameTimer() {
        this.initTime = 0;
        this.finalTime = 0;
        this.delay = 0;
        this.startStop = 0;
        this.stopped = true;
    }

    /**
     * The timer start.
     */
    public void start() {
        if (this.stopped) {
            this.stopped = false;
            if (this.initTime == 0) {
                this.initTime = System.currentTimeMillis();
            } else {
                final long currentDelay = System.currentTimeMillis() - this.startStop;
                this.delay = this.delay + currentDelay;
            }
        }
    }

    /**
     * The timer will be stopped.
     */
    public void stop() {
        this.stopped = true;
        this.startStop = System.currentTimeMillis();
        this.finalTime = this.startStop;
    }

    /**
     * Getter for the elapsed time in milliseconds format.
     * @return elapsed milliseconds
     */
    public long getTimeMillis() {
        return this.finalTime - this.initTime - this.delay;
    }

    /**
     * Getter for elapsed minutes. 
     * @return elapsed minutes
     */
    public long getMinutes() {
        if (!this.stopped) {
            this.finalTime = System.currentTimeMillis();
        }
        return (this.getTimeMillis() / 1000) / SECONDS_IN_MINUTE;
    }

    /**
     * Getter for elapsed seconds.
     * @return elapsed seconds
     */
    public long getSeconds() {
        if (!this.stopped) {
            this.finalTime = System.currentTimeMillis();
        }
        return (this.getTimeMillis() / 1000) % SECONDS_IN_MINUTE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("%02d", this.getMinutes()) + ":" + String.format("%02d", this.getSeconds());
    }
}
