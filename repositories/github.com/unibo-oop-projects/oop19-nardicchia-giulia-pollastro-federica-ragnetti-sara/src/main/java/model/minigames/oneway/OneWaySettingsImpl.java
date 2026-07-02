package model.minigames.oneway;

import java.util.Objects;
import java.util.function.BiFunction;

import model.DifficultyLevel;

/**
 * Implementation of {@link OneWaySettings}.
 *
 */
public class OneWaySettingsImpl implements OneWaySettings {
    private static final int BASE_ARROW = 3;
    private static final int BASE_SIZE = 6;
    private static final int BASE_SEC = 40;
    private static final int NEGATIVE_OFFSET = -5;

    private final DifficultyLevel difficulty;
    private final BiFunction<Integer, Integer, Integer> calculator = (i1, i2) -> i1 + i2;

    /**
     * Constructor of {@link OneWaySettings}.
     * @param difficulty
     *               the selected {@link DifficultyLevel}
     */
    public OneWaySettingsImpl(final DifficultyLevel difficulty) {
        this.difficulty = Objects.requireNonNull(difficulty);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getArrows() {
        return this.calculator.apply(BASE_ARROW, this.difficulty.ordinal());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getGridSize() {
        return this.calculator.apply(BASE_SIZE, this.difficulty.ordinal());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSeconds() {
        return this.calculator.apply(BASE_SEC, NEGATIVE_OFFSET * this.difficulty.ordinal());
    }
}
