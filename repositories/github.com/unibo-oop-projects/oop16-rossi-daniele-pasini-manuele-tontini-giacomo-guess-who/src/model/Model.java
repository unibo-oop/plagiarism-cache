package model;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import model.exception.AttemptException;
import model.exception.NoMoreQuestionAvailableException;
import model.exception.PendingAIGuessingCharacter;
import model.exception.UserLiedException;

/**
 * Exposes the methods for controlling and advancing the game.
 */
public interface Model {

    /**
     * Allows to start a new game with the same previous configuration (same character pack).
     */
    void resetGame();

    /**
     * Defines which characters are left to the human player and which have already been discarded as a result of previously placed questions.
     * @return a map in which each character is associated true if the character is still the one potentially chosen by the opponent, 
     * false otherwise
     */
    Map<Character, Boolean> getHumanCharacters();

    /**
     * Get a set of question left by uman character.
     * @return a set of question left by uman character
     */
    Set<Question> getHumanQuestions();

    /**
    * Defines which characters are left to the AI player and which have already been discarded as a result of previously placed questions.
    * @return a map in which each character is associated true if the character is still the one potentially chosen by the opponent, 
    * false otherwise
    */
    Map<Character, Boolean> getAICharacters();

    /**
     * 
     * @return the character chosen by the AI
     */
    Character getAIchosenCharacter();

    /**
     * Allow to understand who the turn is.
     * @return true if its the human turn
     */
    boolean isHumanTurn();

    /**
     * Allow to understand if the human won.
     * @return true if human won, false otherwise
     */
    boolean humanWon();

    /**
     * Allow to understand if the AI player won.
     * @return true if AI player won, false otherwise
     */
    boolean aiWon();

    /**
     * Set the character chosen by the human.
     * @param c the chosen character
     */
    void humanHasChosenCharacter(Character c);

    /**
     * Allow the human player to ask a question to AI player (in case is his his turn).
     * @param q the question to ask
     * @return the answer to that question
     */
    boolean askToAI(Question q);

    /**
     * Allow the human player to try to guess the AI's chosen character.
     * @param c the character that human player belives it's chosen by the AI
     * @throws AttemptException in case human player has no more attempt
     */
    void humanTryToGuess(Character c) throws AttemptException;

    /**
     * Allow to know the question that AI wants to ask to the human player.
     * @return the next question of AI player
     * @throws NoMoreQuestionAvailableException (the game should be already ended)
     * @throws PendingAIGuessingCharacter  (AI want to try a guess)
     */
    Question getAInextQuestion() throws NoMoreQuestionAvailableException, PendingAIGuessingCharacter;

    /**
     * Allow to know the character AI think is chosen by human player.
     * @return the character that AI think it's chosen by the human player
     */
    Optional<Character> getAIcharacterGuess();

    /**
     * Allows to notify the computer player the answer to the question he has sent to the human player before.
     * @param q the question to which the human player answered.
     * @param answer the answer given by the human player
     * @throws UserLiedException if the user lied
     */
    void humanAnswered(Question q, boolean answer) throws UserLiedException;

    /**
     * Allows to notify the computer player the answer to the guessing question he has sent to the human player before.
     * @param c the character that AI think it's chosen by the human player
     * @param answer the answer given by the human player
     * @throws UserLiedException if the user lied
     */
    void humanAnswered(Character c, boolean answer) throws UserLiedException;
}