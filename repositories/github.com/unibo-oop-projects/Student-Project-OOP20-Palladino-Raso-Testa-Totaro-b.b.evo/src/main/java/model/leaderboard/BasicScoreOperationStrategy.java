package model.leaderboard;

public class BasicScoreOperationStrategy implements ScoreOperationStrategy {

    /**
     * This method represent a basic addition or subtraction on score.
     * @param currentScore - represent the current player score.
     * @param value - represent the value to compute.
     * @return a calculated number resulting by the operation, but return 0 if the resulting be negative.
     */
    @Override
    public int scoreOperation(final int currentScore, final int value) {
        int result;
        if (value < 0 && (currentScore + value) < 0) {
            result = 0;
        } else {
            result = currentScore + value;
        }
        return result;
    }

}
