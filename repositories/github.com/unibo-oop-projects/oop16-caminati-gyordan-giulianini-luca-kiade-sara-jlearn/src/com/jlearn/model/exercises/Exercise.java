package com.jlearn.model.exercises;

import java.util.List;

import com.jlearn.model.utilities.Pair;
import com.jlearn.view.utilities.enums.ExerciseType;

/**
 * Exercises interface.
 *
 * @param <X>
 *            the answers type
 *
 */
public interface Exercise<X> {
    /**
     *
     * @return a List containing the questions
     */
    List<String> getQuestions();

    /**
     *
     * @param question
     *            the question you want to know the answer to
     * @return the answers related to the question parameter
     */
    List<X> getAnswer(String question);

    /**
     *
     * @return a List containing all the answers of this exercise, without giving any information about the questions
     *         they are referred to.
     */
    List<X> getFlatAnswers();

    /**
     *
     * @return a List containing all the answers. At each position there's the answer List related to the question which
     *         has the same index. For example at index 0, there's the answers list related to the question at index 0
     *         in the questions list (that you can obtain by calling the "getQuestions" method.
     */
    List<List<X>> getAnswers();

    /**
     *
     * @return the number of answers related to the whole exercise
     */
    int getNumAnswers();

    /**
     *
     * @return the number of answers related to each question
     */
    List<Integer> getNumAnswersForEachQuestion();

    /**
     *
     * @return the exercise type.
     */
    ExerciseType getType();

    /**
     *
     * @return a list containing all the questions and their corresponding answers.
     */
    List<Pair<String, List<X>>> getQuestionsAndAnswers();

}
