package zombieversity.model.entities.zombie;

import java.util.Set;

import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import zombieversity.model.entities.Player;

/**
 * Used to handle zombies horde.
 *
 */
public interface ZombieModel {

    /**
     * Called to update the model.
     */
    void update();

    /**
     * Used to set obstacles in ZombieAIImpl.
     * @param set set of all obstacles in the map.
     */
    void setObstacles(Set<BoundingBox> set);

    /**
     * Used to set spawn points in SpawnImpl.
     * @param spawnPoints
     */
    void setSpawnPoints(Set<Point2D> spawnPoints);

    /**
     * Used to set player reference.
     * @param player
     */
    void setPlayer(Player player);

    /**
     * Used to set counter of zombies to spawn in following round.
     * @param zombiesToSpawn count of zombies to spawn. 
     */
    void setZombiesToSpawn(int zombiesToSpawn);

    /**
     * Used to set zombie tier. Higher tier means higher statistics.
     * @param zombieTier tier
     */
    void setZombieTier(Tiers zombieTier);

    /**
     * 
     * @return set of alive zombies.
     */
    Set<Zombie> getZombies();

    /**
     * 
     * @return total zombies in the round, counting alive and spawning ones.
     */
    int getZombiesCount();


    /**
     * Used to decrease zombie's life and check if is dead.
     * @param zombie zombie to hit.
     * @param damage damage dealt.
     */
    void hitZombie(Zombie zombie, int damage);

    /**
     * 
     * @return kills counter.
     */
    int getKilledZombies();

}
