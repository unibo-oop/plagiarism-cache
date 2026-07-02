package spacesurvival.model.collision.event;

import spacesurvival.model.World;
import spacesurvival.model.gameobject.GameObject;
import spacesurvival.model.worldevent.WorldEvent;

/**
 * Dead event.
 */
public class DeadEvent implements WorldEvent {
    private final GameObject obj;
    private final EventType type = EventType.DEAD_EVENT;

    /**
     * Constructor for new DeadEvent, generated after the collision to notify the dead of an object to the world.
     * 
     * @param obj the game object representing the dead object
     */
    public DeadEvent(final GameObject obj) {
        this.obj = obj;
    }

    /**
     * Returns the specific dead object.
     * 
     * @return the specified dead object
     */
    public GameObject getDeadObj() {
        return this.obj;
    }

    /**
     * Returns a dead event type.
     * 
     * @return an EventType for the specified event
     */
    @Override
    public EventType getType() {
        return this.type;
    }

    /**
     * Manage the specific behaviour for this type of event.
     * 
     * @param world the current world
     */
    @Override
    public void manage(final World world) {
        this.getDeadObj().collided(world, this);
    }


}
