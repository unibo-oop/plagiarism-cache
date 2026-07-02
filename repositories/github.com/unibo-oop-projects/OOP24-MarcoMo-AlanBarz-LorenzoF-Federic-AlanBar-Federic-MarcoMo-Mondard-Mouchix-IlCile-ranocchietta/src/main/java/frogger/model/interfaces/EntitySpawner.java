package frogger.model.interfaces;

import java.util.List;

/**
 * Model the concept of an object with the only purpose of generate a list of entity,
 * it's useful for the Single Responsibility Principle.
 * @param <X> the type of entity to spawn
 */
public interface EntitySpawner<X> {

    /**
     * Creates a list of generic entities.
     * @param min the min number of entities
     * @param max the max number of entities
     * @return the list
     */
    List<X> spawn(int min, int max);
}
