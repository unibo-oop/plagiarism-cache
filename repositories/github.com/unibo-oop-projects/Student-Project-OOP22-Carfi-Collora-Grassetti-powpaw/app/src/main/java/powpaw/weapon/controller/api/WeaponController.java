package powpaw.weapon.controller.api;

import powpaw.weapon.model.api.Weapon;
import powpaw.weapon.view.api.WeaponRender;

/**
 * Interface for WeaponControllerImpl class that controls the spawning, picking
 * up, and dropping of weapons for players in a game.
 * 
 * @author Giacomo Grassetti
 */
public interface WeaponController {

    /**
     * The function checks if a player collides with a weapon and either sets the
     * weapon to the player
     * or drops it.
     */
    void pickWeapon();

    /**
     * Getter for weapon.
     * 
     * @return The Weapon.
     */
    Weapon getWeapon();

    /**
     * Getterfor the weapon render.
     * 
     * @return A WeaponRender.
     */
    WeaponRender getRender();

}
