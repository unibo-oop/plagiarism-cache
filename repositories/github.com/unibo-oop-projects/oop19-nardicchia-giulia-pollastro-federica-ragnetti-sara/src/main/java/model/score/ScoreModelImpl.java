package model.score;

import model.DifficultyLevel;

/**
 * 
 * This class implements {@link ScoreModel}.
 *
 */
public class ScoreModelImpl implements ScoreModel {

    /**
     * Multiplicative constant for the increment of the base point.
     */
    private static final int MULTIPLICATIVE_INCREMENT = 2;

    private final DifficultyLevel difficulty;
    private final int basePoint;
    private int increment;
    private int score;
    private boolean isFinish;

    /**
     * Constructor of ScoreModelImpl.
     * 
     * @param difficulty
     *                  level difficulty
     * @param basePoint
     *                  base point of minigame
     */
    public ScoreModelImpl(final DifficultyLevel difficulty, final int basePoint) {
        this.difficulty = difficulty;
        this.basePoint = basePoint;
        this.score = 0;
        this.isFinish = false;
        this.initializeIncrement();
    }

    private void initializeIncrement() {
        this.increment = (this.difficulty.ordinal() + this.basePoint) * MULTIPLICATIVE_INCREMENT;
    }

    private void check(final boolean b) {
        if (b) {
            throw new IllegalStateException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPoint() {
        this.check(this.isFinish);
        this.score = this.score + this.increment;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getScore() {
        this.check(this.isFinish);
        this.isFinish = true;
        return this.score;
    }
}
