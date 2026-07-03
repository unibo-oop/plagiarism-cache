package org.gitgud.application;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gitgud.application.branch.BranchController;
import org.gitgud.application.branch.BranchControllerImpl;
import org.gitgud.application.diff.DiffOverviewController;
import org.gitgud.application.diff.DiffOverviewControllerImpl;
import org.gitgud.application.diff.DiffViewerController;
import org.gitgud.application.diff.DiffViewerControllerImpl;
import org.gitgud.application.layout.LayoutController;
import org.gitgud.application.layout.RightSideLayout;
import org.gitgud.application.log.LogsDisplayController;
import org.gitgud.application.log.LogsDisplayControllerImpl;
import org.gitgud.application.merge.MergeController;
import org.gitgud.application.merge.MergeControllerImpl;
import org.gitgud.application.modal.ModalBoxController;
import org.gitgud.application.overview.OverviewController;
import org.gitgud.application.overview.OverviewControllerImpl;
import org.gitgud.application.remote.RemoteController;
import org.gitgud.application.remote.RemoteControllerImpl;
import org.gitgud.application.stage.CommitAreaController;
import org.gitgud.application.stage.CommitAreaControllerImpl;
import org.gitgud.application.stage.StagingAreaController;
import org.gitgud.application.stage.StagingAreaControllerImpl;
import org.gitgud.application.toolbar.ToolbarController;
import org.gitgud.application.toolbar.ToolbarControllerImpl;
import org.gitgud.events.EscPressedListener;
import org.gitgud.events.ExitActionListener;
import org.gitgud.events.ProgressListener;
import org.gitgud.events.repository.RepositoryChangedListener;
import org.gitgud.exceptions.GitGudUnckeckedException;
import org.gitgud.model.Model;
import org.gitgud.model.commons.Commit;
import org.gitgud.model.diff.DiffManager;
import org.gitgud.utils.Utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.input.Clipboard;
import javafx.scene.input.DataFormat;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * An implementation of the WorkingAreaController.
 */
