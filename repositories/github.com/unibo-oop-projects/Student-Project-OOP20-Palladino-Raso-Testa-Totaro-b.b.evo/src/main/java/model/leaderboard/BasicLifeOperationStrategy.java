package model.leaderboard;

public class BasicLifeOperationStrategy implements LifeOperationStrategy {

    /**
     * This method represent a basic addition or subtraction on life.
     * @param currentLife - represent the current player life.
     * @param value - represent the value to compute.
     * @param maxLife - represent the max life that player can have.
     * @return a calculated number resulting by the operation, but return 0 if the resulting be negative,
     * and return max life value if the operation overflow the max life permitted.
     */
    @Override
    public int lifeOperation(final int currentLife, final int value, final int maxLife) {
        int result = currentLife + value;
        if (result > maxLife) {
            result = maxLife;
        }
        if (result < 0) {
            result = 0;
        }
        return result;
    }

}
