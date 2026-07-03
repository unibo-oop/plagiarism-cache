package org.gitgud.application.log;

import java.util.List;

import org.gitgud.application.node.Panel;

/**
 *
 *
 */
public interface LogsDisplayController extends Panel {

    /**
     * @param branch
     *            the branch you want to tag
     */
    void addTag(String branch);

    /**
     * @param branch
     *            the branch where you want to start the new branch
     */
    void createBranchFrom(String branch);

    /**
     * @param branch
     *            the branch you want to delete
     */
    void deleteBranch(String branch);

    /**
     * Triggers the merge operation into current HEAD.
     * 
     * @param branch
     *            the identifier of the branch
     */
    void mergeOnHead(String branch);

    /**
     * @param commits
     *            the list of sha ids selected.
     */
    void openDiffOverview(List<String> commits);

    /**
     * Refreshes the log GUI adding new commits and drawing more of the commit tree.
     */
    void refresh();

    /**
     * @param branch
     *            the branch you want to rename
     */
    void renameBranch(String branch);

    /**
     * @param title
     *            the title of the message
     * @param msg
     *            the message to send to the working area controller
     */
    void sendWarningMessage(String title, String msg);
}
