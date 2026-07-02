package it.unibo.the100dayswar.model.unit.api;

import java.io.Serializable;

/**
 * An interface for a buyable and upgradable object.
 */
public interface Buyable extends Serializable {
    /**
     * This method returns the cost to buy the object.
     * 
     * @return the cost to buy
     */
    int getBuyCost();

    /**
     * This method returns the cost to upgrade of the object.
     * 
     * @return the cost to upgrade
     */
    int getUpgradeCost();

    /**
     * This method returns the level of the object.
     * 
     * @return the level
     */
    int getLevel();

    /**
     * This method is used to set the level of the object.
     * 
     * @param level the level to set
     */
    void setLevel(int level);

    /**
     * This method is used to check if the object can be upgraded.
     * 
     * @return true if the object can be upgraded
     */
    boolean canUpgrade();

    /**
     * Upgrade the object if it is possible.
     * 
     * @throws IllegalStateException if it is not possible upgrade the object
     */
    void upgrade();

    /**
     * Downgrade the object if it is possible otherwise it will destroy it.
     */
    void downgrade();
}
