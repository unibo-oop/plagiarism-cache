package org.lkyhro.enemy;

import org.lkyhro.item.Item;

/**
 * Created by Migani Luca on 11/01/2016.
 */
public interface Enemy{
    /**
     *
     * @return String name of the enemy
     */
    String getName();

    /**
     *
     * @return int attack value of the enemy
     */
    int getAttack();

    /**
     *
     * @return int defense value of the enemy
     */
    int getDefense();

    /**
     *
     * @return int amount of enemy's healts points
     */
    int getHealthPoints();

    /**
     *
     * @return int amount of experience given by the enemy
     */
    int getGivenExperience();

    /**
     *
     * @return Item dropped once defeated an enemy
     */
    Item dropItem();

}
