package model.entities;

import java.util.Objects;

import model.Model;
import model.entitiesutil.Entity;
import model.entitiesutil.EntityAbstract;
import model.physics.CollisionDirection;

/**
 * Boundary {@link Entity} of the game.
 */
public class Wall extends EntityAbstract {

    /**
     * Boundary {@link Entity} of the game.
     * 
     * @param model     is the {@link Model}
     * @param x         is the left x coordinate
     * @param y         is the top y coordinate
     * @param width     of the entity
     * @param height    if the entity
     */
    public Wall(final Model model, final int x, final int y, final int width, final int height) {
        super(Objects.requireNonNull(model), x, y, width, height);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isTouchedBy(final Entity entity, final CollisionDirection collisionDirection) {
        // Do nothing
    }

}
