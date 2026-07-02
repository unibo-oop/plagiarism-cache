package controller.leaderboard;

import java.util.List;

/**
 * This is the Controller associated to the Leaderboard. It sets the "back"
 * button's function and reads the content of the file leaderboard.txt allowing
 * the scores to be displayed in the view.
 */
public interface LeaderboardController {

    /**
     * @return a list with each String containing a line of the file.
     */
    List<String> loadScore();

    /**
     * @return true if the file exists.
     */
    boolean isFileThere();

    /**
     * Goes back to the main menu.
     */
    void goBackHit();
}
