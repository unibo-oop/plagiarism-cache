package scores;

/**
 * FileWriter.
 *
 *
 */
public interface WriteFileInterface {

    /**
     * Create file for ranking in user.home.
     */
    void createRankingFile();

    /**
     * Create file with times in seconds in user.home.
     */
    void createTimesFile();

    /**
     * Create file with scores.
     */
    void createScoresFile();
}
