package model.entities.boss;

import java.util.List;
import java.util.Optional;

import model.entities.Bullet;

/**
 * 
 * Interface for the boss's phases.
 *
 */
public interface Phase {

    /**
     * @return list of the new bullets.
     */
    List<Bullet> shoot();

    /**
     * 
     * @return the next state of the spawner.
     */
    Optional<Phase> getNextPhase();

    /**
     * 
     * @return the phase's fire rate.
     */
    int getPhaseFireRate();
}
