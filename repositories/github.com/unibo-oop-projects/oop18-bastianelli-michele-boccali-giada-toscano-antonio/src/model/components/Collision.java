package model.components;

import model.CollisionHandler.CollisionSide;
import model.entities.EntityModel;

/**
 * Component to be used when a collision on the owner occurs.
 */
public interface Collision extends Component {

    /**
     * Apply the collision effect on the collided body.
     * 
     * @param collidedEntity the entity who collided
     * @param side           the collision side
     */
    void applyCollisionEffect(EntityModel collidedEntity, CollisionSide side);
}
