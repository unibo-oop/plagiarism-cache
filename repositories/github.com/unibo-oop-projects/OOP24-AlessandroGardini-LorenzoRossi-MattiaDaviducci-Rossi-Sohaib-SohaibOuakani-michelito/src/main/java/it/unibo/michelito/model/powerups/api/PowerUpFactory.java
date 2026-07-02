package it.unibo.michelito.model.powerups.api;

import it.unibo.michelito.util.Position;

/**
 * Interface for a factory that creates instances of {@link PowerUp}.
 * This factory provides a method to create different types of power-ups,
 * each associated with a specific {@link Position}.
 * The type of power-up to create is determined by the {@link PowerUpType} parameter.
 */
public interface PowerUpFactory {

    /**
     * Creates a {@link PowerUp} of the specified type at the given position.
     * This method allows for the creation of various power-ups,
     * depending on the provided {@link PowerUpType}.
     *
     * @param position the position associated to the power-up
     * @param type the type of power-up to create, as defined by the {@link PowerUpType} enum
     * @return a new instance of the specified power-up type
     */
    PowerUp createPowerUp(Position position, PowerUpType type);
}
