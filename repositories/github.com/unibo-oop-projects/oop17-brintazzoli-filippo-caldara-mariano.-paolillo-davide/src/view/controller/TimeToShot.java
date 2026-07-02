package view.controller;

/**
 * Enumeration for the enemy period of shot.
 */
public enum TimeToShot {
    /**
     * List of different time to shot.
     */
    EASY_TIME(3000), MEDIUM_TIME(1000), HARD_TIME(500);

    private int timeToShot;

    /**
     * Private constructor.
     * 
     * @param timeToShot
     *            the period of shot.
     */

    TimeToShot(final int timeToShot) {
        this.timeToShot = timeToShot;
    }

    /**
     * Getter of period of shot.
     * 
     * @return the period of shot in ms.
     */
    public int getTime() {
        return this.timeToShot;
    }

}
