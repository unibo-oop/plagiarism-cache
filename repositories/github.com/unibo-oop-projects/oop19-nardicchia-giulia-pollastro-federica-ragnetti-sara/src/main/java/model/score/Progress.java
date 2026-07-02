package model.score;

import java.util.Map;

import view.TrainingArea;

/**
 * 
 * This interface represents the user's progress.
 *
 */
public interface Progress {

    /**
     * The user's progress in the history.
     * 
     * @return the score associated at each minigame to every area
     */
    Map<TrainingArea, Map<String, ScoreManagementImpl>> getHistoryScore();

    /**
     * The user's average progress for each area.
     * 
     * @return the average of the scores at each area
     */
    Map<TrainingArea, Integer> getAverageTrainingAreaScore();

    /**
     * Add the score of the minigame obtained.
     * 
     * @param area     the training area
     * @param miniGame the minigame played
     * @param score    the score gained
     */
    void addMiniGameScore(TrainingArea area, String miniGame, int score);

    /**
     * 
     * @return the score of the minigame played
     */
    int getCurrentScore();

    /**
     * 
     * @return the best score of the minigame played
     */
    int getCurrentBest();
}
