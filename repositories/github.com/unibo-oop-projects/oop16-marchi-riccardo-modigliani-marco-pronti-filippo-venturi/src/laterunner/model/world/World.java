package laterunner.model.world;

import java.util.List;

import laterunner.model.collisions.WorldEventListener;
import laterunner.model.vehicle.Car;
import laterunner.model.vehicle.Obstacle;
import laterunner.model.vehicle.Vehicle;

/**
 * The interface in witch is defined the world features.
 *
 */
public interface World {

    /**
     *  Set the WorldEventListener.
     *
     * @param l
     *          World event listener
     */
    void setEventListener(final WorldEventListener l);

    /**
     * Remove an obstacle from the obstacle list. 
     * 
     * @param obj
     *          Obstacle to be removed
     */
    void removeObstacle(final Obstacle obj);

    /**
     * Returns scene's entities' list.
     * 
     * @return
     *          scene's entities' list
     */
    List<Vehicle> getSceneEntities();

    /**
     * Updtate the world entities.
     * 
     * @param elapsed
     *          Time elapsed
     */
    void updateState(final int elapsed);

    /**
     * Generete the level by the level's number.
     * 
     * @param i
     *          Level's number
     */
    void generateLevel(final int i);

    /**
     * Get the User's car.
     * 
     * @return
     *          User's car
     */
    Car getCar();

    /**
     *  Get the border damage.
     * @return
     *          return the border damage
     */
    int getBorderDamage();

}