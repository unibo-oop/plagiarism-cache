package it.unibo.javajump.model.physics;

import it.unibo.javajump.model.entities.character.Character;

/**
 * The interface that describes a Physics manager.
 */
public interface PhysicsManager {
    /**
     * Method to update the character movement.
     *
     * @param character the character to update
     * @param deltaTime the delta time, to calculate the movement in real time
     * @param direction the direction of movement
     */
    void updateCharacterMovement(Character character, float deltaTime, MovementDirection direction);
}
