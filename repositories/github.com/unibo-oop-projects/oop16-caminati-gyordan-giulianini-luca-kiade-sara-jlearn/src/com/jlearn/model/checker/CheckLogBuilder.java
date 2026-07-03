package com.jlearn.model.checker;

/**
 * Builds a CheckLog.
 */
public interface CheckLogBuilder {

    /**
     * Adds a new answer.
     *
     * @param id
     *            the answer identifier
     * @param result
     *            the result of the answer correction
     */
    void registerAnswer(int id, Checker.Result result);

    /**
     * Removes a answer and its result.
     *
     * @param id
     *            the answer identifier
     */
    void removeRegisteredAnswer(int id);

    /**
     * Builds the CheckLog object.
     *
     * @return the built CheckLog object.
     */
    CheckLog build();

    /**
     * Resets the builder.
     */
    void reset();
}
