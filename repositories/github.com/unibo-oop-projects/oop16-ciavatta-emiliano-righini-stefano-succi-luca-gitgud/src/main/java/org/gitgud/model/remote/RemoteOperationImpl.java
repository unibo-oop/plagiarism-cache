package org.gitgud.model.remote;

import java.net.URISyntaxException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.RemoteAddCommand;
import org.eclipse.jgit.api.RemoteRemoveCommand;
import org.eclipse.jgit.api.RemoteSetUrlCommand;
import org.eclipse.jgit.lib.AnyObjectId;
import org.eclipse.jgit.lib.BranchConfig;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.StoredConfig;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.transport.URIish;
import org.gitgud.exceptions.GitGudUnckeckedException;
import org.gitgud.model.Model;
import org.gitgud.model.RepositoryContainer;
import org.gitgud.model.utils.CommandStatus;
import org.gitgud.model.utils.RepositoryUtils;
import org.gitgud.utils.Log;
import org.gitgud.utils.Pair;

/**
 *
 */
public class RemoteOperationImpl implements RemoteOperation {

    private final Model model;
    private final RepositoryContainer repo;
    private Optional<RemoteError> error = Optional.empty();

    /**
     * @param repo
     *            the repository container instance
     * @param model
     *            the application model
     */
    public RemoteOperationImpl(final Model model, final RepositoryContainer repo) {
        this.model = model;
        this.repo = repo;
    }

    @Override
    public CommandStatus addRemote(final String name, final String url) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(url);

        if (isNameEmpty(name)) {
            return CommandStatus.ERROR;
        }

        if (getRemotes().containsKey(name)) {
            error = Optional.of(RemoteError.NAME_ALREADY_EXISTS);
            return CommandStatus.ERROR;
        }

        URIish uri;
        try {
            uri = new URIish(url);
        } catch (final URISyntaxException e) {
            error = Optional.of(RemoteError.URI_PARSE_ERROR);
            return CommandStatus.ERROR;
        }

        try {
            final RemoteAddCommand addRemoteCmd = RepositoryUtils.getRepo(repo).remoteAdd();
            addRemoteCmd.setName(name);
            addRemoteCmd.setUri(uri);

            addRemoteCmd.call();
        } catch (final Exception e) {
            error = Optional.of(RemoteError.getByJgitMessage(e.getMessage()));
            return CommandStatus.ERROR;
        }

