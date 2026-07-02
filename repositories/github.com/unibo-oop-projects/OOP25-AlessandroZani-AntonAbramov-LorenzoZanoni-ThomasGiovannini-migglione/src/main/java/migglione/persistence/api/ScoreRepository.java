package migglione.persistence.api;

/**
 * Interface used to handle the scores of the players.
 */
@FunctionalInterface
public interface ScoreRepository {

    /**
     * Method to write the score of the player in a file.
     * 
     * <p>
     * It will use the stored name to make sure
     * that it's the same as the winner's, so that
     * it can be written in the file of scores
     * 
     * <p>
     * Since the jar can't write, I decided to
     * use an external folder named .migglione
     * to store the txt file, it can be found
     * in the home of the user
     * 
     * @param playerName is the name of the winner player
     * @param pScore is the score of the winner player
     */
    void writeWinner(String playerName, Integer pScore);
}
