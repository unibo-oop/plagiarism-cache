package spacesurvival.model.gameobject.fireable.shootinglogic.implementation;

import spacesurvival.model.gameobject.fireable.FireableObject;
import spacesurvival.model.gameobject.fireable.shootinglogic.FiringLogic;
import spacesurvival.model.gameobject.main.Status;
import spacesurvival.utilities.Delay;
import spacesurvival.utilities.ThreadUtils;

/**
 * Implement firing logic for fire enemies.
 * The object can shoot but cannot change ammo.
 */
public class EnemyFiringLogic implements FiringLogic {

    /**
     * Start the fire enemy firing every 5 seconds.
     * 
     * @param fireableObject fire enemy which has to shoot with its weapon
     */
    @Override
    public void startFiring(final FireableObject fireableObject) {
        new Thread(() -> {
            while (fireableObject.isAlive()) {
                ThreadUtils.sleep(Delay.FIRE_ENEMY_FIRING);
                if (fireableObject.getStatus() != Status.PARALYZED) {
                    fireableObject.fire();
                }
            }
        }).start();
    }

    /**
     * Doesn't do anything.
     */
    @Override
    public void startChangingAmmo(final FireableObject fireableObject) {
    }

}
