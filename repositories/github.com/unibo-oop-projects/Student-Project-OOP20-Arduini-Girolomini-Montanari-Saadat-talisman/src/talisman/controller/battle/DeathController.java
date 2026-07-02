package talisman.controller.battle;

import talisman.model.character.CharacterModelImpl;

/**
 * A MVC controller for the character's death.
 * 
 * @author Alice Girolomini
 */
public interface DeathController {
    /**
     * Manages the character's death.
     * 
     * @return true if the character died
     */
    boolean death();

    /**
     * Resets the character's informations.
     * 
     * @param character - the character to reset
     * @param index - the player's index
     */
    void resetCharacterInfo(CharacterModelImpl character, int index);

    /**
     * Resets the character's position on the board.
     * 
     * @param index - the index of the player
     */
    void resetCharacterPosition(int index);

}
