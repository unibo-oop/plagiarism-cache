package org.gitgud.application.stage;

import java.util.Set;

import org.gitgud.application.WorkingAreaController;
import org.gitgud.events.repository.RepositoryChangedListener;
import org.gitgud.events.repository.RepositoryUpdatedListener;
import org.gitgud.exceptions.GitGudUnckeckedException;
import org.gitgud.model.Model;
import org.gitgud.model.stage.CleanParamBuilder;
import org.gitgud.model.stage.ResetParamBuilder;
import org.gitgud.model.stage.StageError;
import org.gitgud.model.stage.StageModel;
import org.gitgud.model.utils.ChangeType;
import org.gitgud.model.utils.CommandStatus;
import org.gitgud.utils.Pair;
import org.gitgud.utils.ResourceType;
import org.gitgud.utils.Utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 *
 */
public class StagingAreaControllerImpl
        implements StagingAreaController, RepositoryChangedListener, RepositoryUpdatedListener {

    private final WorkingAreaController parent;
    private StagingAreaView view;
    private Pane pane;

    private final Model model;
    private final StageModel stageModel;

    /**
     * @param parent
     *            the working area
     * @param model
     *            the application model
     */
    public StagingAreaControllerImpl(final WorkingAreaController parent, final Model model) {
        this.parent = parent;
        this.stageModel = model.getStageModel();
        this.model = model;

        final FXMLLoader loader = new FXMLLoader();
        loader.setResources(Utils.getLabelsBundle());
        try {
            this.pane = loader.load(this.getClass().getResourceAsStream("StagingArea.fxml"));
            this.view = loader.getController();
            this.view.attachController(this);
        } catch (final Exception e) {
            throw new GitGudUnckeckedException("Loading failed: StagingArea.fxml");
        }

        VBox.setVgrow(this.pane, Priority.ALWAYS);
        model.getRepositoryModel().addRepositoryChangedListener(this);
        model.getRepositoryModel().addRepositoryUpdatedListener(this);
    }

    @Override
    public void doAddOperation(final Set<String> paths) {
        Utils.doHardWork(() -> {
            if (this.stageModel.add(paths).equals(CommandStatus.SUCCESS)) {
                this.displaySuccessNotification("add");
            } else {
                this.displayWarningError();
            }
            this.model.getRepositoryModel().sendManualRepositoryUpdate();
        });
    }

    @Override
    public void doCleanOperation(final Set<String> paths) {
        Utils.doHardWork(() -> {
            if (this.stageModel.clean(CleanParamBuilder.createCleanParamBuilder().paths(paths).build())
                    .equals(CommandStatus.SUCCESS)) {
                this.displaySuccessNotification("clean");
            } else {
                this.displayWarningError();
            }
            this.model.getRepositoryModel().sendManualRepositoryUpdate();
        });
    }

    @Override
    public void doRemoveOperation(final Set<String> paths, final boolean cached) {
        Utils.doHardWork(() -> {
            if (this.stageModel.remove(paths, cached).equals(CommandStatus.SUCCESS)) {
                if (cached) {
                    this.displaySuccessNotification("remove");
                } else {
                    this.displaySuccessNotification("delete");
                }
            } else {
                this.displayWarningError();
            }
            this.model.getRepositoryModel().sendManualRepositoryUpdate();
        });
    }

    @Override
    public void doResetOperation(final Set<String> paths) {
        Utils.doHardWork(() -> {
            if (this.stageModel.reset(ResetParamBuilder.createResetParamBuilder().paths(paths).build())
                    .equals(CommandStatus.SUCCESS)) {
                this.displaySuccessNotification("reset");
            } else {
                this.displayWarningError();
            }
            this.model.getRepositoryModel().sendManualRepositoryUpdate();
        });
    }

    @Override
    public Pane getPane() {
        return this.pane;
    }

    @Override
    public void onContentUpdated() {
        this.updateView();
    }

    @Override
    public void onManualUpdated() {
        this.updateView();
    }

    @Override
    public void onRepositoryChanged() {
        this.updateView();
    }

    @Override
    public void onRepositoryUpdated() {
        this.updateView();
    }

    @Override
    public void updateView() {
        Utils.updateView(() -> {
            this.updateUntracked(this.stageModel.getStatusUntracked());
            this.updateUnstaged(this.stageModel.getStatusNotStaged());
            this.updateStaged(this.stageModel.getStatusStaged());
            this.view.setLabelsLength();
        });
    }

    private void updateStaged(final Set<Pair<String, ChangeType>> staged) {
        this.displayWarningError();
        this.view.setStaged(staged);
    }

    private void updateUnstaged(final Set<Pair<String, ChangeType>> unstaged) {
        this.displayWarningError();
        this.view.setNotStaged(unstaged);
    }

    private void updateUntracked(final Set<Pair<String, ChangeType>> untracked) {
        this.displayWarningError();
        this.view.setUntracked(untracked);
    }

    private void displaySuccessNotification(final String command) {
        this.parent.showSuccessNotification(
                Utils.resolveString(ResourceType.MESSAGES, "stage.command." + command + ".success.title"),
                Utils.resolveString(ResourceType.MESSAGES, "stage.command." + command + ".success.message"));
    }

    private void displayWarningError() {
        if (this.stageModel.getError().isPresent()) {
            final StageError error = this.stageModel.getError().get();
            this.parent.showErrorNotification(Utils.resolveString(ResourceType.ERRORS, error.getTitle()),
                    Utils.resolveString(ResourceType.ERRORS, error.getMessage()));
        }
        if (this.stageModel.getWarning().isPresent()) {
            final StageError warning = this.stageModel.getWarning().get();
            this.parent.showWarningNotification(Utils.resolveString(ResourceType.ERRORS, warning.getTitle()),
                    Utils.resolveString(ResourceType.ERRORS, warning.getMessage()));
        }
    }
}
