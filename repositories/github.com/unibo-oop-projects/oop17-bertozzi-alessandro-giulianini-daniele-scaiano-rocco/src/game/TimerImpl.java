package game;

/**
 * A basic counter.
 */
public final class TimerImpl implements Timer {

    private int timer;

    /**
     * Sets the timer.
     * @param timer the time
     */
    public TimerImpl(final int timer) {
        this.timer = timer;
    }

    @Override
    public void tick() {
        this.timer--;
    }

    @Override
    public boolean isEnded() {
        return this.timer <= 0;
    }

    @Override
    public int getTimeLeft() {
        return this.timer;
    }
}
