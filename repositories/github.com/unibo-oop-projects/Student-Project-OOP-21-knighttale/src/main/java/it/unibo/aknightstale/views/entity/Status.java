package it.unibo.aknightstale.views.entity;

import java.util.Locale;

/**
 *
 * The entity status.
 *
 */
public enum Status {
    /**
     * Idle status.
     */
    IDLE,

    /**
     * Walk status.
     */
    WALK,

    /**
     * Attack status.
     */
    ATTACK;

    @Override
    public String toString() {
        return this.name().toLowerCase(Locale.getDefault());
    }
}
