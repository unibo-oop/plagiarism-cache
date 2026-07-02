package talisman.model.character;

import talisman.model.quests.TalismanQuest;

/**
 * Interface that models the player.
 * 
 * @author Enrico Maria Montanari
 *
 */
public interface PlayerModel {
    /**
     * Gets the player's id.
     * 
     * @return the value
     */
    int getIndex();

    /**
     * Gets the count of all active players.
     * 
     * @return the value
     */
    int getNumPlayers();

    /**
     * Gets the information of the current character assigned to the player.
     * 
     * @return the value
     */
    CharacterModel getCurrentCharacter();

    /**
     * Sets id.
     * 
     @param id - the value to be set
     */
    void setIndex(int id);

    /**
     * Sets the current number of active players.
     * 
     @param number - the value to be set
     */
    void setNumPlayers(int number);

    /**
     * Sets the current character assigned to the player.
     * 
     @param character - the character to be set
     */
    void setCurrentCharacter(CharacterModelImpl character);

    /**
     * Give to the player the crown of command
     */
    void setCrownPlayer();

    /**
     * Gives to the player a quest to complete
     *
     * @param questType the instance of the quest
     */
    void giveTalismanQuest(TalismanQuest questType);

    /**
     * Resolve the active quest for the player
     *
     *
     */
    void resolveActiveQuest();

    /**
     * Gets the active quest for the player
     *
     * @return the instance of the quest, null if there isn't any
     */
    TalismanQuest getActiveQuest();

    /**
     * Checks if the player has a quest active
     *
     * @return true if the player has a quest
     */
    boolean hasQuest();

    /**
     * Checks whether the players has the crown of command.
     * @return true if the player has the crown
     */
    boolean hasCrown();

    /**
     * Checks if player owns a talisman
     *
     * @return true if the player owns the talisman
     */
    boolean hasTalisman();

}
