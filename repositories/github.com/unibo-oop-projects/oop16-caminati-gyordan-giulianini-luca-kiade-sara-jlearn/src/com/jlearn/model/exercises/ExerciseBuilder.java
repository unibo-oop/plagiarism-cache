package com.jlearn.model.exercises;

import java.util.List;

/**
 *
 * A Builder for exercises.
 *
 * @param <X>
 *            the answers type
 *
 */
public interface ExerciseBuilder<X> {
    /**
     *
     * @param quest
     *            the new question
     * @param answers
     *            the answers related to the new question
     */
    void addQuestion(String quest, List<X> answers);

    /**
     *
     * @param quest
     *            the question to remove
     */
    void removeQuestion(String quest);

    /**
     * @return the built Exercise
     */
    Exercise<X> build();

    /**
     *
     * resets the builder.
     */
    void reset();

    /**
     *
     * @return true if the exercise has been built, false otherwise
     */
    boolean isBuilt();

}
