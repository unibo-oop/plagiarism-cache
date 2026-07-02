package spacesurvival.model.gameobject.fireable.shootinglogic;

import spacesurvival.model.gameobject.fireable.FireableObject;

/**
 * Implements the firing logic based on the game object.
 */
public interface FiringLogic {

    /**
     * Starts the game object's weapon shoot with a certain frequency.
     * 
     * @param fireableObject object which has to shoot with its weapon
     */
    void startFiring(FireableObject fireableObject);

    /**
     * Define the frequency to change automatically ammo type randomly while object is alive.
     * 
     * @param fireableObject object which has to change ammo type to its weapon
     */
    void startChangingAmmo(FireableObject fireableObject);

}
