package com.jlearn.model.exercises;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.jlearn.view.utilities.enums.ExerciseType;

/**
 *
 * Builds a Boolean exercise.
 */
public class BooleanExerciseBuilder extends AbstractExerciseBuilder<Boolean> {
    private static final Logger LOG = Logger.getLogger(BooleanExerciseBuilder.class);

    /**
     *
     * The constructor.
     *
     * @param type
     *            the exercise type
     */
    public BooleanExerciseBuilder(final ExerciseType type) {
        super(type);
        LOG.setLevel(Level.WARN);
        if (!type.getAnswersType().equals(Boolean.class)) {
            LOG.warn("Can not build a Boolean exercise, because the ExerciseType answer type is not Boolean.class");
            throw new IllegalArgumentException("Wrong Exercise type. You must "
                    + "choose an exercise type whose answersType is "
                    + Boolean.class.getName());
        }

    }

}
