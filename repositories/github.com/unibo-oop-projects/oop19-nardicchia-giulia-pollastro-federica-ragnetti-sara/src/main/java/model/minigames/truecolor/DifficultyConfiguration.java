package model.minigames.truecolor;

import java.util.Objects;
import java.util.Optional;

import model.DifficultyLevel;

/**
 * Enumeration that convert DifficultyLevel adding configuration fields.
 *
 */
public enum DifficultyConfiguration {

    /**
     * Represent the easy level configuration.
     */
    EASY(4, 1, 1),

    /**
     * Represent the normal level configuration.
     */
    NORMAL(6, 1, 2),

    /**
     * Represent the hard level configuration.
     */
    HARD(8, 1, 2);

    private final int numColor;
    private final int meanButton;
    private final int trueButton;

    DifficultyConfiguration(final int numColor, final int meanButton, final int trueButton) {
        this.numColor = numColor;
        this.meanButton = meanButton;
        this.trueButton = trueButton;
    }

    /**
     * @return the number of color of the specific difficulty level
     * 
     */
    public int getNumColor() {
        return numColor;
    }

    /**
     * @return the number of meaning color of the specific difficulty level
     * 
     */
    public int getMeanButton() {
        return meanButton;
    }

    /**
     * @return the number of true color of the specific difficulty level
     * 
     */
    public int getTrueButton() {
        return trueButton;
    }

//    public DifficultyStrategy getStrategy() {
//        return new DifficultyStrategyImpl(this);
//    }

    /**
     * Get the right DifficultyConfiguration.
     * 
     * @param difficulty
     *          the difficulty selected
     * @return DifficultyConfiguration
     *          the configuration with useful fields
     * 
     */
    public static Optional<DifficultyConfiguration> getConfiguration(final DifficultyLevel difficulty) {
        Objects.requireNonNull(difficulty, "DifficultyLevel is null in DifficultyConfiguration ");
        switch (difficulty) {
        case EASY:
            return Optional.of(DifficultyConfiguration.EASY);
        case NORMAL:
            return Optional.of(DifficultyConfiguration.NORMAL);
        case HARD:
            return Optional.of(DifficultyConfiguration.HARD);
        default:
            return Optional.empty();
        }
    }
}
