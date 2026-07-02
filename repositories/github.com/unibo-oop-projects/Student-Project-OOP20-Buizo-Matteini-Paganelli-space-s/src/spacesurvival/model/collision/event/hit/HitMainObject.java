package spacesurvival.model.collision.event.hit;

import spacesurvival.model.World;
import spacesurvival.model.collision.event.EventType;
import spacesurvival.model.gameobject.main.MainObject;
import spacesurvival.model.worldevent.WorldEvent;

/**
 * Hit event for the main object type.
 */
public class HitMainObject implements WorldEvent {
    private final MainObject object;
    private final MainObject collidedObject;
    private final EventType type = EventType.HIT_MAIN_OBJECT;

    /**
     * Constructor for new HitMainGameObject, generated after the collision with MainGameObject to notify the world.
     * 
     * @param object the MainGameObject representing the first main game object
     * @param collidedObject the MainGameObject representing the second main game object
     */
    public HitMainObject(final MainObject object, final MainObject collidedObject) {
        this.object = object;
        this.collidedObject = collidedObject;
    }

    /**
     * Returns the first collided MainGameObject.
     * 
     * @return the specified MainGameObject
     */
    public MainObject getObject() {
        return this.object;
    }

    /**
     * Returns the second collided MainGameObject.
     * 
     * @return the specified MainGameObject
     */
    public MainObject getCollidedObject() {
        return this.collidedObject;
    }

    /**
     * Returns a main game object event type.
     * 
     * @return an EventType for this event
     */
    public EventType getType() {
        return this.type;
    }

    /**
     * Manage the main game object event.
     * 
     * @param world the current world
     */
    public void manage(final World world) {
        this.getObject().collided(world, this);
        this.getCollidedObject().collided(world, this);
    }

}
