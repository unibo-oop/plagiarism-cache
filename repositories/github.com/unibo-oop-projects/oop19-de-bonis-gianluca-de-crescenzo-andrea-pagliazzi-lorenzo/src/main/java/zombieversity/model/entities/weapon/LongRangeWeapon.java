package zombieversity.model.entities.weapon;

/**
 *  Represents every kind of long distance weapon like guns, rifles, shotguns...
 */
public interface LongRangeWeapon extends ShortRangeWeapon {

    /**
     * Starts the action of recharging the weapon.
     */
    void startRecharging();

    /**
     * 
     * @return True if the weapon is still recharging, false otherwise.
     */
    boolean isRecharging();

    /**
     * 
     * @return The amount of ammo present in the magazine at the actual moment.
     */
    int getActualAmmo();

}
