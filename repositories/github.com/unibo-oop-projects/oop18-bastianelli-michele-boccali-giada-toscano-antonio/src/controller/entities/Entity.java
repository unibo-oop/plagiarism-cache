package controller.entities;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;

import enumerators.EntityType;
import model.entities.EntityModel;
import model.physics.Size2D;
import view.entities.EntityView;

/**
 * Base interface implemented by the entity's controllers.
 */
public interface Entity {

    /**
     * @return the physic position of the entity got by the {@link World}.
     */
    Vec2 getPhysicPosition();

    /**
     * @return the node position of the entity got by his view {@link EntityView}.
     *         The node position depends on the camera position and the physic
     *         position.
     */
    Vec2 getViewPosition();

    /**
     * Get the entity type.
     * 
     * @return the entity type,
     */
    EntityType getEntityType();

    /**
     * @return the physic body of the entity
     */
    Body getBody();

    /**
     * Update the entity when a tick occurs.
     */
    void updateEntity();

    /**
     * @return the entity model
     */
    EntityModel getModel();

    /**
     * @return the entity view
     */
    EntityView getView();

    /**
     * Destroy the entity by removing its view and it's model.
     * The entity must be destroyed from the World in the next world step
     */
    void destroy();

    /**
     * Get the entity dimension.
     * 
     * @return the entity dimension
     */
    Size2D getDimension();

}
