package it.unibo.javajump.model.entities.collectibles;

/**
 * Enum that represents the possible simple states of a Coin. (Semi-STATE PATTERN);
 * For a proper STATE PATTERN, the Coin should be a STATE MACHINE (like the Character),
 * but the entity is pretty simple as of now, so we'll keep it simple with an enum.
 */
public enum CoinState {
    /**
     * Idle coin state, the coin is not being touched by the player.
     */
    IDLE,
    /**
     * Collecting coin state, the coin has been touched by the player.
     */
    COLLECTING,
    /**
     * Finished coin state: the coin is ready for removal.
     */
    FINISHED
}
