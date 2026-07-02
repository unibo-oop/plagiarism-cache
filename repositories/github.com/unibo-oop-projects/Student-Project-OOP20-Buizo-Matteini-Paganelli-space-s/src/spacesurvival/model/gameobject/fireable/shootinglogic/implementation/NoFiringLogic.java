package spacesurvival.model.gameobject.fireable.shootinglogic.implementation;

import spacesurvival.model.gameobject.fireable.FireableObject;
import spacesurvival.model.gameobject.fireable.shootinglogic.FiringLogic;

/**
 * It doesn't implement any firing logic.
 */
public class NoFiringLogic implements FiringLogic {

    /**
     * Doesn't do anything.
     */
    @Override
    public void startFiring(final FireableObject fireableObject) {
    }

    /**
     * Doesn't do anything.
     */
    @Override
    public void startChangingAmmo(final FireableObject fireableObject) {
    }


}
