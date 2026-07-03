package com.geoquiz.controller.quiz;

import javax.xml.bind.JAXBException;

/**
 * A class that implements the time available within which the player has to
 * answer. Different levels of game are available based on the player's score.
 *
 */
public class TimeController {

    private static final int EASY_LEVEL = 8;
    private static final int MEDIUM_LEVEL = 6;
    private static final int HARD_LEVEL = 4;
    private static final int IMPOSSIBLE_LEVEL = 2;
    private static final int BASIC_SCORE = 5;
    private static final int EASY_SCORE = 10;
    private static final int MEDIUM_SCORE = 20;
    private static final int HARD_SCORE = 30;

    private final QuizController quiz;
    private int time;

    /**
     * @param quiz
     *            QuizController object.
     * @throws JAXBException
     *             except.
     */
    public TimeController(final QuizController quiz) throws JAXBException {
        this.quiz = quiz;
    }

    private void setTimeLevel() {
        this.time = this.setTime(this.quiz.getScore());

    }

    private int setTime(final int score) {

        if (score < TimeController.BASIC_SCORE) {

            return this.quiz.getQuestionDuration();
        } else if (score >= TimeController.BASIC_SCORE && score < TimeController.EASY_SCORE) {
            return TimeController.EASY_LEVEL;
        } else if (score >= TimeController.EASY_SCORE && score < TimeController.MEDIUM_SCORE) {
            return TimeController.MEDIUM_LEVEL;
        } else if (score >= TimeController.MEDIUM_SCORE && score < TimeController.HARD_SCORE) {
            return TimeController.HARD_LEVEL;
        } else {
            return TimeController.IMPOSSIBLE_LEVEL;
        }
    }

    /**
     * @return time available whithin which the player has to answer.
     */
    public int getTime() {
        this.setTimeLevel();
        return this.time;
    }
}
