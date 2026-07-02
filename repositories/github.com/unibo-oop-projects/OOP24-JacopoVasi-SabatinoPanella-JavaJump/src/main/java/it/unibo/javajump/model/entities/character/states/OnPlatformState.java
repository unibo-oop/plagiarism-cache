package it.unibo.javajump.model.entities.character.states;

import it.unibo.javajump.model.entities.character.Character;
import it.unibo.javajump.model.entities.character.CharacterState;

/**
 * Class implementation of the CharacterState interface, representing the state when the character is on a platform.
 * Useful for future implementations and extensibility.
 */
public final class OnPlatformState implements CharacterState {
    @Override
    public void updateCharacter(final Character character, final float deltaTime) {
    }

    @Override
    public void onEnter(final Character character) {
    }

    @Override
    public void onExit(final Character character) {
    }

    @Override
    public boolean isOnPlatform() {
        return true;
    }
}
