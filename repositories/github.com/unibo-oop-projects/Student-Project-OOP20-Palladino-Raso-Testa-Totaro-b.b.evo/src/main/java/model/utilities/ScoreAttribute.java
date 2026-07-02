package model.utilities;

public enum ScoreAttribute {

    /**
     * Used to represent the value that must be added to the score when the brick breaks.
     */
    BRICK_BREAK(+80),

    /**
     * Used to represent the value that must be added to the score when the brick is damaged.
     */
    BRICK_DAMAGED(+40),

    /**
     * Used to represent the value that must be added to the score
     * when the player takes a positive powerup.
     */
    POSITIVE_POWERUP(+40),

    /**
     * Used to represent the value that must be added to the score
     * when the player takes a negative powerup.
     */
    NEGATIVE_POWERUP(-80),

    /**
     * Used to represent the value that must be added to the score
     * when the player loses a life.
     */
    LOST_LIFE(-10),

    /**
     * Used to represent the value that must be added to the score
     * when the player takes a life.
     */
    INCREASE_LIFE(+10);

    private int value;

    ScoreAttribute(final int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
