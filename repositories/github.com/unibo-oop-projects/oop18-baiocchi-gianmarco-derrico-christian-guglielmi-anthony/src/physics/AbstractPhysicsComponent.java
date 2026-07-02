package physics;

import java.util.Set;

import model.AbstractStillObject;
import model.GameObject;

/**
 * Class to manage any entity physics.
 */
public abstract class AbstractPhysicsComponent extends PhysicComponentImpl {

    /**
     * Constructor.
     * @param entity : to manage its physics
     */
    public AbstractPhysicsComponent(final GameObject entity) {
        super(entity);
    }

    /**
     * Check a generic collision with this entity.
     * @param tile : object which to check the collision
     * @return true/false if this entity collide with the tile passed
     */
    public boolean checkGenericCollision(final GameObject tile) {
        return tile.getBounds().intersects(super.getObject().getBounds());
    }

    /**
     * Method to check any wall collision.
     * @param walls : walls on which to check the collision
     */
    public abstract void checkWallsCollisions(Set<AbstractStillObject> walls);
}
