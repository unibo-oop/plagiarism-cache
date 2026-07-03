package org.gitgud.application.branch;

/**
 * The branch controller relative to the branch managements.
 */
public interface BranchController {

    /**
     * Perform the checkout operation with a local branch.
     *
     * @param branchName
     *            the local branch to move in
     */
    void checkoutLocalBranch(String branchName);

    /**
     * Perform the checkout operation with a remote branch.
     *
     * @param branchName
     *            the remote branch to move in
     */
    void checkoutRemoteBranch(String branchName);

    /**
     * Create a new branch from the HEAD.
     *
     * @param startPoint
     *            the start point
     */
    void createBranch(String startPoint);

    /**
     * Create a new branch from the specified ref.
     *
     * @param branchName
     *            the new branch name
     * @param ref
     *            the starting point
     */
    void createBranchFromRef(String branchName, String ref);

    /**
     * Delete a local branch.
     *
     * @param branchName
     *            the name of the local branch to delete
     */
    void deleteLocalBranch(String branchName);

    /**
     * Delete a remote branch.
     *
     * @param branchName
     *            the name of the remote branch to delete
     */
    void renameLocalBranch(String branchName);

}
