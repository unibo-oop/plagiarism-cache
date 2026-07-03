package model.spawner;

import java.util.Collection;
import java.util.Optional;

import model.entities.Enemy;

/**
 * 
 * Interface for the enemies spawner.
 *
 */
public interface Spawner {

    /**
     * 
     * @param elapsed
     *            time elapsed from the previous update.
     */
    void update(int elapsed);

    /**
     * 
     * @return true if an enemy can be spawned.
     */
    boolean canSpawn();

    /**
     * 
     * @param delay
     *            new spawn delay.
     */
    void setSpawnDelay(int delay);

    /**
     * 
     * @return a collection of enemies that have just spawned.
     */
    Collection<Enemy> spawn();

    /**
     * 
     * @return the number of enemy spawned.
     */
    int getEnemySpawned();

    /**
     * 
     * @return true if all enemies are already spawned.
     */
    boolean isSpawnFinished();

    /**
     * 
     * @return the actual state of the spawner.
     */
    Optional<State> getState();

    /**
     * 
     * @param state
     *            to set.
     */
    void setState(Optional<State> state);
}
