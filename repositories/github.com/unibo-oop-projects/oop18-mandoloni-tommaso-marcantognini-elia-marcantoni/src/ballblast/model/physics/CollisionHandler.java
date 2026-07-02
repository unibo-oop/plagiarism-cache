package ballblast.model.physics;

import ballblast.model.gameobjects.GameObject;

/**
 * The interface to select the correct behavior for the collision events.
 */
public interface CollisionHandler {

    /**
     * The functional method to execute the correct behavior.
     * 
     * @param coll the {@link Collidable} component that loses out the collision.
     * @param obj  the {@link GameObject} that collides.
     */
    void execute(Collidable coll, GameObject obj);

}
