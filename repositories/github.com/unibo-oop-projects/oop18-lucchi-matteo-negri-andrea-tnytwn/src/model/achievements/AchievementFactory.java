package model.achievements;

import model.demand.Demand;

/**
 * Interface of the AchievementFactory.
 */
public interface AchievementFactory {

    /**
     * The method allows the creation of an Achievement.
     * @param name
     *          the name of the Achievement
     * @param request
     *          the items requested to unlock the Achievement
     * @param type
     *          the type of the Achievement
     * @return
     *          an Achievement
     */
    Achievement createAchievement(String name, Demand request, AchievementType type);
}
