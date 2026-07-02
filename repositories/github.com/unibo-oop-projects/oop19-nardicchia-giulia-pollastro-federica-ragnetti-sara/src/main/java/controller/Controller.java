package controller;

import java.io.File;

import model.DifficultyLevel;
import view.TrainingArea;
import view.View;

/**
 * The controller interface of the whole game.
 *
 */
public interface Controller {

    /**
     * Set the first scene to show.
     *
     * @param view 
     *             the scene to show.
     */
    void initializeView(View view);

    /**
     * Set the logged in user as the player of the actual game session.
     * 
     * @param userFileName 
     *               the file's name of the current player.
     */
    void initializePlayer(File userFileName);

    /**
     * Set the selected training area.
     * 
     * @param area 
     *             the selected {@link TrainingArea}.
     */
    void setArea(TrainingArea area);

    /**
     * Set the selected minigame and difficultyLevel.
     * 
     * @param minigame 
     *                 the selected minigame
     * 
     * @param difficultyLevel 
     *                        the selected {@link DifficultyLevel}.
     */
    void setMiniGame(String minigame, DifficultyLevel difficultyLevel);

    /**
     * The selected difficultyLevel.
     * 
     */
    void selectedDifficultyLevel();

    /**
     * Save the user's data.
     */
    void saveData();

    /**
     * Add the score to the current user.
     * 
     * @param score
     *          the score to add
     */
    void updateProgress(Integer score);

    /**
     * Initialize the statistics data of the user.
     */
    void initStatistics();
}
