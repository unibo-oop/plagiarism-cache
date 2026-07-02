package it.unibo.oop.manpac.model.collisions;

import it.unibo.oop.manpac.model.Action;
import it.unibo.oop.manpac.model.Entity;
import it.unibo.oop.manpac.model.Manpac;

/**
 * Implementation Collisions interface, which manages the collisions.
 */
public class CollisionsImpl implements Collisions {

    private final Manpac model;

    /**
     * Constructor of CollisionImpl.
     * 
     * @param model the model which will call this call
     * 
     */
    public CollisionsImpl(final Manpac model) {
        this.model = model;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Action checkPacmanCollisions(final Entity entity) {
        switch (entity) {

        case WALL:
            return model.stopPacman();

        case PILL:
            return model.pillEaten();

        case POWERPILL:
            return model.powerPillEaten();

        case PORTAL:
            return Action.PACMAN_EFFECT;

        case BLINKY:
        case INKY:
        case PINKY:
        case CLYDE:
            if (model.arePhantomsFeared()) {
                return model.phantomEaten();
            } else {
                return model.decreaseLives();
            }

        default:
            return Action.NOTHING_HAPPENS;

        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Action checkPhantomCollisions(final Entity name, final Entity entity) {

        if (entity.equals(Entity.WALL)) {
            return model.changePhantomDirection(name);
        }

        return Action.NOTHING_HAPPENS;
    }

}
