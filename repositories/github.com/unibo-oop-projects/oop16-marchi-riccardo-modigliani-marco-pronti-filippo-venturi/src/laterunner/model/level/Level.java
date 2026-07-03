package laterunner.model.level;

import java.util.List;

import laterunner.model.vehicle.Obstacle;

/**
 * The interface in witch is defined the levels.
 *
 */
public interface Level {

    /**
     * Set the list of obstacle of the level.
     * 
     * @param list
     *          the list of obstacle to be set 
     */
    void setLevel(final List<Obstacle> list);

    /**
     * Get the level's obstacle list. 
     *
     * @return
     *          the level's list
     */
    List<Obstacle> getLevel();

}
