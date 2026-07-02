package it.unibo.model.worldconstructor.gameobjectspawn.api;

import it.unibo.model.gameobj.api.Platform;
import it.unibo.model.physics.api.Vector2d;

/**
 * Interface for creating game objects from a spawn pool.
 * Selects specific objects to create based on random chance and the configured
 * pool.
 */
public interface SpawnPoolCreator {

    /**
     * Sets the pool of entities to be used for creation.
     * 
     * @param spawnPool the pool containing entity factories and probabilities
     */
    void setSpawnPool(SpawnPool spawnPool);

    /**
     * Creates a static platform based on the given chance.
     * 
     * @param chance the random value used to select the platform type
     * @param pos    the position where the platform should be created
     */
    void createStaticPlatform(double chance, Vector2d pos);

    /**
     * Creates a moving platform based on the given chance.
     * 
     * @param chance the random value used to select the platform type
     * @param pos    the position where the platform should be created
     */
    void createMovingPlatform(double chance, Vector2d pos);

    /**
     * Creates a on-touch platform based on the given chance.
     * 
     * @param chance the random value used to select the platform type
     * @param pos    the position where the platform should be created
     */
    void createOnTouchPlatform(double chance, Vector2d pos);

    /**
     * Creates a monster (enemy) based on the given chance.
     * 
     * @param chance the random value used to select the monster type
     * @param platform the platform where the monster should be created
     */
    void createMonster(double chance, Platform platform);

    /**
     * Creates a gadget based on the given chance.
     * 
     * @param chance the random value used to select the gadget type
     * @param platform the platform where the gadget should be created
     */
    void createGadget(double chance, Platform platform);

    /**
     * Creates a coin (money) based on the given chance.
     * 
     * @param chance the random value used to select the coin type
     * @param platform the platform where the coin should be created
     */
    void createMoney(double chance, Platform platform);

}
