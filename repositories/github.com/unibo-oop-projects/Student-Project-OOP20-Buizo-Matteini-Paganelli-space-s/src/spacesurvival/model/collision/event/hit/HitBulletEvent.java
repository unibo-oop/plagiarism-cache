package spacesurvival.model.collision.event.hit;

import spacesurvival.model.World;
import spacesurvival.model.collision.event.EventType;
import spacesurvival.model.gameobject.main.MainObject;
import spacesurvival.model.gameobject.moveable.Bullet;
import spacesurvival.model.worldevent.WorldEvent;

/**
 * Hit event for the bullet type.
 */
public class HitBulletEvent implements WorldEvent {
    private final Bullet bullet;
    private final MainObject collidedObject;
    private final EventType type = EventType.HIT_BULLET;

    /**
     * Constructor for new HitBulletEvent, generated after the collision to notify the world.
     * 
     * @param bullet Bullet representing the bullet.
     * @param collidedObject MainGameObject represents the object that collided with the bullet.
     */
    public HitBulletEvent(final Bullet bullet, final MainObject collidedObject) {
        this.bullet = bullet;
        this.collidedObject = collidedObject;
    }

    /**
     * Returns the collided MainGameObject.
     * 
     * @return the specified MainGameObject.
     */
    public MainObject getCollidedObject() {
        return this.collidedObject;
    }

    /**
     * Returns the Bullet that collided.
     * 
     * @return the specified Bullet.
     */
    public Bullet getBullet() {
        return this.bullet;
    }

    /**
     * Returns a bullet event type.
     * 
     * @return an EventType for this event
     */
    @Override
    public EventType getType() {
        return this.type;
    }


    /**
     * Manage the bulled game object event.
     * 
     *@param world the current world
     */
    @Override
    public void manage(final World world) {
        this.getBullet().collided(world, this);
    }

}
