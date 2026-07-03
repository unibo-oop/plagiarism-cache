package laterunner.model.collisions;

import laterunner.model.vehicle.Car;

/**
 * Object's box used to manage collisions.
 */
public interface BoundingBox {

    /**
     * Checks if an item is colliding with user's car.
     * 
     * @param car
     *          user's car
     * @return
     *          true if there is a collision
     */
    boolean isCollidingWith(final Car car);
}
