package spacesurvival.model.gameobject.fireable;

import spacesurvival.model.EngineImage;
import spacesurvival.model.collision.bounding.BoundingBox;
import spacesurvival.model.collision.eventgenerator.EventComponent;
import spacesurvival.model.common.P2d;
import spacesurvival.model.common.V2d;
import spacesurvival.model.gameobject.fireable.shootinglogic.FiringLogic;
import spacesurvival.model.gameobject.fireable.weapon.Weapon;
import spacesurvival.model.gameobject.main.MainObject;
import spacesurvival.model.gameobject.moveable.movement.MovementLogic;

/**
 * A main object which can fire with a weapon, having a firing logic.
 */
public abstract class FireableObject extends MainObject {

    private Weapon weapon;
    private FiringLogic firingLogic;

    public FireableObject(final EngineImage engineImage, final P2d position, final BoundingBox bb,
            final EventComponent phys, final V2d velocity, final double acceleration, final MovementLogic movementLogic,
            final int life, final int impactDamage, final int score, final P2d targetPosition, final Weapon weapon,
            final FiringLogic firingLogic) {
        super(engineImage, position, bb, phys, velocity, acceleration, movementLogic, life, impactDamage, score, targetPosition);
        this.weapon = weapon;
        this.firingLogic = firingLogic;
    }

    /**
     * @return the object weapon
     */
    public Weapon getWeapon() {
        return weapon;
    }

    /**
    * Sets a new weapon to object.
    *
    * @param weapon the new weapon to set
    */
    public void setWeapon(final Weapon weapon) {
        this.weapon = weapon;
    }

    /**
     * @return the object firing logic
     */
    public FiringLogic getFiringLogic() {
        return firingLogic;
    }

    /**
     * Sets a new shooting logic to object.
     *
     * @param firingLogic the new firing logic to set
     */
    public void setFiringLogic(final FiringLogic firingLogic) {
        this.firingLogic = firingLogic;
    }

    /**
     * Start firing with the own weapon and depending by the firing logic.
     */
    public void startFiring() {
        firingLogic.startFiring(this);
        firingLogic.startChangingAmmo(this);
    }

    /**
     * Fire one shot with the own weapon.
     */
    public void fire() {
        weapon.shoot();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "FireableObject [weapon=" + weapon + ", firingLogic=" + firingLogic + super.toString() + "]";
    }

}
