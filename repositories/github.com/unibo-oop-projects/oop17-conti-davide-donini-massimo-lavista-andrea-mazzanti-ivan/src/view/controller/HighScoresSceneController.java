package view.controller;

import java.util.List;

import model.data.HighScore;

/**
 * 
 * Represent the scene for high score.
 *
 */
public interface HighScoresSceneController {

    /**
     * 
     * @param highScores
     *            high scores to print.
     */
    void setHighScores(List<HighScore> highScores);

}
