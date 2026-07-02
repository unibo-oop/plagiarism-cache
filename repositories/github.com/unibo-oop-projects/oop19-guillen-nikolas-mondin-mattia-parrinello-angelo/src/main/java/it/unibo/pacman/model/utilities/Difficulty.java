package it.unibo.pacman.model.utilities;
/**
 * An enum that represents the {@link Difficulty} of this game.
 */
public enum Difficulty {
    /**
     * Represents the "Normal" difficulty level.
     */
    NORMAL,
    /**
     * Represents the "Hard" difficulty level.
     */
    HARD,
    /**
     * Used for JUnit test.
     */
    TEST;
    /**
     * @return the difficulty in form of a string
     */
    @Override
    public String toString() {
        switch (this) {
        case NORMAL:
            return "Normal";
        case HARD:
            return "Hard";
        case TEST:
            return "Test";
        default:
            return "";
        }
    }
}
