package zombieversity.model.entities.zombie;

import java.util.Set;

import javafx.geometry.Point2D;

/**
 * Used to manage spawn mechanic.
 *
 */
public interface Spawn {

    /**
     * 
     * @return a Point2D representing one random spawn point.
     */
    Point2D getSpawnPoint();

    /**
     * Used to set spawn points coordinates.
     * @param spawnPoints Set of all generated spawn points
     */
    void setSpawnPoints(Set<Point2D> spawnPoints);

    /**
     * Used to build a zombie, allowing to choose the tier.
     * @param spawnPoint Point rapresenting the coordinates of the spawn point.
     * @param tier of zombie to spawn.
     * @return Zombie of the given tier.
     */
    Zombie getZombieFromTier(Point2D spawnPoint, Tiers tier);

}
