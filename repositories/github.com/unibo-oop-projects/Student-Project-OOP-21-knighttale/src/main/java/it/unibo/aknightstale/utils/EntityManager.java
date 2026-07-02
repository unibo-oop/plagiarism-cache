package it.unibo.aknightstale.utils;

import java.util.List;

import it.unibo.aknightstale.controllers.entity.EntityController;
import it.unibo.aknightstale.models.entity.Character;
import it.unibo.aknightstale.views.entity.AnimatedEntityView;

public interface EntityManager {
    /**
     * Checks the collisions between all characters.
     * 
     * @return A list of lists of characters who colliding with each other.
     */
    List<List<EntityController<? super Character, ? super AnimatedEntityView>>> update();

    /**
     * Adds a new entity in the list of entities if it is not already present.
     * 
     * @param ec the new entity that will be added if it is not already present.
     */
    void addEntity(EntityController<? super Character, ? super AnimatedEntityView> ec);

    /**
     * Removes a entity from the entity list if it is present.
     * 
     * @param ec the entity will be removed if it is present.
     */
    void removeEntity(EntityController<? super Character, ? super AnimatedEntityView> ec);

    /**
     * Gets the entity list.
     * 
     * @return the entity list.
     */
    List<EntityController<? super Character, ? super AnimatedEntityView>> getEntities();

    /**
     * Gets the collision manager.
     * 
     * @return the collision manager.
     */
    CollisionManager getCollisionManager();

    /**
     * Sets the collision manager.
     * 
     * @param the collision manager.
     */
    void setCollisionManager(CollisionManager collision);
}
