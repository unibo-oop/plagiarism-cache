package it.unibo.javajump.model.collision;

import it.unibo.javajump.model.GameModel;

/**
 * Interface that defines the collision manager.
 */
public interface CollisionManager {
    /**
     * Controls and manages collision events between all the GameObjects in the model.
     *
     * @param model the GameModel
     */
    void checkCollisions(GameModel model);
}
