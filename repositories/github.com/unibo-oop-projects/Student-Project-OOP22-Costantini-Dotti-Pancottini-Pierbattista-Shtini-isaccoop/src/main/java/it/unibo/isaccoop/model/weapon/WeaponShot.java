package it.unibo.isaccoop.model.weapon;

import it.unibo.isaccoop.model.common.MapElement;

/**
 * WeaponShot interface that models the weapon shot concept.
 * */
public interface WeaponShot extends MapElement {

    /**
     * Method to update the weapon shot state.
     * */
    void tickShot();
}
