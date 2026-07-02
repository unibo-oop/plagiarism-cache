package zombieversity.model.score;

import java.util.List;

/**
 * Class used to keep track of the Scores of all the games played locally.
 */
public interface Leaderboard {

    /**
     * Used to handle a new {@link Score} that will be effectively added to the Leaderboard by calling the method updateLeaderboard().
     * @param score
     *          The new score to be added to the Leaderboard.
     */
    void handleScore(Score score);

    /**
     * @return
     *          The last score added to the Leaderboard.
     */
    Score getLastScore();

    /**
     * Method to definitively add the last score handled to the Leaderboard. 
     */
    void updateLeaderboard();

    /**
     * @return
     *          The list of the scores present in the Leaderboard ordered by its position.
     */
    List<Score> getLeaderboard();

}
