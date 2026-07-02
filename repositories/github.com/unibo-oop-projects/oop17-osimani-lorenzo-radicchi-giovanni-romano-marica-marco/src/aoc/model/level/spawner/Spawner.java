package aoc.model.level.spawner;

import aoc.model.entity.EntityInterface;

/**
 * This interface implements the methods of a generic spawner of entities.
 */
public interface Spawner {
    
    /**
     * This method advances the internal clock of the spawner.
     */
    void tick();
    
    /**
     * This method checks if the spawner is ready to spawn new entities.
     * @return an boolean
     */
    boolean readyToSpawn();
    
    /**
     * This method checks if the object still has entities to spawn.
     * @return an boolean
     */
    boolean isEmpty();
    
    /**
     * Returns the entity that the object is spawning
     * when this method is called.
     * 
     * @return the entity spawned.
     */
    EntityInterface entityToSpawn();
}
