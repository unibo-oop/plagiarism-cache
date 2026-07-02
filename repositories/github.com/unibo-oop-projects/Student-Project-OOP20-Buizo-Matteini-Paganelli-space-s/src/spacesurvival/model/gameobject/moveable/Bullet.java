
package spacesurvival.model.gameobject.moveable;

import spacesurvival.model.common.P2d;
import spacesurvival.model.common.V2d;
import spacesurvival.model.gameobject.fireable.FireableObject;
import spacesurvival.model.gameobject.fireable.SpaceShipSingleton;
import spacesurvival.model.gameobject.fireable.weapon.Effect;
import spacesurvival.model.gameobject.fireable.weapon.Weapon;
import spacesurvival.model.gameobject.main.MainObject;
import spacesurvival.model.gameobject.moveable.movement.implementation.FixedMovementLogic;
import spacesurvival.model.worldevent.WorldEvent;
import java.util.Optional;
import spacesurvival.model.EngineImage;
import spacesurvival.model.World;
import spacesurvival.model.collision.bounding.BoundingBox;
import spacesurvival.model.collision.bounding.RectBoundingBox;
import spacesurvival.model.collision.event.EventType;
import spacesurvival.model.collision.event.hit.HitBulletEvent;
import spacesurvival.model.collision.eventgenerator.EventComponent;

/**
 * A bullet fired from a weapon, it has a fixed velocity and direction.
 */
public class Bullet extends MoveableObject {

    private int damage;
    private Effect effect;
    private final Weapon originWeapon;

    public Bullet(final EngineImage engineImage, final P2d position, final BoundingBox bb, final EventComponent eventComponent,
            final V2d velocity, final double acceleration, final P2d target, final int damage,
            final Effect effect, final Weapon originWeapon) {
        super(engineImage, position, bb, eventComponent, velocity, acceleration, new FixedMovementLogic(), target);
        this.setBoundingBox(RectBoundingBox.createRectBoundingBox(position, engineImage, this.getTransform()));
        this.damage = damage;
        this.effect = effect;
        this.originWeapon = originWeapon;
        this.stopAnimation();
    }

    /**
     * @return the bullet damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Sets a new damage to the bullet.
     * 
     * @param damage the damage to set
     */
    public void setDamage(final int damage) {
        this.damage = damage;
    }

    /**
     * @return the effect which applies the bullet to a collided object
     */
    public Effect getEffect() {
        return effect;
    }

    /**
     * Sets a new effect to the bullet.
     * 
     * @param effect the effect to set
     */
    public void setEffect(final Effect effect) {
        this.effect = effect;
    }

    /**
     * @return the weapon from which the bullet was fired
     */
    public Weapon getOriginWeapon() {
        return originWeapon;
    }

    /**
     * @return the FireableObject which has fired the bullet
     */
    public FireableObject getShooter() {
        return this.originWeapon.getOwner();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void collided(final World world, final WorldEvent ev) {
        final Optional<EventType> evType = EventType.getEventFromHit(ev);
        if (evType.isPresent()) {
            switch (EventType.getEventFromHit(ev).get()) {
            case HIT_BORDER:
                world.removeBullet(this);
                break;
            case HIT_BULLET:
                final HitBulletEvent bulletEvent = (HitBulletEvent) ev;
                final MainObject collidedObject = bulletEvent.getCollidedObject();
                if (!this.getShooter().equals(collidedObject)) {
                    if (collidedObject instanceof SpaceShipSingleton && !collidedObject.isInvincible()) {
                        world.getQueueDecreaseLife().add(collidedObject.getImpactDamage());
                        collidedObject.setStatus(this.getEffect().getStatus());
                    } else {
                        world.damageObject(collidedObject, this.getDamage(), this.getEffect().getStatus());
                    }
                    world.removeBullet(this);
                }
                break;
            default:
                break;
            }
        }

    }

}
