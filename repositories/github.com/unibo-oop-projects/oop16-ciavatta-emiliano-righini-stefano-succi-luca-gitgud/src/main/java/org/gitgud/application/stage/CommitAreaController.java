package org.gitgud.application.stage;

import org.gitgud.application.node.Panel;

/**
 * Commit area controller.
 */
public interface CommitAreaController extends Panel {

    /**
     * Perform a commit.
     *
     * @param message
     *            the message of the commit
     * @param isAmend
     *            true if is an amend operation
     */
    void doCommitOperation(String message, boolean isAmend);

    /**
     * update the commit area view.
     */
    void updateView();
}
