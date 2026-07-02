package model.components;

import java.util.Objects;

/**
 * 
 * Models a score that decreases by one every second and
 * that computes the point based on the typed letters.
 * It stops decreasing for two seconds during the pause between two words' sets.
 *
 */
public final class ScoreImpl implements Score {

    private static final int LETTER_VALUE = 2;
    private static final int DECREASING_VALUE = 1;
    private static final int ONE_SECOND_PAUSE = 1000;
    private int globalScore;
    private int combo = 1;
    private boolean isPaused;

    /**
     * {@inheritDoc}
     */
    @Override
    public int getGlobalScore() {
        return this.globalScore;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPoints(final int typedLetters) {
        final int totLetters = Objects.requireNonNull(typedLetters, "You need to give at least one point, null means nothing to me");
        this.globalScore += this.computePoints(totLetters);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void incCombo() {
        this.combo++;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetCombo() {
        this.combo = 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        this.resetGlobalScore();
        this.resetCombo();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setWordSetPause(final boolean pauseStatus) {
        this.isPaused = pauseStatus;
    }

    /**
     * Decreases the global score by one every second.
     * If the current set of words has ended, it pauses for a two seconds.
     */
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            this.decreaseScore();
            this.checkWordSetPause(this.isPaused);
            this.setWordSetPause(false);
            this.sleepForX(ONE_SECOND_PAUSE);
        }
    }

    @Override
    public String toString() {
        return Integer.toString(this.globalScore);
    }

    private void decreaseScore() {
        if (this.globalScore > 0) {
            this.globalScore -= DECREASING_VALUE;
        }
    }

    private int computePoints(final int letters) {
        return letters * LETTER_VALUE * this.combo;
    }

    private void resetGlobalScore() {
        this.globalScore = 0;
    }
}
