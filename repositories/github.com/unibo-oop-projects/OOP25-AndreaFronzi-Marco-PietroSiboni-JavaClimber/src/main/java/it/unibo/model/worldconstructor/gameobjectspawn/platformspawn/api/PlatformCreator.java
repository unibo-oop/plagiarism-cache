package it.unibo.model.worldconstructor.gameobjectspawn.platformspawn.api;

import it.unibo.model.physics.api.Vector2d;

/**
 * Interface for creating platforms in the game.
 * The PlatformCreator is responsible for generating platforms based on certain
 * conditions (like random chance) and placing them at specific positions.
 */
public interface PlatformCreator {

    /**
     * Creates a platform based on the given chance and position.
     * 
     * @param chance the random value used to select the platform type
     * @param pos    the position where the platform should be created
     */
    void createPlatform(double chance, Vector2d pos);

    /**
     * Sets the platform pool that the creator will use to spawn platforms.
     * 
     * @param platformPool the platform pool to be used by the creator
     */
    void setPlatformPool(PlatformPool platformPool);
}
