package it.unibo.javajump.model.entities.platforms;

import it.unibo.javajump.model.entities.GameObject;

/**
 * The interface Platform.
 */
public interface Platform extends GameObject {

    /**
     * Triggers touched flag.
     */
    void triggerTouched();

    /**
     * Consume the touched flag.
     *
     * @return the flag
     */
    boolean consumeTouched();
}
