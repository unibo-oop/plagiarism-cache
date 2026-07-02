package it.unibo.model.impl;

import it.unibo.model.api.ComponentType;

/**
 * Component to stop Ralph from throwing bricks for a certain amount of time.
 */
public class StopRalphComponent extends AbstractComponent {

    private static final int THRESHOLDTIME = 10_000;
    private long startTime;

    /**
     * Sets the stopRalph behavior by blocking the given ThrowBrickComponent
     * and recording the start time.
     *
     * @param throwBrickComponent the ThrowBrickComponent to be blocked
     */
    public void setStopRalph(final ThrowBrickComponent throwBrickComponent) {
        throwBrickComponent.setBlocked();
        this.startTime = System.currentTimeMillis();
    }

    /**
     * Checks if Ralph should be unlocked based on the state of the ThrowBrickComponent.
     * If the ThrowBrickComponent is blocked and the elapsed time since the start is greater than or equal to 10 seconds,
     * the ThrowBrickComponent is set to unblocked.
     *
     * @param throwBrickComponent the ThrowBrickComponent to check for blocking state
     */
    public void checkUnlockRalph(final ThrowBrickComponent throwBrickComponent) {
        if (throwBrickComponent.isBlocked()) {
            final long currentTime = System.currentTimeMillis();
            if (currentTime - this.startTime >= THRESHOLDTIME) {
                throwBrickComponent.setUnblocked();
            }
        }
    }

    /**
     * Returns the component type of this StopRalphComponent.
     *
     * @return the component type
     */
    @Override
    public ComponentType getComponent() {
        return ComponentType.STOPRALPH;
    }

}
