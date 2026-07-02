package it.unibo.arkanoid.model;

import it.unibo.arkanoid.subject.Brick;

/**
 * 
 * Interface for the generator of the {@link PowerUp}.
 *
 */
public interface PowerUpGenerator {
    /**
     * 
     * @param brick
     *            The {@link Brick} where spawn the Power Up.
     * @param level
     *            The {@link Level} where spawn the Power Up.
     * 
     */
    void onBrickDestroyed(Brick brick, Level level);
}
