package com.jlearn.model.exercises;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.jlearn.view.utilities.enums.ExerciseType;

/**
 *
 * Builds a multiple choice exercise.
 */
public class StringExerciseBuilder extends AbstractExerciseBuilder<String> {
    private static final Logger LOG = Logger.getLogger(StringExerciseBuilder.class);

    /**
     *
     * The constructor.
     *
     * @param type
     *            the exercise type
     */
    public StringExerciseBuilder(final ExerciseType type) {
        super(type);
        LOG.setLevel(Level.WARN);
        if (!type.getAnswersType().equals(String.class)) {
            LOG.warn("Can not build a String exercise, because the ExerciseType answer type is not String.class");
            throw new IllegalArgumentException("Wrong Exercise type. You must "
                    + "choose an exercise type whose answersType is "
                    + String.class.getName());
        }
    }

}
