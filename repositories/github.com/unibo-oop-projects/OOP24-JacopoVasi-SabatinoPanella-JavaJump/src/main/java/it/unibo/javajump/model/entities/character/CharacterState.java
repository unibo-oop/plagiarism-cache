package it.unibo.javajump.model.entities.character;

/**
 * Interface for the Character's states during the game (STATE PATTERN).
 */
public interface CharacterState {
    /**
     * Updates the Character's state.
     *
     * @param character the characterImpl to update
     * @param deltaTime the time passed from the last frame
     */
    void updateCharacter(Character character, float deltaTime);

    /**
     * Method called when the character enters this state.
     *
     * @param character the character
     */
    void onEnter(Character character);

    /**
     * Method called when the character exits this state.
     *
     * @param character the character
     */
    void onExit(Character character);

    /**
     * Signals if, in this current state, the character is on a platform.
     *
     * @return true if the character is on a platform
     */
    boolean isOnPlatform();
}
