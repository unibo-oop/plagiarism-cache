package com.jlearn.model.utilities;

/**
 *
 * An enumeration to define levels.
 *
 */
public enum Levels {

    /**
     *
     */
    EASY("Easy", 10), MEDIUM("Medium", 6), HARD("Hard", 3);

    private static final int SECS_IN_MINUTE = 60;
    private final int        minutes;
    private final String     name;

    /**
     *
     * @return the given name.
     *
     */
    public String getName() {
        return this.name;
    }

    /**
     *
     * @return the given minutes. *
     *
     */
    public int getMinutes() {
        return this.minutes;
    }

    /**
     *
     * @return the given seconds.
     *
     */
    public int getSecs() {
        return this.minutes * Levels.SECS_IN_MINUTE;
    }

    Levels(final String name, final int minutes) {
        this.name = name;
        this.minutes = minutes;

    }
}
