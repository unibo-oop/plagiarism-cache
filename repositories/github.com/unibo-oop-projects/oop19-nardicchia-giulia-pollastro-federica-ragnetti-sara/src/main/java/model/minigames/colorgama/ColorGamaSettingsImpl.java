package model.minigames.colorgama;

import java.util.function.BiFunction;

import model.DifficultyLevel;

/**
 * This class implements {@link ColorGamaSettings}.
 *
 */
public class ColorGamaSettingsImpl implements ColorGamaSettings {

    private static final int BASE_SECONDS = 5;
    private static final int ORIGIN = 30;
    private static final int BASE = 4;

    private final int gridSize;
    private final int numColors;
    private final int seconds;

    /**
     * Constructor of {@link ColorGamaSettings}.
     * 
     * @param difficulty
     *          the difficulty level of the game
     */
    public ColorGamaSettingsImpl(final DifficultyLevel difficulty) {
        final BiFunction<Integer, Integer, Integer> operation = (i1, i2) -> i1 * difficulty.ordinal() + i2;
        this.gridSize = operation.apply(BASE, BASE);
        this.seconds = operation.apply(BASE_SECONDS, ORIGIN);
        this.numColors = operation.apply(BASE, BASE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getGridSize() {
        return this.gridSize;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getBasePoint() {
        return BASE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSeconds() {
        return this.seconds;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumColor() {
        return this.numColors;
    }
}
