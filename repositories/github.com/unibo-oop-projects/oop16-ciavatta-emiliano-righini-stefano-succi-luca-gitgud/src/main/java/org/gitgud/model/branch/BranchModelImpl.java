package org.gitgud.model.branch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.jgit.api.CreateBranchCommand.SetupUpstreamMode;
import org.eclipse.jgit.api.DeleteBranchCommand;
import org.eclipse.jgit.api.ListBranchCommand.ListMode;
import org.eclipse.jgit.api.errors.CannotDeleteCurrentBranchException;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NotMergedException;
import org.eclipse.jgit.api.errors.RefAlreadyExistsException;
import org.eclipse.jgit.lib.Ref;
import org.gitgud.model.Model;
import org.gitgud.model.RepositoryContainer;
import org.gitgud.model.utils.CommandStatus;
import org.gitgud.model.utils.RepositoryUtils;

/**
 *
 *
 *
 */
public class BranchModelImpl implements BranchModel {

    private final Model model;
    private final RepositoryContainer repo;
    private BranchError lastError = BranchError.NO_ERROR;

    /**
     * @param repo
     *            the repository container.
     * @param model
     *            the model, core of the application.
     */
    public BranchModelImpl(final Model model, final RepositoryContainer repo) {
        this.model = model;
        this.repo = repo;
    }

    @Override
    public CommandStatus checkoutToLocal(final String branch) {
        Objects.requireNonNull(branch);
        final String name = RepositoryUtils.formatLocalBranchRef(branch, true);
        if (!getLocalBranches().contains(name)) {
            lastError = BranchError.BRANCH_NOT_FOUND;
            return CommandStatus.ERROR;
        }
        try {
            RepositoryUtils.getRepo(repo).checkout().setName(name).call();
        } catch (final GitAPIException e) {
            lastError = BranchError.UNKNOWN_ERROR;
            return CommandStatus.ERROR;
        }
        return CommandStatus.SUCCESS;
    }

    @Override
    public CommandStatus checkoutToRemote(final String branch) {
        Objects.requireNonNull(branch);
        final String name = RepositoryUtils.formatRemoteBranchRef(branch, false);
        if (!getRemoteBranches().values().stream().flatMap(l -> l.stream()).anyMatch(n -> n.equals(name))) {
            lastError = BranchError.BRANCH_NOT_FOUND;
            return CommandStatus.ERROR;
        }
        final String newName = RepositoryUtils.formatRemoteBranchRef(branch, true);
        try {
            RepositoryUtils.getRepo(repo).checkout().setCreateBranch(true).setStartPoint(name)
                    .setUpstreamMode(SetupUpstreamMode.SET_UPSTREAM).setName(newName).call();
        } catch (final GitAPIException e) {
            lastError = BranchError.UNKNOWN_ERROR;
            lastError = BranchError.getByJgitMessage("already exists");
            return CommandStatus.ERROR;
        }
        return CommandStatus.SUCCESS;
    }

    @Override
    public CommandStatus createBranch(final String branch, final String startPoint) {
        Objects.requireNonNull(branch);
        Objects.requireNonNull(startPoint);
        final String name = RepositoryUtils.formatLocalBranchRef(branch, true);
        try {
            RepositoryUtils.getRepo(repo).branchCreate().setStartPoint(startPoint).setName(name).call();
            model.getRepositoryModel().sendManualRepositoryUpdate();
        } catch (final RefAlreadyExistsException e) {
            lastError = BranchError.BRANCH_NAME_IN_USE;
            return CommandStatus.ERROR;
        } catch (final GitAPIException e) {
            lastError = BranchError.UNKNOWN_ERROR;
            return CommandStatus.ERROR;
        }
        return CommandStatus.SUCCESS;
    }

    @Override
    public CommandStatus deleteBranch(final List<String> branchnames) {
        Objects.requireNonNull(branchnames);
        final DeleteBranchCommand db = RepositoryUtils.getRepo(repo).branchDelete()
                .setBranchNames(branchnames.toArray(new String[branchnames.size()]));
        try {
            db.call();
        } catch (final NotMergedException e) {
            lastError = BranchError.BRANCH_NOT_MERGED;
            return CommandStatus.ERROR;
        } catch (final CannotDeleteCurrentBranchException e) {
            lastError = BranchError.BRANCH_IN_USE;
            return CommandStatus.ERROR;
        } catch (final GitAPIException e) {
            lastError = BranchError.UNKNOWN_ERROR;
            return CommandStatus.ERROR;
        }
        return CommandStatus.SUCCESS;
    }

    @Override
    public String getCheckedOutBranch() {
        try {
            return RepositoryUtils.getRepo(repo).getRepository().getBranch();
        } catch (final IOException e) {
            return null;
        }
    }

    @Override
    public BranchError getError() {
        return lastError;
    }

    @Override
    public Set<String> getLocalBranches() {
        final List<Ref> reflist = new ArrayList<>();
        try {
            reflist.addAll(RepositoryUtils.getRepo(repo).branchList().call());
        } catch (final GitAPIException e) {
            lastError = BranchError.UNKNOWN_ERROR;
        }
        return Collections
                .unmodifiableSet(reflist.stream().map(r -> r.getLeaf())
                        .filter(r -> r.getName().contains("/") && !r.getName().contains("heads/HEAD"))
                        .map(r -> RepositoryUtils.formatLocalBranchRef(r.getName(), true))
                        .collect(Collectors.toSet()));
    }

    @Override
    public LogManager getLogManager() {
        return new LogManagerImpl(repo);
    }

    @Override
    public Map<String, Collection<String>> getRemoteBranches() {
        final List<Ref> reflist = new ArrayList<>();
        try {
            reflist.addAll(RepositoryUtils.getRepo(repo).branchList().setListMode(ListMode.REMOTE).call());
        } catch (final GitAPIException e) {
            lastError = BranchError.UNKNOWN_ERROR;
        }
        final Map<String, Collection<String>> remotes = new HashMap<>();
        reflist.stream().map(r -> r.getLeaf()).distinct().filter(r -> !r.getName().equals("HEAD")).map(r -> r.getName())
                .forEach(n -> {
                    final String[] tmp = n.split("/");
                    if (!remotes.containsKey(tmp[2])) {
                        remotes.put(tmp[2], new ArrayList<>());
                    }
                    remotes.get(tmp[2]).add(n);
                });
        return remotes;
    }

    @Override
    public CommandStatus renameBranch(final String branch, final String newName) {
        final String name = RepositoryUtils.formatLocalBranchRef(branch, true);
        if (!getLocalBranches().contains(name)) {
            lastError = BranchError.BRANCH_NOT_FOUND;
            return CommandStatus.ERROR;
        }
        if (getLocalBranches().contains(newName)) {
            lastError = BranchError.BRANCH_NAME_IN_USE;
            return CommandStatus.ERROR;
        }
        try {
            RepositoryUtils.getRepo(repo).branchRename().setOldName(name).setNewName(newName).call();
        } catch (final GitAPIException e) {
            lastError = BranchError.UNKNOWN_ERROR;
            return CommandStatus.ERROR;
        }
        return CommandStatus.SUCCESS;
    }
}
