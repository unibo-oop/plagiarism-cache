package it.unibo.javacrush.common;

import java.util.Random;

/**
 * Enum representing the different types of cells in the game.
 */
public enum CellType {
    COFFEE_BEAN,
    SUGAR,
    CUP,
    MILK,
    MOKA,
    SPOON;

    private static final Random RANDOM = new Random();

    /**
     * With this method you can get a random CellType.
     * 
     * @return a random CellType.
     */
    public static CellType getRandomType() {
        return values()[RANDOM.nextInt(values().length)];
    }
}
