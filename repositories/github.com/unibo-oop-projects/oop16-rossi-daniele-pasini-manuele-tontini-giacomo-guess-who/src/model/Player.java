package model;

import java.util.Map;
import java.util.Set;

import model.exception.NoMoreQuestionAvailableException;

/**
 * Interface for a generic player of the game.
 */
public interface Player {

    /**
     * @return a map in which every character has a corrisponding marker 
     * (true if it's stil a potenzial AI's chosen character, false otherwise)
     */
    Map<Character, Boolean> getCharacters();

    /**
     * @return the set of question that could be posed to the opponent by this player
     * @throws NoMoreQuestionAvailableException 
     */
    Set<Question> getQuestionsLeft() throws NoMoreQuestionAvailableException;

    /**
     * Get the character chosen by this player.
     * @return the character chosen by this player
     */
    Character getChosenCharacter();

    /**
     * Answer to a question posed by the opponent.
     * @param q the question this player should answer
     * @return the answer to the posed question
     */
    boolean answerQuestion(Question q);

    /**
     * Eliminates potential opponent characters based on the response to the question passed as argument.
     * @param q the question that this player asked
     * @param answer the answer of the question q gaved by the opponent
     */
    void questionAsked(Question q, boolean answer);

    /**
     * Put in place the characters left to this player based on the success of guessing attempt.
     * @param c the character that this player though to be chosen by the opponent
     * @param wasHim was the character c the character chosen by the opponent?
     */
    void triedToGuess(Character c, boolean wasHim);

    /**
     * 
     * @return the number of guessing attempt left by this player
     */
    int getAttemptLeft();

}