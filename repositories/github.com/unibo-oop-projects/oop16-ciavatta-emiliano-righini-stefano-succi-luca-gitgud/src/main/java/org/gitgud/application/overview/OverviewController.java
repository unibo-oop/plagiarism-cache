package org.gitgud.application.overview;

import org.gitgud.application.node.Panel;

/**
 * The overview controller relative to the overview panel.
 */
public interface OverviewController extends Panel {

    /**
     * Move the HEAD to the indicated local branch.
     *
     * @param branchName
     *            the local branch name
     */
    void checkoutLocal(String branchName);

    /**
     * Move the HEAD to the indicated remote branch.
     *
     * @param remoteName
     *            the name of the remote of the remote branch
     * @param branchName
     *            the remote branch name
     */
    void checkoutRemote(String remoteName, String branchName);

    /**
     * Delete a local branch.
     *
     * @param branchName
     *            the local branch name
     */
    void deleteLocalBranch(String branchName);

    /**
     * Delete a remote branch.
     *
     * @param remoteName
     *            the name of the remote of the remote branch
     * @param branchName
     *            the remote branch name
     */
    void deleteRemoteBranch(String remoteName, String branchName);

    /**
     * Edit a remote.
     *
     * @param remoteName
     *            the remote name of the remote to edit
     */
    void editRemote(String remoteName);

    /**
     * Fetch a remote.
     *
     * @param remoteName
     *            the remote name of the remote to fetch
     */
    void fetchRemote(String remoteName);

    /**
     * Remove a remote.
     *
     * @param remoteName
     *            the remote name of the remote to remove
     */
    void removeRemote(String remoteName);

    /**
     * Rename a remote branch.
     *
     * @param remoteName
     *            the remote name of the remote branch to rename
     * @param branchName
     *            the remote branch name
     */
    void renameRemoteBranch(String remoteName, String branchName);

    /**
     * @param branchName
     *            the name of the local branch
     * @param enabled
     *            true to enable the branch visualization
     */
    void setLocalBranchEnabled(String branchName, boolean enabled);

    /**
     * @param remoteName
     *            the remote name of the remote branch
     * @param branchName
     *            the name of the remote branch
     * @param enabled
     *            true to enable the branch visualization
     */
    void setRemoteBranchEnabled(String remoteName, String branchName, boolean enabled);

    /**
     * @param tagName
     *            the tag name
     * @param enabled
     *            true to enable the tag visualization
     */
    void setTagEnabled(String tagName, boolean enabled);

    /**
     * @param branchName
     *            the branch name of the branch that request as upstream update
     */
    void setUpstream(String branchName);

}
