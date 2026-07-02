package tmw.controller.entities;

import tmw.model.entities.MilkEntity;
import tmw.model.inventory.Inventory;

/**
 * This interface represents the controller for the main character.
 */
public interface MilkController extends EntityController<MilkEntity> {

    /**
     * Getter for the inventory of the main character.
     * 
     * @return the inventory
     */
    Inventory getInventory();

    /**
     * Getter for the hp of the main character.
     * 
     * @return the current amount of hp
     */
    int getHp();

    /**
     * Getter for player max hp.
     * 
     * @return max hp
     */
    int getMaxHp();
}
