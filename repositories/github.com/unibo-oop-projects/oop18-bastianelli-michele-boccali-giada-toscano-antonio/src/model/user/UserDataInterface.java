package model.user;

import java.util.Set;

import enumerators.Level;
import enumerators.PlayerCharacter;

public interface UserDataInterface {

    /**
     * @return number of level unlocked by the user
     */
    Level getLevel();

    /**
     * Set the number of levels unlocked by the user
     * 
     * @param level : the number of the level unlocked
     */
    void setLevel(final Level level);

    /**
     * @return max height reached by the user
     */
    int getMaxHeight();

    /**
     * Set user max height reached
     * 
     * @param score
     */
    void setMaxHeight(final int maxHeight);

    /**
     * Get the unlocked characters
     * 
     * @return an HashSet of characters
     */
    Set<PlayerCharacter> getCharacters();

    /**
     * Get the current character selected
     * 
     * @return the selected character
     */
    PlayerCharacter getCurrentCharacter();

    /**
     * Set the selected character as current character
     * 
     * @param currentCharacter : the character selected
     */
    void setCurrentCharacter(final PlayerCharacter currentCharacter);

    /**
     * Get the coin owned by the user
     * 
     * @return coin owned
     */
    int getCoin();

    /**
     * Set coin value
     * 
     * @param coin : the new coin value, must be not negative
     */
    void setCoin(final int coin);

    /**
     * Add coin to the actual coin owned
     * 
     * @param coin : the new coin value, must be not negative
     */
    void addCoin(final int coin);

    /**
     * Subtrat coin to the actual coin owned
     * 
     * @param coin : the new coin value, must be not negative
     */
    void subtractCoin(final int coin);

    /**
     * Set PlayerCharacters
     * 
     * @param character : Set of characters
     */
    void setCharacters(final Set<PlayerCharacter> character);

    /**
     * Add PlayerCharacter to the actual PlayerCharacters unlocked
     * 
     * @param character : the new PlayerCharacter
     */
    void addCharacter(final PlayerCharacter character);

    /**
     * Delete a character from the actual PlayerCharacters unlocked
     * 
     * @param character : the character to be deleted
     */
    void deleteCharacter(final PlayerCharacter character);

}