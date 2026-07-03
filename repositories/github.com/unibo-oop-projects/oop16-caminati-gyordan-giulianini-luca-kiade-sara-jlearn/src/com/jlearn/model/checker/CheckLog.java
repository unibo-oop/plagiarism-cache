package com.jlearn.model.checker;

import java.io.Serializable;
import java.util.List;

/**
 *
 * Exercises checking information tracer.
 *
 */
public interface CheckLog extends Serializable {
    /**
     * @param result
     *            the result you want to filter by.
     * @return the number of the answers which result matches the result parameter.
     */
    int getAnswersNumberByResult(Checker.Result result);

    /**
     * @param result
     *            the result you want to filter by.
     * @return the given answers IDs, matching the result parameter.
     */
    List<Integer> getAnswersIDsByResult(Checker.Result result);

}
