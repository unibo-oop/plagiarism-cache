package org.gitgud.application.overview;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.gitgud.application.WorkingAreaController;
import org.gitgud.application.utils.ApplicationUtils;
import org.gitgud.application.utils.ColorManager;
import org.gitgud.events.repository.RepositoryChangedListener;
import org.gitgud.events.repository.RepositoryUpdatedListener;
import org.gitgud.exceptions.GitGudUnckeckedException;
import org.gitgud.model.Model;
import org.gitgud.model.utils.CommandStatus;
import org.gitgud.model.utils.RepositoryUtils;
import org.gitgud.utils.Log;
import org.gitgud.utils.Pair;
import org.gitgud.utils.ResourceType;
import org.gitgud.utils.Utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * The implementation of the overview controller.
 */
public class OverviewControllerImpl
        implements OverviewController, RepositoryChangedListener, RepositoryUpdatedListener {

    private final Model model;

    private final WorkingAreaController parent;
    private OverviewView view;
    private Pane pane;
    private String currentBranch = "master";

    private final ColorManager remoteColorManager;
    private final Map<String, Color> remoteColors = new HashMap<>();

    /**
     * @param parent
     *            - the parent controller
     * @param model
     *            - the application model
     */
    public OverviewControllerImpl(final WorkingAreaController parent, final Model model) {
        this.parent = parent;
        this.model = model;

        final FXMLLoader loader = new FXMLLoader();
        loader.setResources(Utils.getLabelsBundle());
        try {
            pane = loader.load(getClass().getResourceAsStream("Overview.fxml"));
            view = loader.getController();
            view.attachController(this);
        } catch (final Exception e) {
            throw new GitGudUnckeckedException("Loading failed: Overview.fxml");
        }

        model.getRepositoryModel().addRepositoryChangedListener(this);
        model.getRepositoryModel().addRepositoryUpdatedListener(this);

        remoteColorManager = ApplicationUtils.getApplicationProperties().getRemoteColorManager();
        remoteColors.clear();
    }

    @Override
    public void checkoutLocal(final String branchName) {
        if (branchName.equals(currentBranch)) {
            parent.showWarningNotification(Utils.resolveString(ResourceType.MESSAGES, "overview.checkout.deny.title"),
                    Utils.resolveString(ResourceType.MESSAGES, "overview.checkout.deny.description"));
            return;
        }

        parent.setWaitingState(true);
        displayCheckoutResult(model.getBranchModel().checkoutToLocal(branchName), branchName);
        parent.setWaitingState(false);
    }

    @Override
    public void checkoutRemote(final String remoteName, final String branchName) {
        parent.setWaitingState(true);
        displayCheckoutResult(model.getBranchModel().checkoutToRemote("refs/remotes/" + remoteName + "/" + branchName),
                branchName);
        parent.setWaitingState(false);
    }

    @Override
    public void deleteLocalBranch(final String branchName) {
        parent.getBranchController().deleteLocalBranch(branchName);
    }

    @Override
    public void deleteRemoteBranch(final String remoteName, final String branchName) {
        // TODO Auto-generated method stub

    }

    @Override
    public void editRemote(final String remoteName) {
        parent.getRemoteController().editRemote(remoteName);
    }

    @Override
    public void fetchRemote(final String remoteName) {
        parent.getRemoteController().fetchRemote(remoteName);
    }

    @Override
    public Pane getPane() {
        return pane;
    }

    @Override
    public void onContentUpdated() {
    }

    @Override
    public void onManualUpdated() {
        updateOverview();
    }

    @Override
    public void onRepositoryChanged() {
        /*
         * remoteColorManager = ApplicationUtils.getApplicationProperties().getRemoteColorManager();
         * remoteColors.clear();
         */

        Utils.updateView(() -> view.setRepositoryName(model.getRepositoryModel().getName()));
        updateOverview();
    }

    @Override
    public void onRepositoryUpdated() {
        updateOverview();
    }

    @Override
    public void removeRemote(final String remoteName) {
        parent.getRemoteController().removeRemote(remoteName);
    }

    @Override
    public void renameRemoteBranch(final String remoteName, final String branchName) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setLocalBranchEnabled(final String branchName, final boolean enabled) {
        Log.getLogger().info(branchName + " enabled: " + enabled);
    }

    @Override
    public void setRemoteBranchEnabled(final String remoteName, final String branchName, final boolean enabled) {
        Log.getLogger().info("remotes/" + remoteName + "/" + branchName + " enabled: " + enabled);
    }

    @Override
    public void setTagEnabled(final String tagName, final boolean enabled) {
        Log.getLogger().info(tagName + " enabled: " + enabled);
    }

    @Override
    public void setUpstream(final String branchName) {
        parent.getRemoteController().setUpstream(branchName);
    }

    private void displayCheckoutResult(final CommandStatus status, final String newBranch) {
        if (status == CommandStatus.SUCCESS) {
            final String messageTitle = Utils.resolveString(ResourceType.MESSAGES, "overview.checkout.done.title");
            String messageDescription = Utils.resolveString(ResourceType.MESSAGES,
                    "overview.checkout.done.description");
            messageDescription = Utils.replacePattern(messageDescription, "oldbranch", currentBranch);
            messageDescription = Utils.replacePattern(messageDescription, "newbranch", newBranch);
            parent.showInfoNotification(messageTitle, messageDescription);
        } else {
            parent.showErrorNotification(Utils.resolveString(ResourceType.MESSAGES, "overview.checkout.error.title"),
                    Utils.resolveString(ResourceType.ERRORS, model.getBranchModel().getError().getErrorCode()));
        }
    }

    private void updateOverview() {
        final Map<String, Pair<String, String>> remotes = model.getRemoteModel().getOperations().getRemotes();
        final SortedSet<String> localBranches = new TreeSet<>(model.getBranchModel().getLocalBranches());
        final Map<String, Pair<Integer, Integer>> aheadBehindStatus = model.getRemoteModel().getOperations()
                .getAheadBehindStatus();
        final Map<String, Collection<String>> remoteBranches = model.getBranchModel().getRemoteBranches();
        final List<String> tags = model.getTagModel().getTags();
        currentBranch = model.getBranchModel().getCheckedOutBranch();

        remoteColors.entrySet().stream().filter(e -> !remotes.containsKey(e.getKey()))
                .forEach(e -> remoteColorManager.releaseColor(e.getValue()));

        remotes.keySet().stream().filter(s -> !remoteColors.containsKey(s))
                .forEach(e -> remoteColors.put(e, remoteColorManager.requestColor()));

        Utils.updateView(() -> {
            view.resetBranches();

            localBranches.forEach(s -> view.addLocalBranch(RepositoryUtils.formatLocalBranchRef(s, true)));
            remoteBranches.entrySet().forEach(e -> e.getValue()
                    .forEach(b -> view.addRemoteBranch(e.getKey(), RepositoryUtils.formatRemoteBranchRef(b, true))));

            if (localBranches.contains(currentBranch)) {
                view.setCheckedOutBranch(currentBranch);
            }

            aheadBehindStatus.entrySet().forEach(e -> view.setUpdates(e.getKey(), e.getValue()));

            view.resetRemotes();
            new TreeSet<>(remotes.keySet()).forEach(s -> view.addRemote(s, remotes.get(s), remoteColors.get(s)));

            view.resetTags();
            tags.forEach(s -> view.addTag(RepositoryUtils.formatTagRef(s, true)));
        });
    }

}
