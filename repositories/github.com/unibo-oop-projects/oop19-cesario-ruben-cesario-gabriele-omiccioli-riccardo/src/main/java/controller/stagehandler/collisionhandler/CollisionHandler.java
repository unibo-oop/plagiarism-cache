package controller.stagehandler.collisionhandler;

import model.Stage;
import model.entity.CollidableEntity;

/**
 * Controls how collisions occur and what are their consequences.
 */
public interface CollisionHandler {

    /**
     * Returns true if the two specified entities are colliding.
     * @param e1 : the first specified collidable entity.
     * @param e2 : the second specified collidable entity.
     * @return true if the two specified entities are colliding, false otherwise.
     */
    boolean areColliding(CollidableEntity e1, CollidableEntity e2);

    /**
     * Check all the collisions that may have occurred within the specified stage.
     * @param stage : the specified stage.
     */
    void checkCollisions(Stage stage);

}
