package org.gitgud.application.toolbar;

import org.gitgud.application.node.Panel;

/**
 * The toolbar controller relative to the toolbar pane.
 */
public interface ToolbarController extends Panel {

    /**
     * Command the branch action.
     */
    void doBranchAction();

    /**
     * Command the diff action.
     */
    void doDiffAction();

    /**
     * Command the log action.
     */
    void doLogAction();

    /**
     * Command the merge action.
     */
    void doMergeMode();

    /**
     * Command the pull action.
     */
    void doPullAction();

    /**
     * Command the push action.
     */
    void doPushAction();

    /**
     * Command the rebase action.
     */
    void doRebaseAction();

    /**
     * Command the remote action.
     */
    void doRemoteAction();

    /**
     * Command the repository action.
     */
    void doRepositoryAction();

    /**
     * Command the settings action.
     */
    void doSettingsAction();

    /**
     * Command the stage action.
     */
    void doStageAction();

    /**
     * Command the stash action.
     */
    void doStashAction();

    /**
     * Command the tag action.
     */
    void doTagAction();

    /**
     * Command the user action.
     */
    void doUserAction();

}
