package it.unibo.bmbman.controller.game;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import it.unibo.bmbman.model.entities.BombImpl;
import it.unibo.bmbman.model.entities.Entity;
import it.unibo.bmbman.model.entities.HeroImpl;
import it.unibo.bmbman.model.utilities.Pair;
import it.unibo.bmbman.view.entities.BombView;
/**
 * Interface of BombController.
 */
public interface BombController {
    /**
     * Get bombs that must be removed.
     * @return list of {@link Bomb, BombView}
     */
    List<Pair<BombImpl, BombView>> getBombsToRemove();
    /**
     * Get bombs that must explode.
     * @return list of {@link BombImpl}
     */
    List<BombImpl> getBombsInExplosion();
    /**
     * Used to get planted Bombs view.
     * @return set of Bombs
     */
    Set<BombView> getBombView();
    /**
     * It creates a new {@link BombImpl} to planted if {@link Hero} has enough bombs.
     * @param hero 
     * @return bomb that hero can plant, otherwise an empty optional.
     */
    Optional<BombImpl> plantBomb(HeroImpl hero);
    /**
     * Update bomb view and model.
     */
    void update();
    /**
     * Remove bombs that are exploded. 
     */
    void removeBomb();
    /**
     * Determines if bomb is in collision with one of the given entities.
     * @param entities set of {@link Entity}
     */
    void collision(Set<Entity> entities);
}
