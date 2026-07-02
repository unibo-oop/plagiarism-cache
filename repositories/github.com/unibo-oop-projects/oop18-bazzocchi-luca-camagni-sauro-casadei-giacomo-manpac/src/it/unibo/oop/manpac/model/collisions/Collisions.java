package it.unibo.oop.manpac.model.collisions;

import it.unibo.oop.manpac.model.Action;
import it.unibo.oop.manpac.model.Entity;

/**
 * Interface for handling any collision.
 *
 */
public interface Collisions {

    /**
     * Check Pacman's collisions with an entity.
     * 
     * @param entity the entity which collided with pacman
     *
     * @return what happened after that collision
     */
    Action checkPacmanCollisions(Entity entity);

    /**
     * Check Phantom's collisions with an entity.
     * 
     * @param name the name of the phantom which collided with an entity
     * @param entity the entity which collided with pacman
     *
     * @return what happened after that collision
     */
    Action checkPhantomCollisions(Entity name, Entity entity);

}
