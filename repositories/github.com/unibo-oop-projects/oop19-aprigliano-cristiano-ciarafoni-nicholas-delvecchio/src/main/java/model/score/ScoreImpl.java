package model.score;

/**
 * Implementation of the Interface Score, it handles the Score.
 */
public final class ScoreImpl implements Score {

    private int score;

    /**
     * Constructor method that initializes the score to zero.
     */
    public ScoreImpl() {
        this.score = 0;
    }

    @Override
    public void setScore(final int n) {
        this.score = n;
    }

    @Override
    public void incrementScore() {
        this.score++;
    }

    @Override
    public int getScore() {
        return score;
    }
}
