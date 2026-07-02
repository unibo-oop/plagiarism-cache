package it.unibo.bmbman.model.entities;
import java.util.Optional;

import it.unibo.bmbman.model.utilities.BombState;
/**
 * Interface of bomb. 
 */
public interface Bomb {
    /**
     * Get {@link Explosion}.
     * @return explosion
     */
    Optional<Explosion> getExplosion();
    /**
     * Get state of bomb.
     * @return {@link BombState}
     */
    BombState getState();
    /**
     * Set state EXPLODED to the bomb.
     */
    void setBombExploded();
    /**
     * Start timer for bomb planted. At the end of it, explosion occurs. 
     */
    void startTimer();
}
