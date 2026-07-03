package org.gitgud.application.diff;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.gitgud.application.WorkingAreaController;
import org.gitgud.application.utils.ApplicationUtils;
import org.gitgud.application.utils.ColorManager;
import org.gitgud.events.repository.RepositoryChangedListener;
import org.gitgud.exceptions.GitGudUnckeckedException;
import org.gitgud.model.Model;
import org.gitgud.model.commons.Author;
import org.gitgud.model.commons.Commit;
import org.gitgud.model.diff.DiffManager;
import org.gitgud.model.diff.DiffParam;
import org.gitgud.model.diff.DiffParamBuilder;
import org.gitgud.model.utils.ChangeType;
import org.gitgud.utils.Pair;
import org.gitgud.utils.Utils;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * The implementation of diff overview controller.
 */
public class DiffOverviewControllerImpl implements DiffOverviewController, RepositoryChangedListener {

    private static final int SHORT_HASH_LENGTH = 6;

    private static final Insets DETAILS_TITLE_INSETS = new Insets(15.0, 0.0, 0.0, 0.0);

    private final Model model;
    private final WorkingAreaController parent;

    private DiffDetailsView firstDetailsView;
    private DiffDetailsView secondDetailsView;
    private Pane firstDetailsPane;
    private Pane secondDetailsPane;

    private DiffChangesView firstChangesView;
    private DiffChangesView secondChangesView;
    private Pane firstChangesPane;
    private Pane secondChangesPane;

    private final Pane pane;

    private DiffManager firstManager;
    private DiffManager secondManager;

    private ColorManager colorManager;
    private final Map<String, Color> usedColors = new HashMap<>();

    /**
     * @param parent
     *            - the parent controller
     * @param model
     *            - the application model
     */
    public DiffOverviewControllerImpl(final WorkingAreaController parent, final Model model) {
        this.parent = parent;
        this.model = model;

        pane = new VBox();
        VBox.setVgrow(pane, Priority.ALWAYS);

        final FXMLLoader firstDetailsLoader = new FXMLLoader();
        firstDetailsLoader.setResources(Utils.getLabelsBundle());
        try {
            firstDetailsPane = firstDetailsLoader.load(getClass().getResourceAsStream("DiffDetails.fxml"));
            firstDetailsView = firstDetailsLoader.getController();
        } catch (final Exception e) {
            throw new GitGudUnckeckedException("Loading failed: DiffDetails.fxml");
        }

        final FXMLLoader secondDetailsLoader = new FXMLLoader();
        secondDetailsLoader.setResources(Utils.getLabelsBundle());
        try {
            secondDetailsPane = secondDetailsLoader.load(getClass().getResourceAsStream("DiffDetails.fxml"));
            secondDetailsView = secondDetailsLoader.getController();
        } catch (final Exception e) {
            throw new GitGudUnckeckedException("Loading failed: DiffDetails.fxml");
        }

        final FXMLLoader firstChangesLoader = new FXMLLoader();
        firstChangesLoader.setResources(Utils.getLabelsBundle());
        try {
            firstChangesPane = firstChangesLoader.load(getClass().getResourceAsStream("DiffChanges.fxml"));
            firstChangesView = firstChangesLoader.getController();
            firstChangesView.attachController(this, 1);
        } catch (final Exception e) {
            throw new GitGudUnckeckedException("Loading failed: DiffChanges.fxml");
        }

        final FXMLLoader secondChangesLoader = new FXMLLoader();
        firstChangesLoader.setResources(Utils.getLabelsBundle());
        try {
            secondChangesPane = secondChangesLoader.load(getClass().getResourceAsStream("DiffChanges.fxml"));
            secondChangesView = secondChangesLoader.getController();
            secondChangesView.attachController(this, 2);
        } catch (final Exception e) {
            throw new GitGudUnckeckedException("Loading failed: DiffChanges.fxml");
        }

        model.getRepositoryModel().addRepositoryChangedListener(this);
    }

    @Override
    public void computeFileDiff(final String filePath, final int id) {
        if (id == 1) {
            parent.displayDiffViewer(firstManager, filePath);
        } else {
            parent.displayDiffViewer(secondManager, filePath);
        }
    }

    @Override
    public Pane getPane() {
        return pane;
    }

