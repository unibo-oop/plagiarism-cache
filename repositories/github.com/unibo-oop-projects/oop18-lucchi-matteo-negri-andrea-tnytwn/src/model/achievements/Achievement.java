package model.achievements;

import model.observer.Observer;

/**
 * Interface of an Achievement.
 */
public interface Achievement extends Observer {
    /**
     * The method return the type of the achievement.
     * @return
     *          the type of the achievement
     */
    AchievementType getType();

    /**
     * The method return the name of the achievement.
     * @return
     *          the name of the achievement
     */
    String getName();

    /**
     * The method return the status of the Achievement.
     * @return
     *          true:   if the Achievements is unlock
     *          false:  if the Achievements is lock
     */
    boolean isUnlock();
}
