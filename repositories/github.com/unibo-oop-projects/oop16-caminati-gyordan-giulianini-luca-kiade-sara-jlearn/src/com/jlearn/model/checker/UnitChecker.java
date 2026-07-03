package com.jlearn.model.checker;

import java.util.List;

/**
 *
 * Interface for checking the whole unit.
 *
 */
public interface UnitChecker {

    /**
     * @return the unit score
     */
    int getUnitTotalScore();

    /**
     * @return an ordered list containing the score of each exercise.
     */
    List<Integer> getUnitScores();

    /**
     * @return an ordered list containing the detailed log of each exercise.
     */
    List<CheckLog> getUnitCheckLogs();

    /**
     * @param result
     *            the result you want to filter by.
     * @return the total number of the answer given in this unit which match the given result
     */
    int totalAnswersByResult(Checker.Result result);

}
