package it.unibo.javajump.model.entities.objectstrategies;

import it.unibo.javajump.model.entities.GameObject;

/**
 * The interface Movement behaviour.
 */
public interface MovementBehaviour {
    /**
     * Updates the position of a GameObject based on deltaTime.
     *
     * @param obj       the GameObject to update
     * @param deltaTime the time passed from the last update
     */
    void update(GameObject obj, float deltaTime);
}
