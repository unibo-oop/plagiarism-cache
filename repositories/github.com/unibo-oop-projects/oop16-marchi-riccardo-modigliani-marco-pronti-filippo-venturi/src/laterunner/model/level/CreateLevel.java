package laterunner.model.level;

import java.util.List;

import laterunner.model.vehicle.Vehicles;
import laterunner.physics.S2d;

/**
 * The interface in witch is defined the method of levels' creation.
 *
 */
public interface CreateLevel {

    /**
     * Creates the level to be played.
     * 
     * @param initialList
     *          the list of enum that define the obstacle to create
     * @param speed
     *          the speed that Obstacle must have
     * @param distance
     *          the gap between DISTANCE that make the distance between two Obstacle
     * @return
     *          the level
     */
    Level generateLevel(final List<Vehicles> initialList, final S2d speed, final int distance);

}