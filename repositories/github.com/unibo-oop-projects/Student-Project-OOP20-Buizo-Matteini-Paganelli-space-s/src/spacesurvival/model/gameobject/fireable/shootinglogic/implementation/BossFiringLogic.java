package spacesurvival.model.gameobject.fireable.shootinglogic.implementation;

import spacesurvival.model.gameobject.fireable.FireableObject;
import spacesurvival.model.gameobject.fireable.shootinglogic.FiringLogic;
import spacesurvival.model.gameobject.main.Status;
import spacesurvival.model.gameobject.takeable.ammo.AmmoType;
import spacesurvival.utilities.Delay;
import spacesurvival.utilities.ThreadUtils;

/**
 * Implement firing logic for boss.
 * The boss can shoot and randomly change the ammo type of its weapon.
 */
public class BossFiringLogic implements FiringLogic {

    /**
     * Start the boss firing every 3 seconds.
     * 
     * @param fireableObject boss which has to shoot with its weapon
     */
    @Override
    public void startFiring(final FireableObject fireableObject) {
        new Thread(() -> {
            while (fireableObject.isAlive()) {
                ThreadUtils.sleep(Delay.BOSS_FIRING);
                if (fireableObject.getStatus() != Status.PARALYZED) {
                    fireableObject.fire();
                }
            }
        }).start();
    }

    /**
     * Start the boss changing ammo every 20 seconds.
     * 
     * @param fireableObject object which which will fire
     */
    @Override
    public void startChangingAmmo(final FireableObject fireableObject) {
        new Thread(() -> {
            while (fireableObject.isAlive()) {
                ThreadUtils.sleep(Delay.BOSS_CHANGING_AMMO);
                fireableObject.getWeapon().setAmmoType(AmmoType.randomExceptNormal());
            }
        }).start();
    }

}
