package it.unibo.isaccoop.model.creator;

import it.unibo.isaccoop.model.enemy.Enemy;
import it.unibo.isaccoop.model.item.Item;
import it.unibo.isaccoop.model.powerup.PowerUp;

/**
 * CreatorFactory interface to model a factory of creator concept.
 * */
public interface CreatorFactory {

    /**
     * Method to get an enemy creator.
     *
     * @return an enemy creator
     * */
    Creator<Enemy> createEnemies();

    /**
     * Method to get a boss creator.
     *
     * @return a boss creator
     * */
    Creator<Enemy> createBoss();

    /**
     * Method to get a shop power up creator.
     *
     * @return a shop power up creator
     * */
    Creator<PowerUp> createShopPowerUps();

    /**
     * Method to get a treasure power up creator.
     *
     * @return a treasure power up creator
     * */
    Creator<PowerUp> createTreasurePowerUps();

    /**
     * Method to get an items creator.
     *
     * @return an items creator
     * */
    Creator<Item> createItems();

}
