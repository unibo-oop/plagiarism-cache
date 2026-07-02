package it.unibo.the100dayswar.model.tower.api;

import it.unibo.the100dayswar.model.unit.api.Unit;

/**
 * Interface that rapresent a generic tower.
 */
public interface Tower extends Unit {
    /**
     * Method that returns the damage that the tower inflicts.
     * to another unit. 
     * 
     * @return the damage
     */
    int getDamage();

    /**
     * Method that returns the type of the tower.
     * 
     * @return the tower type
     */
    TowerType getTowerType();

    /**
     * Method that sets the damage of the tower.
     * 
     * @param damage the damage of the tower
     */
    void setDamage(int damage);
}
