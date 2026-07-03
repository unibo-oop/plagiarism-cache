package view.controller;

import java.util.Map;

import model.data.Achievement;
import model.data.AchievementType;

/**
 * Represent the scene for achievements.
 */
public interface AchievementsSceneController {

    /**
     * 
     * @param achievements
     *            game's achievements
     */
    void setAchievements(Map<AchievementType, Achievement> achievements);

}
