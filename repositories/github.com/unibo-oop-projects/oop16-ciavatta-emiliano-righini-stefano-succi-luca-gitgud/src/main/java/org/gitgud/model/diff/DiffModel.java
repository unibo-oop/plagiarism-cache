package org.gitgud.model.diff;

/**
 * The model to calculate differences between two stages.
 */
public interface DiffModel {

    /**
     * Calculate the differences between two stages.
     *
     * @param diffParam
     *            - all arguments of the diff operation
     * @return the DiffManager
     */
    DiffManager diff(DiffParam diffParam);

}
