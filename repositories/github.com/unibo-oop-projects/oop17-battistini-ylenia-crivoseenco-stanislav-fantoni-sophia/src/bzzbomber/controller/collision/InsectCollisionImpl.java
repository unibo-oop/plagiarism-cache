package bzzbomber.controller.collision;

import bzzbomber.model.entities.Entity;
import bzzbomber.model.entities.BzzBomber;

/**
 * Implementation of @InsectCollision .
 * 
 *
 */
public class InsectCollisionImpl extends CollisionImpl implements InsectCollision {

    /**
     * Constructor of @InsectCollisionImpl .
     * 
     * @param entity
     *            The entity.
     */
    public InsectCollisionImpl(final Entity entity) {
        super(entity);
    }

    @Override
    public final boolean heroCollision(final BzzBomber hero) {
        return this.getCollisionBox().intersects(hero.getCollisionBox());
    }

}
