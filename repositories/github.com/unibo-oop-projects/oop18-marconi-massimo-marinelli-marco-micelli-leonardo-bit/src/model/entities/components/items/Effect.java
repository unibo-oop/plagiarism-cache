package model.entities.components.items;

/**
 * Something which is triggered and have an effect in the game world.
 */
public interface Effect {

    /**
     * An effect that occurs when the player triggers an event.
     */
    void whenTriggered();
}
