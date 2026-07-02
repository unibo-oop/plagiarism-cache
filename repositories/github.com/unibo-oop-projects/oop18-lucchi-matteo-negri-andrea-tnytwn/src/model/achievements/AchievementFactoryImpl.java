package model.achievements;

import model.demand.Demand;

/**
 * This class is used to implements a factory that allows the Achievements creation.
 */
public class AchievementFactoryImpl implements AchievementFactory {

    /*
     * (non-Javadoc)
     * @see model.achievements.AchievementFactory#createAchievement(java.lang.String, model.demand.Demand, model.achievements.AchievementType)
     */
    @Override
    public Achievement createAchievement(final String name, final Demand request, final AchievementType type) {
        return new AchievementImpl(name, request, type);
    }

}
