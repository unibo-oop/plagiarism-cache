package model.minigames.colorgama;

import java.util.Objects;
import java.util.function.BiFunction;

import model.DifficultyLevel;

/**
 * This class implements {@link HSBValuesSettings} and extends {@link ColorGamaSettingsImpl}.
 *
 */
public class HSBValuesSettingImpl implements HSBValuesSetting {

    private static final String NULL_ARG = " passed is null";
    private static final int ORIGIN = 30;
    private static final int BOUND = 1;
    private static final double HUNDRED = 100;
    private static final int ORIGIN_VALUE = 20;
    private static final int OFFSET = 10;

    private final double valueRange;
    private final int hueRange;

    /**
     * Constructor of {@link HSBValuesSettings}.
     * 
     * @param difficulty
     *          the difficulty level of the game
     */
    public HSBValuesSettingImpl(final DifficultyLevel difficulty) {
        Objects.requireNonNull(difficulty, "DifficultyLevel" + NULL_ARG);
        final BiFunction<Integer, Integer, Integer> operation = (i1, i2) -> i1 * difficulty.ordinal() + i2;
        this.hueRange = operation.apply(-BOUND, ORIGIN_VALUE);
        this.valueRange = (double) operation.apply(-BOUND, OFFSET) / HUNDRED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double getRandomColorOrigin() {
        return (double) ORIGIN / HUNDRED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double getRandomColorBound() {
        return BOUND - this.valueRange;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getHueRange() {
        return this.hueRange;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double getValueRange() {
        return this.valueRange;
    }
}
