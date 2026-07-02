package model.entity.factory;

import java.util.List;
import model.entity.DynamicEntity;
import model.entity.SpawnLevel;

/**
 * 
 * Factory Method for {@link DynamicEntity}.
 *
 */
public interface EntityFactory {

    /**
     * Create a new Obstacle's instance.
     * @param level the level on which Obstacle spawn.
     * @return a new Obstacle's instance.
     */
    DynamicEntity createObstacle(SpawnLevel level);

    /**
     * Create a new Platform's instance.
     * @param level the level on which Platform spawn.
     * @return a new Platform's instance.
     */
    DynamicEntity createPlatform(SpawnLevel level);

    /**
     * Create a new Coin's instance.
     * @param level the level on which Coin spawn.
     * @return a new Coin's instance. 
     */
    DynamicEntity createCoin(SpawnLevel level);

    /**
     * 
     * @param platformLevel the level on which Platform should spawn. 
     * @param obstacleLevel the level on which Obstacle should spawn.
     * @return a list containing Platform and Obstacle combined. 
     */
    List<DynamicEntity> combinePlatformObstacle(SpawnLevel platformLevel, SpawnLevel obstacleLevel);

    /**
     * 
     * @param platformLevel the level on which Platform should spawn. 
     * @param coinLevel the level on which Coin should spawn.
     * @return a list containing Platform and Coin combined. 
     */
    List<DynamicEntity> combinePlatformCoin(SpawnLevel platformLevel, SpawnLevel coinLevel);

    /**
     * 
     * @param obstacleLevel the level on which Obstacle should spawn. 
     * @param coinLevel the level on which Coin should spawn.
     * @return a list containing Obstacle and Coin combined. 
     */
    List<DynamicEntity> combineObstacleCoin(SpawnLevel obstacleLevel, SpawnLevel coinLevel);

    /**
     * 
     * @param platformLevel the level on which Platform should spawn.
     * @param obstacleLevel the level on which Obstacle should spawn.
     * @param coinLevel the level on which Coin should spawn. 
     * @return a list containing Platform, Obstacle and Coin combined. 
     */
    List<DynamicEntity> combineAll(SpawnLevel platformLevel, SpawnLevel obstacleLevel, SpawnLevel coinLevel);

    /**
     * Create a new PowerUp.
     * @param level the level on which the PowerUp should spawns.
     * @return a new PowerUp's instance.
     */
    DynamicEntity createPowerup(SpawnLevel level); 

}