        clearError();
        return CommandStatus.SUCCESS;
    }

    @Override
    public CommandStatus editRemote(final String name, final String newUrl) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(newUrl);

        if (isNameEmpty(name)) {
            return CommandStatus.ERROR;
        }

        if (!getRemotes().containsKey(name)) {
            error = Optional.of(RemoteError.NAME_INEXISTENT);
            return CommandStatus.ERROR;
        }

        URIish uri;
        try {
            uri = new URIish(newUrl);
        } catch (final URISyntaxException e) {
            error = Optional.of(RemoteError.URI_PARSE_ERROR);
            return CommandStatus.ERROR;
        }

        try {
            final RemoteSetUrlCommand editRemoteCmd = RepositoryUtils.getRepo(repo).remoteSetUrl();
            editRemoteCmd.setName(name);
            editRemoteCmd.setUri(uri);
            editRemoteCmd.setPush(false);
            editRemoteCmd.call();

            editRemoteCmd.setPush(true);
            editRemoteCmd.call();
        } catch (final Exception e) {
            error = Optional.of(RemoteError.getByJgitMessage(e.getMessage()));
            return CommandStatus.ERROR;
        }

        clearError();
        return CommandStatus.SUCCESS;
    }

    @Override
    public Map<String, Pair<Integer, Integer>> getAheadBehindStatus() {
        final Git git = RepositoryUtils.getRepo(repo);
        final Map<String, Pair<Integer, Integer>> status = new HashMap<>();
        final Map<String, Pair<String, String>> remotes = getRemotes();

        for (final String branchName : model.getBranchModel().getLocalBranches()) {
            final BranchConfig branchConfig = getBranchConfig(branchName);

            if (branchConfig.getMerge() == null || branchConfig.getRemote() == null) {
                continue;
            }

            final String mergeName = RepositoryUtils.formatLocalBranchRef(branchConfig.getMerge(), true);
            final String remoteName = branchConfig.getRemote();
            final String remoteBranch = "refs/remotes/" + remoteName + "/" + mergeName;
            final Map<String, Collection<String>> remoteBranches = model.getBranchModel().getRemoteBranches();

            if (!remotes.containsKey(remoteName)) {
                continue;
            } else if (!remoteBranches.containsKey(remoteName)
                    || !remoteBranches.get(remoteName).contains(remoteBranch)) {
                continue;
            }

            try {
                final AnyObjectId startId = git.getRepository()
                        .resolve(RepositoryUtils.formatLocalBranchRef(branchName, false));
                final AnyObjectId endId = git.getRepository().resolve(remoteBranch);

                int aheadCount = 0;
                int behindCount = 0;
                final Iterator<RevCommit> aheadIterator = git.log().addRange(endId, startId).call().iterator();
                final Iterator<RevCommit> behindIterator = git.log().addRange(startId, endId).call().iterator();

                while (aheadIterator.hasNext()) {
                    aheadIterator.next();
                    aheadCount++;
                }

                while (behindIterator.hasNext()) {
                    behindIterator.next();
                    behindCount++;
                }

                if (aheadCount > 0 || behindCount > 0) {
                    status.put(branchName, new Pair<>(aheadCount, behindCount));
                }
            } catch (final Exception e) {
                Log.getLogger().warning("Can't check updates for branch " + branchName);
            }
        }

        return status;
    }

    @Override
    public RemoteError getError() {
        if (!error.isPresent()) {
            throw new IllegalStateException();
        }

        return error.get();
    }

    @Override
    public Map<String, Pair<String, String>> getRemotes() {
        final Git git = RepositoryUtils.getRepo(repo);
        try {
            return git.remoteList().call().stream().filter(config -> !config.getURIs().isEmpty())
                    .collect(Collectors.toMap(config -> config.getName(),
                            config -> new Pair<>(config.getURIs().get(0).toString(),
                                    config.getURIs().get(0).toString())));

        } catch (final Exception e) {
            throw new GitGudUnckeckedException("Can't retrieve remotes: " + e.getMessage());
        }
    }

    @Override
    public CommandStatus removeRemote(final String name) {
        Objects.requireNonNull(name);

        if (isNameEmpty(name)) {
            return CommandStatus.ERROR;
        }

        if (!getRemotes().containsKey(name)) {
            error = Optional.of(RemoteError.NAME_INEXISTENT);
            return CommandStatus.ERROR;
        }

        try {
            final RemoteRemoveCommand removeRemoteCmd = RepositoryUtils.getRepo(repo).remoteRemove();
            removeRemoteCmd.setName(name);
            removeRemoteCmd.call();
        } catch (final Exception e) {
            error = Optional.of(RemoteError.getByJgitMessage(e.getMessage()));
            return CommandStatus.ERROR;
        }

        clearError();
        return CommandStatus.SUCCESS;
    }

    @Override
    public CommandStatus setUpstream(final String branchName, final String remoteName, final String remoteBranch) {
        Objects.requireNonNull(branchName);
        Objects.requireNonNull(remoteName);
        Objects.requireNonNull(remoteBranch);

        final StoredConfig config = RepositoryUtils.getRepo(repo).getRepository().getConfig();
        config.setString("branch", branchName, "remote", remoteName);
        config.setString("branch", branchName, "merge",
                "refs/remotes/" + remoteName + "/" + RepositoryUtils.formatRemoteBranchRef(remoteBranch, true));

        try {
            config.save();
        } catch (final Exception e) {
            error = Optional.of(RemoteError.getByJgitMessage(e.getMessage()));
            return CommandStatus.ERROR;
        }

        clearError();
        return CommandStatus.SUCCESS;
    }

    private void clearError() {
        error = Optional.empty();
    }

    private BranchConfig getBranchConfig(final String branchName) {
        final Repository repository = RepositoryUtils.getRepo(repo).getRepository();

        try {
            return new BranchConfig(repository.getConfig(), branchName);
        } catch (final Exception e) {
            throw new GitGudUnckeckedException("Failed to obtain RemoteConfig: " + e.getMessage());
        }
    }

    private boolean isNameEmpty(final String name) {
        if (name.isEmpty()) {
            error = Optional.of(RemoteError.NAME_EMPTY);
            return true;
        }

        return false;
    }

}
