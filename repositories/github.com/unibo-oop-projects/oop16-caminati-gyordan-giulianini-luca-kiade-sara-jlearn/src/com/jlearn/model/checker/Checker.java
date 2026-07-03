package com.jlearn.model.checker;

import java.util.List;
import java.util.Optional;

import com.jlearn.model.exercises.Exercise;

/**
 * An interface for the exercises correction.
 *
 */
public interface Checker {
    /**
     *
     * An enumeration to define the possible results of a single answers correction and the related score assignment.
     *
     */
    enum Result {

        WRONG_ANSWER("Wrong Answer", 0), RIGHT_ANSWER("Right Answer", 2), NULL_ANSWER("Null Answer", 1);

        private final int    points;
        private final String name;

        /**
         *
         * @return value of Answer.
         */
        public int getPoints() {
            return this.points;
        }

        Result(final String name, final int points) {
            this.name = name;
            this.points = points;

        }

        /**
         *
         * @return the given name.
         *
         */
        public String getName() {
            return this.name;
        }

    }

    /**
     *
     * @param ex
     *            the exercise
     * @param givenAnswers
     *            the answers to check
     * @return an instance of CheckLog, containing the correction information.
     */
    CheckLog check(Exercise<?> ex, List<Optional<?>> givenAnswers);

    /**
     *
     * @param ex
     *            the exercise
     * @return the score reached by having all the answers right, without considering the level modifiers (such as time)
     */
    int getMaxBasicScore(Exercise<?> ex);
}
