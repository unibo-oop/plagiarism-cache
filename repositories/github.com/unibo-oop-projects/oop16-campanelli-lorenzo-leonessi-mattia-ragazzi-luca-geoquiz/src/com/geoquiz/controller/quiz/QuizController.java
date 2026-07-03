package com.geoquiz.controller.quiz;

import java.io.InputStream;
import java.util.Optional;
import java.util.Set;

/**
 * QuizController interface that provides methods to manage account.
 *
 */
public interface QuizController {

    /**
     * @return the set of possible answers.
     */
    Set<String> showAnswers();

    /**
     * @return the current question of type String.
     */
    String showStringQuestion();

    /**
     * @return the current question of type InputStream.
     */
    InputStream showImageQuestion();

    /**
     * @param answer
     *  the answer selected from the player.
     */
    void hitAnswer(Optional<String> answer);

    /**
     * Provides the next question.
     */
    void nextQuestion();

    /**
     * @return true if the answer selected is correct, false otherwise.
     */
    boolean checkAnswer();

    /**
     * @return the current answer.
     */
    String getCorrectAnswer();

    /**
     * @return the available lives of the player.
     */
    int getRemainingLives();

    /**
     * @return true if game is over, false otherwise.
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
     * @return true if 5050 is available, false otherwise.
     */
    boolean is5050Available();

    /**
     * @return the freeze time.
     */
    double freeze();

    /**
     * Allows the player to switch the current question.
     */
    void skip();

    /**
     * @return the set of wrong answers.
     */
    Set<String> use5050();

    /**
     * @return time available within which the player has to answer.
     */
    int getQuestionDuration();

    /**
     * Allows the player to restart the game.
     */
    void restart();

    /**
     * @return the current score.
     */
    int getScore();

}
