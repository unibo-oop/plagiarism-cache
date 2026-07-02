package it.unibo.bmbman.controller.game;
import java.util.Optional;

import it.unibo.bmbman.model.collision.EntityCollisionManager;
import it.unibo.bmbman.model.entities.Entity;
import it.unibo.bmbman.view.entities.EntityView;
/**
 * Used to create a controller for each entity in our game.
 */
public interface EntityController {
    /**
     * Used to get the entity.
     * @return {@link Entity}
     */
    Entity getEntity();
    /**
     * Used to get the image associated with the entity.
     * @return {@link EntityView}
     */
    EntityView getEntityView();
    /**
     * Used to get {@link CollisionController} associated with the entity.
     * @return {@link CollisionController}
     */
    Optional<EntityCollisionManager> getCollisionManager();
    /**
     * Used to update view and model.
     */
    void update();
    /**
     * Used to remove the entity.
     */
    void remove();
}
