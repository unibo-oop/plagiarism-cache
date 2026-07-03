package model.spawner;

import java.util.Collection;
import java.util.Optional;

import model.entities.Enemy;

/**
 * 
 * Interface for the spawner state.
 *
 */
public interface State {

    /**
     * 
     * Method that spawn enemies.
     * @return the enemies spawned.
     */
    Collection<Enemy> spawn();

    /**
     * 
     * @return true if all this state's enemies have spawned.
     */
    boolean isStateEnded();

    /**
     * 
     * @return the next state of the spawner.
     */
    Optional<State> getNextState();

    /**
     * @return spawn delay.
     */
    int getSpawnDelay();
}
