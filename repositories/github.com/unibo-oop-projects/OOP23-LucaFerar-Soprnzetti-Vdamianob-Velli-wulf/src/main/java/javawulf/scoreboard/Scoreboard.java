package javawulf.scoreboard;

import java.io.File;
import java.util.List;

/**
 * Scoreboard stores all the results of past games and the scores of the players.
 */
public interface Scoreboard {

    /**
     * Maximum Results in the scoreboard.
     */
    int SCOREBOARD_SIZE = 10;

    /**
     * File path for saving and reading the Scoreboard.
     */
    String FILE_PATH = new File(System.getProperty("user.dir") + File.separator + "Scoreboard.txt").getPath();

    /**
     * Updates the scoreboard.
     * @param result new result that gets added
     */
    void addNewScore(Result result);

    /**
     * Saves the scoreboard.
     */
    void saveScoreBoard();

    /**
     * Loads the scoreboard from file.
     */
    void loadScoreBoardFromFile();

    /**
     * 
     * @return all the scores presents
     */
    List<Result> getAllScores();

}
