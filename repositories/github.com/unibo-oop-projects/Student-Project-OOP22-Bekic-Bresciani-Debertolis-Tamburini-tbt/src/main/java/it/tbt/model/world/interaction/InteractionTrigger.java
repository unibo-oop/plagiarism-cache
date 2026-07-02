package it.tbt.model.world.interaction;

/**
 * Interface for objects who are capable of triggering interactions.
 */
public interface InteractionTrigger {

    /**
     * Method that initiates the interaction or delegates it to another object, for example, an InteractionComponent.
     */
    void triggerInteraction();
}
