package org.lkyhro.gui;

/**
 * Created by Migani Luca on 29/02/2016.
 */
public interface InventoryPanelObserver {
    /**
     * Method used to terminate the action of the inventory, used in MonsterBattle and HeroDisplay class
     */
    void inventoryActionDone();
}