    @Override
    public void onRepositoryChanged() {
        colorManager = ApplicationUtils.createColorManager();
        usedColors.clear();
    }

    @Override
    public void showDiff(final List<Commit> selectedCommits) {
        parent.setWaitingState(true);
        final DiffParam diffParam;
        final Commit commitFrom;
        final Optional<Commit> commitTo;
        final String hashTo;

        if (selectedCommits.size() == 1) {
            commitFrom = selectedCommits.get(0);
            commitTo = Optional.empty();

            if (commitFrom.getParentsId().isEmpty()) {
                hashTo = "";
            } else {
                hashTo = commitFrom.getParentsId().get(0);
            }
        } else {
            commitFrom = selectedCommits.get(0);
            commitTo = Optional.of(selectedCommits.get(selectedCommits.size() - 1));
            hashTo = commitTo.get().getID();
        }

        diffParam = DiffParamBuilder.createDiffParamBuilder().oldCommitHash(hashTo).newCommitHash(commitFrom.getID())
                .build();
        firstManager = model.getDiffModel().diff(diffParam);

        Utils.updateView(() -> {
            pane.getChildren().clear();

            if (commitTo.isPresent()) {
                setCommitDetails(firstDetailsView, commitFrom, "Here are the changes between");
                setCommitDetails(secondDetailsView, commitTo.get(), "and");
                pane.getChildren().addAll(firstDetailsPane, secondDetailsPane);
            } else {
                setCommitDetails(firstDetailsView, commitFrom, "Here are the changes");
                pane.getChildren().add(firstDetailsPane);
            }

            firstChangesView.setChanges(firstManager.getChanges());
            pane.getChildren().add(firstChangesPane);
        });

        parent.setWaitingState(false);
    }

    @Override
    public void showStagingDiff() {
        parent.setWaitingState(true);

        final DiffParam unstagedDiffParam;
        final DiffParam stagedDiffParam;

        unstagedDiffParam = DiffParamBuilder.createDiffParamBuilder().setDiffUnstaged().build();
        stagedDiffParam = DiffParamBuilder.createDiffParamBuilder().setDiffStaged().build();
        firstManager = model.getDiffModel().diff(unstagedDiffParam);
        secondManager = model.getDiffModel().diff(stagedDiffParam);

        final List<Pair<String, ChangeType>> unstagedChanges = firstManager.getChanges();
        final List<Pair<String, ChangeType>> stagedChanges = secondManager.getChanges();

        Utils.updateView(() -> {
            final Label lbUnstaged = new Label("Unstaged files (" + unstagedChanges.size() + ")");
            final Label lbStaged = new Label("Staged files (" + stagedChanges.size() + ")");
            lbUnstaged.getStyleClass().add("diff-details-title");
            lbStaged.getStyleClass().add("diff-details-title");
            VBox.setMargin(lbStaged, DETAILS_TITLE_INSETS);

            pane.getChildren().clear();

            firstChangesView.setChanges(unstagedChanges);
            secondChangesView.setChanges(stagedChanges);
            pane.getChildren().addAll(lbUnstaged, firstChangesPane, lbStaged, secondChangesPane);
        });

        parent.setWaitingState(false);
    }

    private void setCommitDetails(final DiffDetailsView view, final Commit commit, final String paneTitle) {
        final Author author = commit.getAuthor();
        final DateFormat df = new SimpleDateFormat("d MMM yyyy @ HH:mm", Utils.getLocale());
        final String date = df.format(commit.getWhen());

        final Color color;
        if (usedColors.containsKey(author.getName())) {
            color = usedColors.get(author.getName());
        } else {
            color = colorManager.requestColor();
            usedColors.put(author.getName(), color);
        }

        view.setTitle(paneTitle);
        view.setAuthor(author.getName(), ApplicationUtils.getAuthorInitials(author.getName()), color);
        view.setMail("<" + author.getMail() + ">");
        view.setHash(shortHash(commit.getID()), commit.getID());
        view.setParents(
                commit.getParentsId().stream().collect(Collectors.toMap(id -> shortHash(id), Function.identity())));
        view.setMessage(commit.getFullMessage());
        view.setDate(date);
    }

    private String shortHash(final String longHash) {
        return longHash.substring(0, SHORT_HASH_LENGTH);
    }

}
