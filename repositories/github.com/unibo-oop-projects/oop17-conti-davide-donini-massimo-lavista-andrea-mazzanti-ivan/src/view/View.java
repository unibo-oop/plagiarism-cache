package view;

import java.util.List;
import java.util.Map;

import model.data.Achievement;
import model.data.AchievementType;
import model.data.GameData;
import model.data.HighScore;
import utility.GameModes;
import view.controller.GameSceneController;
import view.entities.ViewEntity;
import view.scenefactory.SceneFactory;

/**
 * 
 * Represents the View initializer.
 *
 */
public interface View {

    /**
     * Launches the game view.
     */
    void viewLauncher();

    /**
     * 
     * @param gameSceneController
     *            javaFX controller of game scene.
     * 
     * @param gameMode
     *            the game mode that must be loaded.
     */
    void startGame(GameSceneController gameSceneController, GameModes gameMode);

    /**
     * Start thread that renders view.
     */
    void startViewRender();

    /**
     * Stop thread that renders view.
     */
    void stopViewRender();

    /**
     * 
     * Close game scene.
     * 
     * @param gameData
     *            the data relative to the game just finished.
     * @param isHighScore
     *            true if the new score is an high score
     */
    void closeGame(GameData gameData, boolean isHighScore);

    /**
     * Causes the game to reset.
     */
    void resetGame();

    /**
     * 
     * @param gameEntities
     *            games entities
     * 
     * @param gameData
     *            current game data
     */
    void setStateGame(List<ViewEntity> gameEntities, GameData gameData);

    /**
     * 
     * @param achievements
     *            current achievements
     */
    void setAchievements(Map<AchievementType, Achievement> achievements);

    /**
     * 
     * @param highScores
     *            current high scores
     */
    void setHighScores(List<HighScore> highScores);

    /**
     * 
     * @return a collection that contains high scores
     */
    Map<AchievementType, Achievement> getAchievements();

    /**
     * 
     * @return a collection that contains high scores
     */
    List<HighScore> getHighScores();

    /**
     * 
     * @return a collection that contains high scores
     */
    GameData getGameData();

    /**
     * 
     * @return the factory used to change page into the view
     */
    SceneFactory getSceneFactory();

}
