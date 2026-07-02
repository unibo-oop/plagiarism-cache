package it.tbt.model.world.interaction;

import it.tbt.model.entities.SpatialEntity;

/**
 * Interface to be implemented by objects who are able to are to be interacted with.
 */
public interface Interactable {

    /**
     * Performs the operation on its interaction .
     * Different outcomes can be achieved based on the type of the parameter.
     * @param interactionTrigger
     */
    void onInteraction(SpatialEntity interactionTrigger);
}
