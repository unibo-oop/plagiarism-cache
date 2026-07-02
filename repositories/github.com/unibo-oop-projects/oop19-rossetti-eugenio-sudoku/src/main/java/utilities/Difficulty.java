package utilities;

/**
 * 
 * Enumeration that defines difficulty for the Sudoku.
 *
 */
public enum Difficulty {

    /**
     * Easy sudoku.
     */
    EASY("Easy"),

    /**
     * Medium sudoku.
     */
    MEDIUM("Medium"),

    /**
     * Hard sudoku.
     */
    HARD("Hard");

    private final String name;

    /**
     * Create a new Difficulty.
     * @param name
     */
    Difficulty(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
