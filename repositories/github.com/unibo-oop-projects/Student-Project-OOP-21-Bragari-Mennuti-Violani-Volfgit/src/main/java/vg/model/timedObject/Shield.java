package vg.model.timedObject;

/**
 * Shield that protect player while it is moving on borders of map.
 * It has an interal countdown timer that when expired deactivate itself.
 */
public class Shield extends TimedObjectImpl {
    /**
     * Default duration of shield in game loop time unit.
     */
    public static final int DEFAULT_DURATION = 70000;

    /**
     * Shield activation status.
     * */
    private boolean isActive;

    private Shield(final double duration, final boolean isActive) {
        super(duration);
        this.isActive = isActive;
    }

    public static Shield create(final double duration, final boolean isActive) {
        return new Shield(duration, isActive);
    }

    /**
     * Get status of shield. if it is active or not.
     * Its state depend on internal timer.
     * @return true if active, false if not
     */
    public final boolean isActive() {
        return this.isActive && !this.isTimeOver();
    }

    /**
     * Disable shield.
     * */
    public void disableShield() {
        this.isActive = false;
    }

    /**
     * Enable shield if timeout isn'thread expired.
     * */
    public void enableShield() {
        this.isActive = !this.isTimeOver();
    }

    /**
     * Updates timer if shield is active.
     * @param elapsedTime {@inheritDoc}
     * */
    @Override
    public void updateTimer(final double elapsedTime) {
        if (this.isActive()) {
           super.updateTimer(elapsedTime);
        }
    }

}
