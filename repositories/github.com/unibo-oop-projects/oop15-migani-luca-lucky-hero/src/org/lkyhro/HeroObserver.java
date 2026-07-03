package org.lkyhro;

/**
 * Created by  Migani Luca on 26/02/2016.
 */
public interface HeroObserver {
    /**
     *
     * @param levelUpCounter number of level-ups hero has accumulated by getting experience
     */
    void levelUp(int levelUpCounter);
}
