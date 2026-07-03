package com.jlearn.view.utilities.enums;

/**
 * The type of the exercises.
 *
 */
public enum ExerciseType {
    /**
     * Types.
     */
    TRUE_FALSE("TrueFalse", Boolean.class, 1), COMPLETE("Complete", String.class, 10), MULTI("Multi", String.class, 1), AUDIO("Audio", Boolean.class, 1);

    private String   exerciseTypeName;
    private Class<?> answersType;
    private int      maxAnswers;

    ExerciseType(final String exerciseTypeName, final Class<?> answersType,
            final int maxAnswers) {
        this.exerciseTypeName = exerciseTypeName;
        this.answersType = answersType;
        this.maxAnswers = maxAnswers;
    }

    /**
     *
     * @param value
     *            The string to be checked.
     * @return true if is part of {@link ExerciseType} Enum.
     */
    public static boolean isValue(final String value) {

        for (final ExerciseType type : ExerciseType.values()) {
            if (type.name().equals(value)) {
                return true;
            }
        }

        return false;
    }

    /**
     * @return the exerciseTypeName
     */
    public String getExerciseTypeName() {
        return this.exerciseTypeName;
    }

    /**
     * @return the answers type.
     */
    public Class<?> getAnswersType() {
        return this.answersType;
    }

    /**
     * @return the max number of answers allowed.
     */
    public int getMaxAnswers() {
        return this.maxAnswers;
    }

}
