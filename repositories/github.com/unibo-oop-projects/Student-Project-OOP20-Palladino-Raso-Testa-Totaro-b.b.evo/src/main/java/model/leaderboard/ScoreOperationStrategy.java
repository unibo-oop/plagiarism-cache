package model.leaderboard;

/**
 * Interface that define a generic operation performed on score.
 */
@FunctionalInterface
public interface ScoreOperationStrategy {

    int scoreOperation(int currentScore, int value);
}
