package it.unibo.modularcheckers.util;

/**
 * Used to serialize some static game rules, like the colors playing or the
 * board configurations.
 */
public interface GameDataSerializer {

    /**
     * Serialize the list of color which plays in certain game into a file.
     */
    void serializeColors();

    /**
     * Serialize the initial Checkers board configuration a file.
     */
    void serializeCheckersBoard();

}
