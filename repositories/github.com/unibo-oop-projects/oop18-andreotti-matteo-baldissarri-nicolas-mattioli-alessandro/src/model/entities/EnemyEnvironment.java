package model.entities;

import java.util.Optional;

/**
 * Models the environment in which an enemy is situated.
 */
public final class EnemyEnvironment extends AbstractEnvironment {

    /**
     * @param initPos The entity's initial position
     * @param height  The height of the entity
     * @param width   The width of the entity
     * @param borders The entity's boundaries
     */
    public EnemyEnvironment(final Position initPos, final double height, final double width,
            final Optional<Border> borders) {
        super(initPos, height, width, borders);
    }

    @Override
    public boolean canMove(final Position position) {
        return true;
    }

}
