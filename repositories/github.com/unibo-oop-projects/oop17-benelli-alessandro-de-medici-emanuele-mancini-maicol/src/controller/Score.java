package controller;

import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * ScoreImpl interface.
 *
 */
public interface Score {

    /**
     * Starts timer that increments time.
     */
    void startScore();

    /**
     * Adds score's text to the chosen Pane.
     * 
     * @param root
     *            the chosen Pane
     */
    void addScoreToPane(Pane root);

    /**
     * Adds leaderboard's text to the chosen Pane.
     * 
     * @param root
     *            the chosen Pane
     */
    void addLeaderboardToPane(Pane root);

    /**
     * Getter of the score's text.
     * 
     * @return textScore
     */
    Text getText();

    /**
     * Stops timer.
     */
    void stopTimer();

    /**
     * Resets timer.
     */
    void resetTimer();

    /**
     * Calls a method to save score in the file and a method to load and read scores
     * from the file.
     */
    void leaderboard();

    /**
     * Getter of the time.
     * 
     * @return time
     */
    double getTime();
}
