package org.gitgud.application;

import java.util.List;

import org.gitgud.application.branch.BranchController;
import org.gitgud.application.merge.MergeController;
import org.gitgud.application.modal.ModalBoxController;
import org.gitgud.application.node.Panel;
import org.gitgud.application.remote.RemoteController;
import org.gitgud.application.stage.StagingAreaController;
import org.gitgud.events.ProgressListener;
import org.gitgud.model.commons.Commit;
import org.gitgud.model.diff.DiffManager;

/**
 * The controller of the internal application pane, that manage all others pane.
 */
public interface WorkingAreaController extends Panel {

    /**
     * Check if the repository is no empty (e.g. has no commits) and show a warning notification if it is true.
     *
     * @return true if the repository if empty
     */
    boolean checkRepositoryEmpty();

    /**
     * Check if the repository is opened and show a warning notification if no repository is opened.
     *
     * @return true if there is a repository opened
     */
    boolean checkRepositoryOpened();

    /**
     * Copy a text to the text clipboard.
     *
     * @param text
     *            the text to copy to the clipboard
     */
    void copyToClipboard(String text);

    /**
     * Display the diff overview from the logs.
     */
    void displayDiffOverviewFromLogs();

    /**
     * Display the diff overview from the toolbar.
     */
    void displayDiffOverviewFromToolbar();

    /**
     * Display the diff viewer that contains the differences of a file.
     *
     * @param manager
     *            the current DiffManager that contains the file differences
     * @param filePath
     *            the file path
     */
    void displayDiffViewer(DiffManager manager, String filePath);

    /**
     * Display the log view.
     */
    void displayLogsView();

    /**
     * Display the staging view.
     */
    void displayStagingView();

    /**
     * @return the branch controller
     */
    BranchController getBranchController();

    /**
     * @return the merge controller
     */
    MergeController getMergeController();

    /**
     * @return the modal box controller
     */
    ModalBoxController getModalBox();

    /**
     * @return the remote controller
     */
    RemoteController getRemoteController();

    /**
     * @return the selected commits in the log view
     */
    List<Commit> getSelectedCommits();

    /**
     * @return the staging area controller
     */
    StagingAreaController getStagingAreaController();

    /**
     * Open the repository box to open, clone or init a repository.
     */
    void openRepositoryBox();

    /**
     * @param isWaiting
     *            true to block the interface with a loader.
     */
    void setWaitingState(boolean isWaiting);

    /**
     * Show an error notification popup.
     *
     * @param title
     *            the notification popup title
     * @param description
     *            the notification popup description
     */
    void showErrorNotification(String title, String description);

    /**
     * Show an info notification popup.
     *
     * @param title
     *            the notification popup title
     * @param description
     *            the notification popup description
     */
    void showInfoNotification(String title, String description);

    /**
     * Show a success notification popup.
     *
     * @param title
     *            the notification popup title
     * @param description
     *            the notification popup description
     */
    void showSuccessNotification(String title, String description);

    /**
     * Show a warning notification popup.
     *
     * @param title
     *            the notification popup title
     * @param description
     *            the notification popup description
     */
    void showWarningNotification(String title, String description);

    /**
     * Create a progress listener for a new task and show the progress.
     *
     * @param taskName
     *            the task name
     * @return the progress listener associated with the current task
     */
    ProgressListener startTaskProgressMode(String taskName);

    /**
     * Stop the current task and hide the progress.
     */
    void stopTaskProgressMode();

    /**
     * Update the selected commits in the log view.
     *
     * @param commits
     *            the list of selected commits, from the newer to the laster
     */
    void updateSelectedCommits(List<Commit> commits);

}
