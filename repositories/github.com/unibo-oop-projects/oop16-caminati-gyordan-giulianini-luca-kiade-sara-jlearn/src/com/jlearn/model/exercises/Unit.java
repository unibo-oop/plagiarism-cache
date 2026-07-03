package com.jlearn.model.exercises;

import java.util.List;

import com.jlearn.view.utilities.enums.ExerciseType;

/**
 * Unit interface.
 *
 */
public interface Unit {
    /**
     *
     * @return The exercises contained in this unit
     */
    List<Exercise<?>> getExercises();

    /**
     * @param type
     *            the type of the exercises you want back
     * @return The exercises contained in this unit of the chosen type.
     */
    List<Exercise<?>> getExercisesByType(ExerciseType type);

    /**
     * @return for each exercise a list that for each question containes the number of related answers.
     */
    List<List<Integer>> getDetailedNumAnswers();

    /**
     * @return the unit identifier.
     */
    int getUnitID();

    /**
     * @return the unit title.
     */
    String getUnitTitle();

}
