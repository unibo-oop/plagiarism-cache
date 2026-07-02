package pvz.model.game.api;

/**
 * Enumeration representing the difficulty levels of the game.
 */
public enum Difficulty {

    /** Easy difficulty, for beginners. */
    EASY,
    /** Normal difficulty, recommended for most players. */
    NORMAL,
    /** Hard difficulty, for experienced players. */
    HARD;

    @Override
    public String toString() {
        return switch (this) {
            case EASY -> "Facile";
            case NORMAL -> "Normale";
            case HARD -> "Difficile";
        };
    }
}
