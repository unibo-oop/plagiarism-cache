package model.data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Represent all information of every games played.
 */
public interface GlobalData extends Serializable {

    /**
     * 
     * @return an unmodifiable map that contains achievements
     */
    Map<AchievementType, Achievement> getAchievements();

    /**
     * 
     * @return an unmodifiable list that contains high scores
     */
    List<HighScore> getHighScores();

    /**
     * 
     * @param score
     *            game's score
     * @return true if this score will be in the high scores
     */
    boolean isHighscore(int score);

    /**
     * 
     * @param gameData
     *            game data necessary to update the global data
     * @param name
     *            the player's name only if he does a new high score
     */
    void addGameData(GameData gameData, Optional<String> name);

}
