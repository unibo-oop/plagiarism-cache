package it.unibo.oop.model;

import static it.unibo.oop.utilities.CharactersSettings.BONUS;

import java.util.Random;

public class ScoreBonus extends AbstractEntity implements Collectable {

    private final int scoreBonusValue;

    private static final int LOW_BONUS = 10;
    private static final int MEDIUM_BONUS = 50;
    private static final int HIGH_BONUS = 150;

    /**
     * Constructor that creates a {@link ScoreBonus} in a defined position
     * @param startingX The initial X position
     * @param startingY The initial Y value
     */
    public ScoreBonus(final double startingX, final double startingY) {
        super(startingX, startingY);
        this.scoreBonusValue = randomScoreGeneration();
    }

    /**
     * Method that takes the {@link MainCharacter} that collected the bonus and
     * gives to him the {@link Score} gained
     * @param bonusCollector the {@link MainCharacter} that collected the bonus
     */
    public void collect(final MainCharacter bonusCollector) {
        bonusCollector.getScore().increaseScore(scoreBonusValue);
    }

    /**
     * Creates a random value of the score to the Score bonus
     * @return a Random score bonus value
     */
    private int randomScoreGeneration() {
        final int value = new Random().nextInt(1000);
        if (isBetween(value, 0, 700)) {
            return LOW_BONUS;
        }
        if (isBetween(value, 701, 950)) {
            return MEDIUM_BONUS;
        }
        return HIGH_BONUS;

    }

    private boolean isBetween(final int x, final int lower, final int upper) {
        return lower <= x && x <= upper;
    }

    /**
     * Gets the {@link ScoreBonus} height
     * @return the height
     */
    protected int getEntityHeight() {
        return BONUS.getHeight();
    }

    /**
     * Gets the {@link ScoreBonus} width
     * @return the width
     */
    protected int getEntityWidth() {
        return BONUS.getWidth();
    }

}
