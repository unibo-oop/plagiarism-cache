package talisman.model.character;

import talisman.Controllers;
import talisman.model.quests.TalismanQuest;
import talisman.model.quests.exceptions.NoActiveQuestException;

/**
 * Implementation of the player.
 * 
 * @author Enrico Maria Montanari
 *
 */
public class PlayerModelImpl implements PlayerModel {
    private int numPlayers;
    private int id;
    private CharacterModelImpl currentCharacter;
    private TalismanQuest quest;
    private boolean crown;
    private boolean talisman;

    /**
     * Creates the enemy's informations.
     * 
     * @param numPlayers - the number of active players
     * @param id - the player's id
     * @param character - the current character's information assigned to the player
     */
    public PlayerModelImpl(final int numPlayers, final int id, final CharacterModelImpl character) {
        this.numPlayers = numPlayers;
        this.id = id;
        this.currentCharacter = character;
        this.crown = false;
        this.talisman = false;
        this.quest = null;
    }

    /**
     * Gets the player's id.
     * 
     * @return the value
     */
    public int getIndex() {
        return this.id;
    }

    /**
     * Gets the count of all active players.
     * 
     * @return the value
     */
    public int getNumPlayers() {
        return this.numPlayers;
    }

    /**
     * Gets the information of the current character assigned to the player.
     * 
     * @return the value
     */
    public CharacterModelImpl getCurrentCharacter() {
        return this.currentCharacter;
    }

    /**
     * Sets id.
     * 
     @param id - the value to be set
     */
    public void setIndex(final int id) {
        this.id = id;
    }

    /**
     * Sets the current number of active players.
     * 
     @param number - the value to be set
     */
    public void setNumPlayers(final int number) {
        this.numPlayers = number;
    }

    /**
     * Sets the current character assigned to the player.
     * 
     @param character - the character to be set
     */
    public void setCurrentCharacter(final CharacterModelImpl character) {
        this.currentCharacter = character;
    }

    /**
     * Give to the player the crown of command
     */
    @Override
    public void setCrownPlayer() {

        crown = true;
    }

    /**
     * Gives to the player a new quest
     *
     * @param questType the instance of the quest
     */
    @Override
    public void giveTalismanQuest(TalismanQuest questType) {

        quest = questType;
    }

    /**
     * Complete the active quest
     */
    @Override
    public void resolveActiveQuest() {

        if (hasQuest()){

            quest = null;
            talisman = true;

            int index = Controllers.getCharactersController().getCurrentPlayer().getIndex();
            Controllers.getBoardController().moveCharacterSection(index, 1, 8);

        } else {

            throw new NoActiveQuestException("player n. " + id + " doesn't have an active quest");
        }
    }

    /**
     * Gets the active quest
     *
     * @return the quest
     */
    @Override
    public TalismanQuest getActiveQuest() {

        return quest;
    }

    /**
     *  Checks if player has an active quest
     *
     * @return true if the player has a quest
     */
    @Override
    public boolean hasQuest() {

        return quest != null;
    }

    /**
     * Checks whether the players has the crown of command.
     *
     * @return true if the player has the crown
     */
    public boolean hasCrown() {

        return this.crown;
    }

    /**
     * Checks if the player owns a talisman
     *
     * @return true if the player owns a talisman
     */
    @Override
    public boolean hasTalisman() {

        return talisman;
    }


}
