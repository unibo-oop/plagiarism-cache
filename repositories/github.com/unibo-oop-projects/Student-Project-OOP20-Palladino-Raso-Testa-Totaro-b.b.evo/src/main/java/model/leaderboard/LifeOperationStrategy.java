package model.leaderboard;

/**
 * Interface that define a generic operation performed on player life.
 */
@FunctionalInterface
public interface LifeOperationStrategy {

    int lifeOperation(int currentLife, int value, int maxLife);
}
