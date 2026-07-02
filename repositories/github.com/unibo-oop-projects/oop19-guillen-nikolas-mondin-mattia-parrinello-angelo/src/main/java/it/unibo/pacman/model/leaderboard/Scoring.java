package it.unibo.pacman.model.leaderboard;

import it.unibo.pacman.model.entities.Entity;

/**
 * Represents the eatable entities in the game with the corresponding score.
 */
public enum Scoring {
    /**
     * pill score.
     */
    PILL(50),
    /**
     * powerpill score.
     */
    POWERPILL(200),
    /**
     * ghost score.
     */
    GHOST(500);

    private int value;
    /**
     * @param value
     */
    Scoring(final int value) {
        this.value = value;
    }
    /**
     * Get value of entity.
     * 
     * @return value
     */
    private int getValue() {
        return value;
    }
    /**
     * Get the scoring of the given entity.
     * 
     * @param entity 
     * @return scoring
     */
    public static int getScoring(final Entity entity) {
        switch (entity.getType()) {
        case POWERPILL:
            return POWERPILL.getValue();
        case PILL:
            return PILL.getValue();
        case INKY:
        case BLINKY:
        case PINKY:
        case CLYDE:
            return GHOST.getValue();
        default:
            return 0;
        }
    }
}
