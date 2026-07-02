package it.unibo.model.world.api;

import java.util.Optional;

import it.unibo.model.gameobj.api.Coin;
import it.unibo.model.gameobj.api.Enemy;
import it.unibo.model.gameobj.api.Gadget;
import it.unibo.model.gameobj.api.Platform;

/**
 * Interface for a world that acts as a queue (FIFO).
 * Allows removing the first element for each entity type,
 * treating the world storage as a queue.
 */
public interface QueueWorld extends BaseWorld {

    /**
     * Removes and returns the first static platform available in the queue.
     *
     * @return an {@link Optional} containing the removed platform if present,
     *         or an empty Optional if the queue is empty.
     */
    Optional<Platform> removeFirstStaticPlatform();

    /**
     * Removes and returns the first moving platform available in the queue.
     *
     * @return an {@link Optional} containing the removed platform if present,
     *         or an empty Optional if the queue is empty.
     */
    Optional<Platform> removeFirstMovingPlatform();

    /**
     * Removes and returns the first on-touch platform available in the queue.
     *
     * @return an {@link Optional} containing the removed platform if present,
     *         or an empty Optional if the queue is empty.
     */
    Optional<Platform> removeFirstOnTouchPlatform();

    /**
     * Removes and returns the first enemy (monster) available in the queue.
     *
     * @return an {@link Optional} containing the removed enemy if present,
     *         or an empty Optional if the queue is empty.
     */
    Optional<Enemy> removeFirstMonster();

    /**
     * Removes and returns the first gadget available in the queue.
     *
     * @return an {@link Optional} containing the removed gadget if present,
     *         or an empty Optional if the queue is empty.
     */
    Optional<Gadget> removeFirstGadget();

    /**
     * Removes and returns the first coin (money) available in the queue.
     *
     * @return an {@link Optional} containing the removed coin if present,
     *         or an empty Optional if the queue is empty.
     */
    Optional<Coin> removeFirstMoney();
}
