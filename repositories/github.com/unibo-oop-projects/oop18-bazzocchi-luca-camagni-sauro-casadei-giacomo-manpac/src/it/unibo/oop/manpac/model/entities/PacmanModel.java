package it.unibo.oop.manpac.model.entities;

import it.unibo.oop.manpac.model.Action;
import it.unibo.oop.manpac.model.Directions;

/**
 * Implementation of the MobileEntity for modeling Pacman.
 */
public final class PacmanModel extends MobileEntityImpl {

    @Override
    protected Action changeAction(final Directions direction) {
        switch (direction) {

        case UP:
            return Action.DIRECTION_CHANGED_UP;

        case DOWN:
            return Action.DIRECTION_CHANGED_DOWN;

        case LEFT:
            return Action.DIRECTION_CHANGED_LEFT;

        case RIGHT:
            return Action.DIRECTION_CHANGED_RIGHT;

        default:
            return Action.DIRECTION_CHANGED_STOP;

        }

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        return super.hashCode() + prime;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        return getClass() == obj.getClass();
    }
}
