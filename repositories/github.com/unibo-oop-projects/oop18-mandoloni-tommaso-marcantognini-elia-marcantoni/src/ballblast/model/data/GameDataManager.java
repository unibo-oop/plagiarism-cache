package ballblast.model.data;

/**
 * Keeps all game data up to date.
 */
public interface GameDataManager {

    /**
     * Increments the destroyed balls counter.
     */
    void incrementDestroyedBalls();
    /**
     * Increments the spawned bullets counter.
     */
    void incrementSpawnedBullets();

    /**
     * Keeps the game time.
     * 
     * @param elapsed the time elapsed since last update.
     */
    void updateGameTime(double elapsed);

    /**
     * Keeps the game score.
     * 
     * @param score the score calculated during the current update.
     */
    void calculateScore(int score);

    /**
     * Gets the {@link GameData} associated.
     * 
     * @return the {@link GameData}.
     */
    GameData getGameData();
}
