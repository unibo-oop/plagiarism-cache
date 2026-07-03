package com.geoquiz.model.quiz;

import java.util.Optional;
import java.util.Set;

import com.geoquiz.model.question.Question;

/**
 * This interface models the concept of Quiz.
 *
 */
public interface Quiz {

    /**
     * @return returns the current question.
     */
    Question getCurrentQuestion();

    /**
     * @param answer
     *            the answer given by the user if any, Optional.empty() otherwise.
     */
    void hitAnswer(Optional<String> answer);

    /**
     * Tells the model to go to the next question.
     */
    void next();

    /**
     * 
     * @return true if the given answer is correct, false otherwise.
     */

    boolean isAnswerCorrect();

    /**
     * 
     * @return a String representing the correct answer
     */

    String getCorrectAnswer();

    /**
     * @return the remaining lives for this quiz
     */
    int getRemainingLives();

    /**
     * @return true if there are no remaining lives or the questions are finished, false otherwise.
     */
    boolean gameOver();

    /**
     * @return true if freeze is available, false otherwise.
     */
    boolean isFreezeAvailable();

    /**
     * @return true if skip is available, false otherwise.
     */
    boolean isSkipAvailable();

    /**
     * @return true if 50:50 is available, false otherwise.
     */
    boolean is5050Available();

    /**
     * Set freeze.
     */
    void freeze();

    /**
     * Skip to next question.
     */
    void skip();

    /**
     * @return The set of answers to delete from the answers.
     */
    Set<String> use5050();

    /**
     * @return the time for a single question;
     */
    int getQuestionDuration();

    /**
     * This method is called when the quiz restarts (with same category and mode). Not used.
     * 
     * @return the quiz restarted. 
     */
    Quiz restart();

    /**
     * This method gets the current score.
     * 
     * @return the current score.
     */
    int getCurrentScore();
}
