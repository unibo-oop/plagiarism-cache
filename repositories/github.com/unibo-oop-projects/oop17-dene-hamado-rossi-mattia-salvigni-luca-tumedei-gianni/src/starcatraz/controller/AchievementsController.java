package starcatraz.controller;

import starcatraz.model.Achievements;

/**
 * Achievements controller.
 */
public interface AchievementsController {

    /**
     * Close Achievements window.
     */
    void closeAchievementsButtonClick();

    /**
     * Set Achievements.
     * @param achievement
     */
    void setAchievements(Achievements achievement);
}
