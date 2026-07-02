package view;

import model.DifficultyLevel;
import model.score.Progress;

/**
 * The interface for the visual representation of the application.
 *
 */
public interface View {

    /**
     * 
     * @param path 
     *              the next {@link SceneType} to show.
     */
    void switchScene(String path);

    /**
     * 
     * @param path 
     *             the minigame scene to show
     */
    void setMiniGameScene(String path);

    /**
     * Initialize the {@link Progress} of the User.
     * 
     * @param path
     *             the minigame handle data scene to show
     */
    void loadProgress(String path);

    /**
     * Load the {@link Progress} of the User.
     *  @param progress
     *          the  {@link Progress} of the User
     */
    void initStatistics(Progress progress);

    /**
     * 
     * @param difficultyLevel
     *          the current {@link difficultyLevel}
     */
    void initGame(DifficultyLevel difficultyLevel);
}
