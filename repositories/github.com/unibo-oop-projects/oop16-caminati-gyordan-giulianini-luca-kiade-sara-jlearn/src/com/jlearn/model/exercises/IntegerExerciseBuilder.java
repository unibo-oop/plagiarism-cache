package com.jlearn.model.exercises;

import org.apache.log4j.Logger;

import com.jlearn.view.utilities.enums.ExerciseType;

/**
 *
 * Builds a Integer exercise.
 */
public class IntegerExerciseBuilder extends AbstractExerciseBuilder<Integer> {

    private static final Logger LOG = Logger.getLogger(IntegerExerciseBuilder.class);

    /**
     *
     * The constructor.
     *
     * @param type
     *            the exercise type
     */
    public IntegerExerciseBuilder(final ExerciseType type) {
        super(type);
        if (!type.getAnswersType().equals(Integer.class)) {
            LOG.warn("Can not build an Integer exercise, because the ExerciseType answer type is not Integer.class");
            throw new IllegalArgumentException("Wrong Exercise type. You must "
                    + "choose an exercise type whose answersType is "
                    + Integer.class.getName());
        }
    }

}
