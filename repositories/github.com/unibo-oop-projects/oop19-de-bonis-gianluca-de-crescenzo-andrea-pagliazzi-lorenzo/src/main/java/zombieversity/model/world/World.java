package zombieversity.model.world;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javafx.geometry.Point2D;
import zombieversity.model.entities.passive.PassiveEntity;
/**
 * Interface that models a world made by blocks.
 *
 */
public interface World {
    /**
     * Method to get all the blocks approximated of the world.
     * @return Map<Point2D, Integer> - where key is the position and value is the type of the block.
     */
    Map<Point2D, Integer> getBlocks();
    /**
     * Method to get all the obstacles on the world.
     * @return List<PassiveEntity> - could be size() = 0 if no obstacles in the world.
     */
    List<? extends PassiveEntity> getObstacles();
    /**
     * Method to get all the zombieSpawns.
     * @return List<PassiveEntity> - at least size() = 1.
     */
    List<? extends PassiveEntity> getZombieSpawnPoints();
    /**
     * Method to get the PlayerSpawn if present.
     * @return Optional<PassiveEntity> - optional because could empty if no playerspawn.
     */
    Optional<? extends PassiveEntity> getPlayerSpawnPoint();
    /**
     * Get The width of the map.
     * @return double
     */

    double getWidth();
    /**
     * Get The Height of the map.
     * @return double
     */
    double getHeight();
    /**
     * Block size, it will represent how far is the position of a block from another.
     * @return double
     */
    double getTileSize();
    void setScale(double scale);
}
