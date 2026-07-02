package it.tbt.model.world.interaction;

/**
 * Interface for the interaction component. Object to be used to trigger interaction on Interactable objects.
 */
public interface InteractionComponent {

    /**
     * Defines how the interaction control and/or execution is done.
     */
    void interactLogic();
}