public class WorkingAreaControllerImpl
        implements WorkingAreaController, RepositoryChangedListener, EscPressedListener, ExitActionListener {

    private final Model model;

    private final ApplicationController parent;
    private final NotificationManager nm;
    private Map<String, Object> namespace;
    private Pane pane;

    private final LayoutController rightSide;

    private final StagingAreaController stagingArea;
    private final CommitAreaController commitArea;
    private final LogsDisplayController logsDisplay;
    private final DiffOverviewController diffOverview;
    private final DiffViewerController diffViewer;

    private final BranchController branchController;
    private final RemoteController remoteController;
    private final MergeController mergeController;

    private List<Commit> selectedCommits = Collections.emptyList();

    private ShowingMode currentMode;

    private boolean escPressed;
    private boolean exitCommand;

    /**
     * @param parent
     *            the parent controller
     * @param model
     *            the application model
     */
    public WorkingAreaControllerImpl(final ApplicationController parent, final Model model) {
        this.parent = parent;
        this.model = model;

        rightSide = new RightSideLayout();

        stagingArea = new StagingAreaControllerImpl(this, model);
        commitArea = new CommitAreaControllerImpl(this, model);
        logsDisplay = new LogsDisplayControllerImpl(this, model);
        diffOverview = new DiffOverviewControllerImpl(this, model);
        diffViewer = new DiffViewerControllerImpl(this);

        branchController = new BranchControllerImpl(this, model);
        remoteController = new RemoteControllerImpl(this, model);
        mergeController = new MergeControllerImpl(this, model);

        final FXMLLoader loader = new FXMLLoader();
        loader.setResources(Utils.getLabelsBundle());
        try {
            pane = loader.load(this.getClass().getResourceAsStream("WorkingArea.fxml"));
            namespace = loader.getNamespace();
        } catch (final Exception e) {
            throw new GitGudUnckeckedException("Loading failed: WorkingArea.fxml");
        }

        final OverviewController overview = new OverviewControllerImpl(this, model);
        final ToolbarController toolbar = new ToolbarControllerImpl(this, model);

        ((Pane) namespace.get("toolbarWrapper")).getChildren().add(toolbar.getPane());
        final Pane overviewWrapper = (Pane) namespace.get("overviewWrapper");
        final Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        nm = new NotificationManagerImpl();
        overviewWrapper.getChildren().addAll(overview.getPane(), spacer, nm.getPane());

        VBox.setVgrow(rightSide.getPane(), Priority.ALWAYS);

        parent.addEscPressedListener(this);
        parent.addExitActionListener(this);

        model.getRepositoryModel().addRepositoryChangedListener(this);
    }

    @Override
    public boolean checkRepositoryEmpty() {
        if (model.getBranchModel().getLocalBranches().isEmpty()) {
            showWarningNotification("Repository Empty",
                    "You should do the first commit before doing this operation!");
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean checkRepositoryOpened() {
        if (!model.getRepositoryModel().hasRepositoryOpened()) {
            showWarningNotification("Repository not loaded",
                    "You should open a repository before performing this action!");
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void copyToClipboard(final String text) {
        final Map<DataFormat, Object> source = new HashMap<>();
        source.put(DataFormat.PLAIN_TEXT, text);

        Clipboard.getSystemClipboard().setContent(source);
        showInfoNotification("Copying", "Copied to clipboard!");
    }

    @Override
    public void displayDiffOverviewFromLogs() {
        if (!checkRepositoryOpened() || selectedCommits.isEmpty()) {
            return;
        }

        currentMode = ShowingMode.LOGVIEW_DIFFOVERVIEW;

        diffOverview.showDiff(selectedCommits);

        Utils.updateView(() -> {
            rightSide.setCenterPane(logsDisplay.getPane());
            rightSide.setRightPane(diffOverview.getPane());
            setMainContent(rightSide.getPane());
        });
    }

    @Override
    public void displayDiffOverviewFromToolbar() {
        if (!checkRepositoryOpened() || !checkRepositoryEmpty()) {
            return;
        }

        currentMode = ShowingMode.LOGVIEW_DIFFOVERVIEW;

        diffOverview.showStagingDiff();

        Utils.updateView(() -> {
            rightSide.setCenterPane(logsDisplay.getPane());
            rightSide.setRightPane(diffOverview.getPane());
            setMainContent(rightSide.getPane());
        });
    }

    @Override
    public void displayDiffViewer(final DiffManager manager, final String filePath) {
        if (!checkRepositoryOpened() || !checkRepositoryEmpty()) {
            return;
        }

        currentMode = ShowingMode.DIFFVIEWER_DIFFOVERVIEW;

        diffViewer.prepareManager(manager, filePath);
        diffViewer.updateDifferences();
        Utils.updateView(() -> {
            rightSide.setCenterPane(diffViewer.getPane());
        });
    }

    @Override
    public void displayLogsView() {
        if (!checkRepositoryOpened() || !checkRepositoryEmpty()) {
            return;
        }

        currentMode = ShowingMode.LOGVIEW;

        Utils.updateView(() -> {
            displayDiffOverviewFromLogs();
            rightSide.setCenterPane(logsDisplay.getPane());
            setMainContent(rightSide.getPane());
        });
    }

    @Override
    public void displayStagingView() {
        if (!checkRepositoryOpened()) {
            return;
        }

        currentMode = ShowingMode.STAGEVIEW_COMMITVIEW;

        Utils.updateView(() -> {
            rightSide.setCenterPane(stagingArea.getPane());
            rightSide.setRightPane(commitArea.getPane());
            setMainContent(rightSide.getPane());
            stagingArea.updateView();
            commitArea.updateView();
        });
    }

    @Override
    public BranchController getBranchController() {
        return branchController;
    }

    @Override
    public MergeController getMergeController() {
        return mergeController;
    }

    @Override
    public ModalBoxController getModalBox() {
        return parent.getModalBox();
    }

    @Override
    public Pane getPane() {
        return pane;
    }

    @Override
    public RemoteController getRemoteController() {
        return remoteController;
    }

    @Override
    public List<Commit> getSelectedCommits() {
        return Collections.unmodifiableList(selectedCommits);
    }

    @Override
    public StagingAreaController getStagingAreaController() {
        return stagingArea;
    }

    @Override
    public void onEscPressed() {
        if (currentMode == ShowingMode.DIFFVIEWER_DIFFOVERVIEW) {
            displayDiffOverviewFromLogs();
        } else if (currentMode == ShowingMode.LOGVIEW_DIFFOVERVIEW) {
            displayLogsView();
        }
        escPressed = true;
    }

    @Override
    public void onExit() {
        exitCommand = true;
    }

    @Override
    public void onRepositoryChanged() {
        displayStagingView();
    }

    @Override
    public void openRepositoryBox() {
        parent.openRepositoryBox("open");
    }

    @Override
    public void setWaitingState(final boolean isWaiting) {
        Utils.updateView(() -> parent.setWaitingState(isWaiting));
    }

    @Override
    public void showErrorNotification(final String title, final String description) {
        Utils.updateView(() -> nm.showError(title, description));
    }

    @Override
    public void showInfoNotification(final String title, final String description) {
        Utils.updateView(() -> nm.showInfo(title, description));
    }

    @Override
    public void showSuccessNotification(final String title, final String description) {
        Utils.updateView(() -> nm.showSuccess(title, description));
    }

    @Override
    public void showWarningNotification(final String title, final String description) {
        Utils.updateView(() -> nm.showWarning(title, description));
    }

    @Override
    public ProgressListener startTaskProgressMode(final String taskName) {
        setWaitingState(true);
        escPressed = false;
        exitCommand = false;

        final ProgressListener listener = new ProgressListener() {

            @Override
            public boolean isCancelled() {
                return escPressed || exitCommand;
            }

            @Override
            public void onProgressUpdated(final double progress) {
                parent.setTaskProgress(progress);
            }

            @Override
            public void onTaskChanged(final String taskKey) {
                Utils.updateView(() -> nm.showTaskProgress(taskName, taskKey));
            }
        };

        return listener;
    }

    @Override
    public void stopTaskProgressMode() {
        setWaitingState(false);
        Utils.updateView(() -> nm.hideTaskProgress());
        parent.resetTaskProgress();
    }

    @Override
    public void updateSelectedCommits(final List<Commit> commits) {
        selectedCommits = commits;
    }

    private void setMainContent(final Pane content) {
        final Pane mainWrapper = (Pane) namespace.get("mainWrapper");
        mainWrapper.getChildren().clear();
        mainWrapper.getChildren().add(content);
    }

    private enum ShowingMode {
        DIFFVIEWER_DIFFOVERVIEW, LOGVIEW_DIFFOVERVIEW, STAGEVIEW_COMMITVIEW, LOGVIEW
    }
}
