package model.character;

import model.world.GameWorld;

/**
 * This interface models the in game characters and their powerups.
 *
 */
public interface Character {

    /**
     * Activates the character's power when a green obstacle is hit.
     * 
     * @param world
     *                  the gameworld needed for applying modifications to game
     *                  elements like ball and bucket.
     */
    void usePower(GameWorld world);

    /**
     * @return the character's name.
     */
    String getName();

    /**
     * Deactivates the character's power and resets the changes it causes in the world.
     * @param gameWorld
     *                  the gameworld needed for applying modifications to game
     *                  elements.
     */
    void deletePower(GameWorld gameWorld);
}
