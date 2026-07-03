package controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import model.Character;
import model.Question;

/**
 * Controller's interface.
 *
 */

public interface Controller {

    /**
     * @return A map of remaining characters in which the opponent's character
     *         is hidden.
     * 
     */
    Map<Character, Boolean> getHumanCharactersLeft();

    /**
     * Loads characters contained in the choosen pack.
     * 
     * @param s
     *            the choosen pack to be loaded
     */
    void loadPack(String s);

    /**
     * 
     * @return A map containing all the characters in which the Player character
     *         is hidden
     */
    Map<Character, Boolean> getAICharactersLeft();

    /**
     * Asks the human question to AI.
     * 
     * @param q
     *            the question the player wants to ask to AI
     */
    void askQuestion(Question q);

    /**
     * 
     * AI tries to guess player's character.
     * 
     * @param c
     *            the character AI thinks has been choosen by the player
     * @param answer
     *            player's answer to that question.
     */
    void aiGuessed(Character c, boolean answer);

    /**
     * Sets the player's choosen character.
     * 
     * @param c
     *            the character choosen by the player.
     */
    void setHumanCharacter(Character c);

    /**
     * Checks if human lied to AI's last question.
     * 
     * @param q
     *            The question the player has answered
     * @param answer
     *            the answer given to that question.
     */
    void humanAnswered(Question q, boolean answer);

    /**
     * @return list of questions that the player still hasn't asked to AI.
     */
    Set<Question> getAvailableQuestions();

    /**
     * 
     * @return All possible packages to be choosen.
     */
    List<String> getPackList();

    /**
     * Allows the player to try and guess AI's choosen character.
     * 
     * @param choosen
     *            player's character guess.
     */
    void tryToGuess(Character choosen);

    /**
     * Starts the game launching the main menu.
     */
    void startGame();

    /**
     * Allow to start a new match with the latest configuration.
     */
    void restartGame();

}
