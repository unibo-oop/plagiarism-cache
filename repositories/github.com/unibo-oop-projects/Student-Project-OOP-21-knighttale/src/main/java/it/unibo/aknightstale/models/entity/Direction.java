package it.unibo.aknightstale.models.entity;

import java.util.Locale;

public enum Direction {
    /**
     * Up direction.
     */
    UP,
    /**
     * Left direction.
     */
    LEFT,
    /**
     * Right direction.
     */
    RIGHT,
    /**
     * West direction.
     */
    DOWN;

    @Override
    public String toString() {
        return this.name().toLowerCase(Locale.getDefault());
    }
}
