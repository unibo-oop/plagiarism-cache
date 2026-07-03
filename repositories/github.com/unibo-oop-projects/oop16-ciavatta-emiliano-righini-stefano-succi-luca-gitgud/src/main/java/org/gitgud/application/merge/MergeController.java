package org.gitgud.application.merge;

/**
 * The branch controller relative to the merge action.
 */
public interface MergeController {

    /**
     * Merge the branch indicated to the HEAD.
     *
     * @param branchName
     *            the name of the branch to be merged.
     */
    void merge(String branchName);

}
