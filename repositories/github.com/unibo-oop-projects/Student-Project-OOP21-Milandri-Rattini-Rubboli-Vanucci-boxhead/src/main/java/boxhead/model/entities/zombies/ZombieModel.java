package boxhead.model.entities.zombies;

import java.util.Set;
import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import boxhead.model.entities.Player;
import boxhead.model.score.Score;

/**
 * Zombies manager
 *
 */
public interface ZombieModel {

    /**
     * Updates zombie model
     */
    void update();

    /**
     * Used to set obstacles
     * @param set map obstacles set
     */
    void setWalls(Set<BoundingBox> walls);

    /**
     * Used to set spawn points
     * @param spawnPoints
     */
    void setSpawnPoints(Set<Point2D> spawnPoints);

    /**
     * Used to set player
     * @param player
     */
    void setPlayer(Player player);
 
    /**
     * Assign score to model
     * @param score
     */
    void linkScore(Score score);
    
    /**
     * Set zombies to spawn in the round
     * @param zombiesToSpawn zombies to spawn counter
     */
    void setZombiesToSpawn(int zombiesToSpawn);

    /**
     * 
     * @return set of alive zombies
     */
    Set<Zombie> getZombies();

    /**
     * 
     * @return the number of zombies in the current round (dead or alive ones)
     */
    int getZombiesCount();


    /**
     * Life and death check method
     * @param zombie zombie to hit
     * @param damage damage inflict
     */
    void hitZombie(Zombie zombie, int damage);

    /**
     * 
     * @return kills counter
     */
    int getKilledZombies();

}
