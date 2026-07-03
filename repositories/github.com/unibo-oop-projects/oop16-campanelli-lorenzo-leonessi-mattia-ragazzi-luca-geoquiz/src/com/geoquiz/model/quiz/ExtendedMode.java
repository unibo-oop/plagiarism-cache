package com.geoquiz.model.quiz;

/**
 * Extended Mode enum.
 *
 */
public enum ExtendedMode implements Mode {
    /**
     * The easy level.
     */
    EASY("F"),
    /**
     * The medium level.
     */
    MEDIUM("M"),
    /**
     * The hard level.
     */
    HARD("D");

    private final String modeCode;

    ExtendedMode(final String modeCode) {
        this.modeCode = modeCode;
    }

    String getModeCode() {
        return this.modeCode;
    }
}
