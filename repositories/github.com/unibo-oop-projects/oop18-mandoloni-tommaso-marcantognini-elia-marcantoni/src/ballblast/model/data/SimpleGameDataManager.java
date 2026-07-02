package ballblast.model.data;

/**
 * Concrete implementation of {@link GameDataManager}.
 */
public class SimpleGameDataManager implements GameDataManager {
    private final GameData gameData;

    /**
     * Class constructor.
     */
    public SimpleGameDataManager() {
        this.gameData = new GameData();
    }

    @Override
    public final void incrementDestroyedBalls() {
        this.gameData.setDestroyedBalls(this.gameData.getDestroyedBalls() + 1);
    }

    @Override
    public final void incrementSpawnedBullets() {
        this.gameData.setSpawnedBullets(this.gameData.getSpawnedBullets() + 1);
    }

    @Override
    public final void updateGameTime(final double elapsed) {
        this.gameData.setGameTime(this.gameData.getTime() + elapsed);
    }

    @Override
    public final void calculateScore(final int score) {
        this.gameData.setScore(score);
    }

    @Override
    public final GameData getGameData() {
        return this.gameData;
    }
}
