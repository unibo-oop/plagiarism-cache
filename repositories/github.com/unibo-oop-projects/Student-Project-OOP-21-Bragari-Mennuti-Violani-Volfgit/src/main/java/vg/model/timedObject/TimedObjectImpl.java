package vg.model.timedObject;

public class TimedObjectImpl implements TimedObject {
    /**
     *Time durationMillis of bonus.
     */
    private double duration;

    public TimedObjectImpl(final double duration) {
        this.duration = duration;
    }

    public final Boolean isTimeOver() {
        return duration <= 0;
    }

    public double getRemainingTime() {
        return this.duration;
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public void updateTimer(final double elapsedTime) {
        if (!this.isTimeOver() && elapsedTime < duration) {
            this.duration = this.duration - elapsedTime;
        } else if (!this.isTimeOver() && elapsedTime >= duration) {
            this.duration = 0;
        }
    }
}
