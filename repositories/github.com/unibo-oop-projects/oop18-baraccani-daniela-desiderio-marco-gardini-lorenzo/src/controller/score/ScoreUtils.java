package controller.score;

/**
 * Singleton class for ScoreManager. Set a default scoreboard file path.
 */
public final class ScoreUtils {

    private static final String FILEPATH = System.getProperty("user.home") + System.getProperty("file.separator")
            + "BobbleBubbleScores.bin";
    private static final ScoreManager SCOREMANAGER = new ScoreManagerImpl(FILEPATH);

    private ScoreUtils() {
    }

    /**
     * Get the ScoreManagerImpl.
     * 
     * @return the builded {@link ScoreManager}
     */
    public static ScoreManager getScoreManager() {
        return SCOREMANAGER;
    }

    /**
     * Get the default path of the ScoreManagerImpl.
     * 
     * @return the path.
     */
    public static String getScoreboardFilePath() {
        return FILEPATH;
    }
}
