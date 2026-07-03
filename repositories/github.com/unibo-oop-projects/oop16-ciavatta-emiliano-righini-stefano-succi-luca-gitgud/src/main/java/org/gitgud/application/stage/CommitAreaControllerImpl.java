package org.gitgud.application.stage;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.gitgud.application.WorkingAreaController;
import org.gitgud.application.utils.ApplicationUtils;
import org.gitgud.application.utils.ColorManager;
import org.gitgud.events.repository.RepositoryChangedListener;
import org.gitgud.events.repository.RepositoryUpdatedListener;
import org.gitgud.exceptions.GitGudUnckeckedException;
import org.gitgud.model.Model;
import org.gitgud.model.stage.CommitParamBuilder;
import org.gitgud.model.stage.StageError;
import org.gitgud.model.stage.StageModel;
import org.gitgud.model.utils.CommandStatus;
import org.gitgud.utils.Pair;
import org.gitgud.utils.ResourceType;
import org.gitgud.utils.Utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 *
 */
public class CommitAreaControllerImpl
        implements CommitAreaController, RepositoryChangedListener, RepositoryUpdatedListener {

    private static final String NO_COMMITTER_NAME = "label.commit.committer.noname";
    private static final String NO_COMMITTER_EMAIL = "label.commit.committer.noemail";
    private final WorkingAreaController parent;
    private CommitAreaView view;
    private Pane pane;

    private final Model model;
    private final StageModel stageModel;
    private final ColorManager colorManager;
    private final Map<String, Color> usedColor;
    private Optional<Pair<String, String>> committerInfo;

    /**
     * @param parent
     *            the working area
     * @param model
     *            the application model
     */
    public CommitAreaControllerImpl(final WorkingAreaController parent, final Model model) {
        this.parent = parent;
        this.stageModel = model.getStageModel();
        this.model = model;
        this.colorManager = ApplicationUtils.createColorManager();
        this.usedColor = new HashMap<>();

        final FXMLLoader loader = new FXMLLoader();
        loader.setResources(Utils.getLabelsBundle());
        try {
            this.pane = loader.load(this.getClass().getResourceAsStream("CommitArea.fxml"));
            this.view = loader.getController();
            this.view.attachController(this);
        } catch (final Exception e) {
            throw new GitGudUnckeckedException("Loading failed: CommitArea.fxml");
        }
        VBox.setVgrow(this.pane, Priority.ALWAYS);
    }

    private void displayCommitterInfo() {
        Pair<String, String> ci = new Pair<>("", "");
        if (this.committerInfo.isPresent()) {
            ci = this.committerInfo.get();
        } else {
            ci = new Pair<>(Utils.resolveString(ResourceType.LABELS, CommitAreaControllerImpl.NO_COMMITTER_NAME),
                    Utils.resolveString(ResourceType.LABELS, CommitAreaControllerImpl.NO_COMMITTER_EMAIL));
        }
        this.view.setCommitter(ci.getX(), ci.getY(),
                ApplicationUtils.getAuthorInitials(ci.getX()),
                this.getColor("initials"));
    }

    private void displayWarningError() {
        if (this.stageModel.getError().isPresent()) {
            final StageError error = this.stageModel.getError().get();
            this.parent.showErrorNotification(error.getTitle(), error.getMessage());
        }
        if (this.stageModel.getWarning().isPresent()) {
            final StageError warning = this.stageModel.getWarning().get();
            this.parent.showWarningNotification(warning.getTitle(), warning.getMessage());
        }
    }

    @Override
    public void doCommitOperation(final String commit, final boolean isAmend) {
        Utils.doHardWork(() -> {
            if (!this.committerInfo.isPresent()) {
                this.parent.showWarningNotification(
                        Utils.resolveString(ResourceType.ERRORS, StageError.MISSING_AUTHOR.getTitle()),
                        Utils.resolveString(ResourceType.ERRORS, StageError.MISSING_AUTHOR.getMessage()));
            } else {
                final String name = this.committerInfo.get().getX();
                final String email = this.committerInfo.get().getY();
                if (this.stageModel.commit(CommitParamBuilder.createCommitParamBuilder()
                        .message(commit)
                        .amend(isAmend)
                        .author(name, email)
                        .committer(name, email)
                        .build())
                        .equals(CommandStatus.SUCCESS)) {
                    this.view.clearTaCommit();
                } else {
                    this.displayWarningError();
                }
                this.model.getRepositoryModel().sendManualRepositoryUpdate();
            }
        });
    }

    private Color getColor(final String id) {
        if (this.usedColor.containsKey(id)) {
            return this.usedColor.get(id);
        } else {
            final Color c = this.colorManager.requestColor();
            this.usedColor.put(id, c);
            return c;
        }
    }

    @Override
    public Pane getPane() {
        return this.pane;
    }

    @Override
    public void onContentUpdated() {
        Utils.updateView(() -> this.updateView());
    }

    @Override
    public void onManualUpdated() {
        Utils.updateView(() -> this.updateView());
    }

    @Override
    public void onRepositoryChanged() {
        Utils.updateView(() -> this.updateView());
    }

    @Override
    public void onRepositoryUpdated() {
        Utils.updateView(() -> this.updateView());
    }

    private void updateCommitterInfo() {
        final Optional<Pair<String, String>> ci = this.model.getRepositoryModel().getGlobalIdentity();
        if (ci.isPresent()) {
            this.committerInfo = ci;
        } else {
            this.committerInfo = Optional.empty();
        }
    }

    @Override
    public void updateView() {
        this.updateCommitterInfo();
        this.displayCommitterInfo();
    }
}
