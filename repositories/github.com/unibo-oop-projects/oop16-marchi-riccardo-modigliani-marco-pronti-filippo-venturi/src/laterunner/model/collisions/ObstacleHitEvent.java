package laterunner.model.collisions;

import laterunner.model.vehicle.Obstacle;

/**
 * Obstacle hit event.
 */
public class ObstacleHitEvent implements WorldEvent {

    private Obstacle o;

    /**
     * Istantiates a new obstacle hit event.
     * 
     * @param obj
     *          obstacle hit
     */
    public ObstacleHitEvent(final Obstacle obj) {
            this.o = obj;
    }

    /**
     * Returns the obstacle hit.
     * 
     * @return
     *          obstacle hit
     */
    public Obstacle getCollisionObj() {
            return o;
    }
}
