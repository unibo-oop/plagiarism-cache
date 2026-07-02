package it.unibo.javajump.model.entities.collectibles;

import it.unibo.javajump.model.entities.GameObject;
import it.unibo.javajump.model.entities.platforms.Platform;

/**
 * Coin interface, representing the collectible coin entity.
 */
public interface Coin extends GameObject {
    /**
     * Method that marks the coin as collected, changing its state to COLLECTED.
     */
    void collect();

    /**
     * Method that marks the coin as done, changing its state to FINISHED.
     */
    void markAsDone();

    /**
     * Method that attaches the coin to a platform.
     *
     * @param platform the platform to which the coin is attached
     */
    void attachToPlatform(Platform platform);

    /**
     * Method that returns the platform to which the coin is attached.
     *
     * @return the attached platform
     */
    Platform getAttachedPlatform();

    /**
     * Method that returns the state of the coin.
     *
     * @return the state of the coin
     */
    CoinState getState();
}
