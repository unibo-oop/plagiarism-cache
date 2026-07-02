package boxhead.model.entities.zombies;

import java.util.Set;
import javafx.geometry.Point2D;

/**
 * Spawn interface methods
 *
 */
public interface Spawn {

    /**
     * 
     * @return a random Point2D spawnpoint 
     */
    Point2D getSpawnPoint();

    /**
     * Set spawn coordinates
     * @param spawnPoints spawn points set
     */
    void setSpawnPoints(Set<Point2D> spawnPoints);

	/**
	 * 
	 * return a Zombie in a spawn point
	 */
	Zombie getZombie(Point2D spawnPoint);


}
