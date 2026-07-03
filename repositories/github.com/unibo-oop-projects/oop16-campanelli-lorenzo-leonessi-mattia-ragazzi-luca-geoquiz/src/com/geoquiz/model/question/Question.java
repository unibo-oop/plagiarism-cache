package com.geoquiz.model.question;

import java.util.Set;

/**
 * This interface represents the concept of question.
 *
 *
 */
public interface Question {

    /**
     * This method pushes out the question to be asked.
     * @return the question to be asked.
     */
    String getQuestion();

    /**
     * This method provides the caller the set of possible answers.
     * @return the list of possible answers
     */
    Set<String> getAnswers();

    /**
     * This method provides the correct answer.
     * @return the correct answer
     */
    String getCorrectAnswer();

    /**
     * @param answer
     *           the selected answer
     * @return true if the answer is correct, false otherwise.
     *
     */
    boolean isAnswerCorrect(String answer);

    /**
     *This interface represents a builder for the question according to Builder design pattern.
     *
     */
    interface Builder {

        static Builder createBuilder() {
            return new QuestionBuilderImpl();
        }

        /** Adds a question.
         * @param question
         *           the question to be added.
         * @return Builder, according to fluent interface.
         */
        Builder addQuestion(String question);

        /**Adds an answer to the answers list.
         * @param answer
         *          the answer to be added.
         * @return Builder, according to fluent interface.
         */
        Builder addAnswer(String answer);

        /** Adds the correct answer
         * @return Builder, according to fluent interface.
         */
        Builder addCorrectAnswer(String correctAnswer);

        /** Builds a new question
         * @throws IllegalStateException
         *              when called without having called all the previous methods before.
         * @return a new Question.
         */
        Question build();

        /** This method shows the size of the set of possible answers.
         * @return the size of the set.
         */
        int getAnswersSetSize();
    }

}
