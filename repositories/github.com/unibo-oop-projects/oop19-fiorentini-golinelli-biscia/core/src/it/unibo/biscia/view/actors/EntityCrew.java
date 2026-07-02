package it.unibo.biscia.view.actors;

import it.unibo.biscia.core.Entity;
import it.unibo.biscia.events.StateObserver;

import java.util.Collection;

/**
 * Takes care of displaying all current {@link Entity} with their respective
 * Color. Every Entity is displayed by an {@link EntityActor}. It has methods to
 * update it self and its Entities and respective {@link EntityActor}. Usually
 * these methods are called by an {@link StateObserver}.
 * 
 * @see EntityCrewImpl
 * @see Game
 * @see it.unibo.biscia.view.View
 * 
 */
public interface EntityCrew {

    /**
     * Called when new Entities should be displayed. It erases old ones and adds new
     * ones. Usually called on
     * {@link StateObserver#newLevel(it.unibo.biscia.core.Level)}
     * 
     * @see Entity
     * 
     * @param cellWidth  cell's width based on stage position, window proportions
     *                   and level's columns
     * @param cellHeight cell's height based on stage position, window proportions
     *                   and level's rows
     * @param entities   new Entities that should replace old ones
     */
    void reset(float cellWidth, float cellHeight, Collection<Entity> entities);

    /**
     * Adds a single {@link Entity} and its respective {@link EntityActor}. This
     * method is usually called by {@link EntityCrew#addEntities(Collection)}
     * 
     * @param entity The {@link Entity} to be added.
     */
    void addEntity(Entity entity);

    /**
     * Adds every {@link Entity} in {@code Entities} and their respective
     * {@link EntityActor}. This method calls {@link EntityCrew#addEntity(Entity)}
     * for every {@link Entity}. This method is usually called on
     * {@link StateObserver#add(java.util.List)}.
     * 
     * @param entities a {@link Collection} of {@link Entity}
     */
    void addEntities(Collection<Entity> entities);

    /**
     * Removes an {@link Entity} and its respective {@link EntityActor}. This method
     * is ususally called by {@link EntityCrew#removeEntities(Collection)}.
     * 
     * 
     * @param entity The {@link Entity} to be removed
     */
    void removeEntity(Entity entity);

    /**
     * Removes every {@link Entity} in {@code Entities} and their respective
     * {@link EntityActor}. This method calls
     * {@link EntityCrew#removeEntity(Entity)} for every {@link Entity}. This method
     * is usually called on {@link StateObserver#remove(java.util.List)}.
     * 
     * @param entities a {@link Collection} of {@link Entity}
     */
    void removeEntities(Collection<Entity> entities);

}
