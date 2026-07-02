package clashclass.battle.timer;

/**
 * Represents a simple {@link Timer} implementation.
 */
public class TimerGameImpl implements Timer {
    private long startTime;
    private long endTime;

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        this.startTime = System.currentTimeMillis();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop() {
        this.endTime = System.currentTimeMillis() - startTime;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onFinished() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getElapsedTime() {
        if (this.startTime == 0) {
            return 0;
        }
        return (System.currentTimeMillis() - startTime) / 1000;
    }

    /**
     * Gets the end time.
     *
     * @return the end time
     */
    public long getEndTime() {
        return this.endTime;
    }
}
