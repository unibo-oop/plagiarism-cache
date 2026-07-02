package it.unibo.javajump.model.score;

import static it.unibo.javajump.utility.Constants.SCORE_INIT;

/**
 * The implementation of the ScoreManager interface.
 */
public final class ScoreManagerImpl implements ScoreManager {

    private int currentScore;
    private int bestScore;
    private boolean bestScoreReached;

    /**
     * Instantiates a new Score manager.
     */
    public ScoreManagerImpl() {
        this.currentScore = SCORE_INIT;
        this.bestScore = SCORE_INIT;
        this.bestScoreReached = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPoints(final int amount) {
        this.currentScore += amount;

        if (this.currentScore > this.bestScore) {
            this.bestScore = this.currentScore;
            this.bestScoreReached = true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCurrentScore() {
        return currentScore;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getBestScore() {
        return bestScore;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isBestScoreReached() {
        return bestScoreReached;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        this.currentScore = SCORE_INIT;
        this.bestScoreReached = false;
    }
}
