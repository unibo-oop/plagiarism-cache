package org.gitgud.model.remote;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.jgit.api.FetchCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.lib.BranchConfig;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.transport.FetchResult;
import org.eclipse.jgit.transport.PushResult;
import org.eclipse.jgit.transport.RemoteConfig;
import org.gitgud.exceptions.GitGudUnckeckedException;
import org.gitgud.model.Model;
import org.gitgud.model.RepositoryContainer;
import org.gitgud.model.merge.MergeParamBuilder;
import org.gitgud.model.remote.fetch.FetchOutput;
import org.gitgud.model.remote.fetch.FetchParam;
import org.gitgud.model.remote.fetch.FetchParamBuilder;
import org.gitgud.model.remote.fetch.FetchTask;
import org.gitgud.model.remote.pull.PullParam;
import org.gitgud.model.remote.push.PushParam;
import org.gitgud.model.remote.push.PushTask;
import org.gitgud.model.utils.CommandStatus;
import org.gitgud.model.utils.ProgressMonitorAdapter;
import org.gitgud.model.utils.RepositoryUtils;
import org.gitgud.utils.Log;

/**
 *
 */
public class RemoteActionImpl implements RemoteAction {

    private final Model model;
    private final RepositoryContainer repo;
    private Optional<RemoteError> error = Optional.empty();

    private List<FetchOutput> fetchOutput = Collections.emptyList();

    /**
     * @param model
     *            the application model
     * @param repo
     *            the repository container instance
     */
    public RemoteActionImpl(final Model model, final RepositoryContainer repo) {
        this.model = model;
        this.repo = repo;
    }

    @Override
    public CommandStatus fetch(final FetchParam param) {
        Objects.requireNonNull(param);
        final Git git = RepositoryUtils.getRepo(repo);
        final FetchCommand fetchCmd = git.fetch();

        final String remoteName;
        if (param.getRemoteName().isPresent()) {
            remoteName = param.getRemoteName().get();
        } else {
            remoteName = Constants.DEFAULT_REMOTE_NAME;
        }

        final RemoteConfig remoteConfig = getRemoteConfig(remoteName);

        fetchCmd.setRemote(remoteName).setCheckFetchedObjects(param.checkValidity()).setDryRun(param.isDryRun());

        if (repo.getCredentialsProvider().isPresent()) {
            fetchCmd.setCredentialsProvider(repo.getCredentialsProvider().get());
        }

        fetchCmd.setRefSpecs(remoteConfig.getFetchRefSpecs());
        fetchCmd.setTimeout(remoteConfig.getTimeout());
        fetchCmd.setTagOpt(remoteConfig.getTagOpt());

        if (param.getProgressListener().isPresent()) {
            fetchCmd.setProgressMonitor(
                    new ProgressMonitorAdapter(param.getProgressListener().get(), FetchTask.UNKNOWN_TASK));
        }

        FetchResult fetchRes = null;
        try {
            fetchRes = fetchCmd.call();
        } catch (final Exception e) {
            error = Optional.of(RemoteError.getByJgitMessage(e.getMessage()));
            return CommandStatus.ERROR;
        }

        if (fetchRes.getTrackingRefUpdates().isEmpty()) {
            return CommandStatus.SUCCESS_NO_UPDATES;
        } else {
            fetchOutput = fetchRes.getTrackingRefUpdates().stream().map(tru -> (FetchOutput) new FetchOutput() {

                @Override
                public String getLocalName() {
                    return tru.getLocalName();
                }

                @Override
                public String getNewCommitHash() {
                    return tru.getNewObjectId().name();
                }

                @Override
                public String getOldCommitHash() {
                    return tru.getOldObjectId().name();
                }

                @Override
                public String getRemoteName() {
                    return tru.getRemoteName();
                }
            }).collect(Collectors.toList());
            clearError();
            return CommandStatus.SUCCESS_WITH_UPDATES;
        }
    }

    @Override
    public RemoteError getError() {
        if (!error.isPresent()) {
            throw new IllegalStateException();
        }

        return error.get();
    }

    @Override
    public List<FetchOutput> getFetchOutput() {
        return fetchOutput;
    }

    @Override
    public CommandStatus pull(final PullParam params) {
        Objects.requireNonNull(params);

        final FetchParamBuilder fetchParamBuilder = FetchParamBuilder.createFetchParamBuilder();
        final BranchConfig config = getBranchConfig(
                RepositoryUtils.formatLocalBranchRef(model.getBranchModel().getCheckedOutBranch(), true));

        fetchParamBuilder.remoteName(config.getRemote()).isDryRun(false).checkValidity(true);

        if (params.getProgressListener().isPresent()) {
            fetchParamBuilder.progressListener(params.getProgressListener().get());
        }

        final CommandStatus fetchStatus = fetch(fetchParamBuilder.build());
        if (fetchStatus == CommandStatus.ERROR) {
            return CommandStatus.ERROR;
        }

        final MergeParamBuilder mergeParamBuilder = MergeParamBuilder.createMergeParamBuilder();
        mergeParamBuilder.fastForwardOnly(params.isFastForwardOnly());
        mergeParamBuilder.mergeAlways(params.isMergeAlways());

        if (params.getProgressListener().isPresent()) {
            mergeParamBuilder.progressListener(params.getProgressListener().get());
        }

        if (params.getCommitMessage().isPresent()) {
            mergeParamBuilder.message(params.getCommitMessage().get());
        }

        mergeParamBuilder.addRef("FETCH_HEAD");

        final CommandStatus mergeStatus = model.getMergeModel().merge(mergeParamBuilder.build());
        if (mergeStatus == CommandStatus.ERROR) {
            return CommandStatus.ERROR;
        }

        clearError();
        return CommandStatus.SUCCESS;
    }

    @Override
    public CommandStatus push(final PushParam params) {
        Objects.requireNonNull(params);

        final BranchConfig branchConfig = getBranchConfig(
                RepositoryUtils.formatLocalBranchRef(model.getBranchModel().getCheckedOutBranch(), true));

        if (branchConfig.getRemote() == null || branchConfig.getMerge() == null) {
            error = Optional.of(RemoteError.NO_UPSTREAM_SET);
            return CommandStatus.ERROR;
        }

        final RemoteConfig remoteConfig = getRemoteConfig(branchConfig.getRemote());
        final PushCommand pushCmd = RepositoryUtils.getRepo(repo).push();

        if (repo.getCredentialsProvider().isPresent()) {
            pushCmd.setCredentialsProvider(repo.getCredentialsProvider().get());
        }

        pushCmd.setRefSpecs(remoteConfig.getFetchRefSpecs()).setTimeout(remoteConfig.getTimeout()).setAtomic(false)
                .setThin(false).setReceivePack(remoteConfig.getReceivePack())
                .setDryRun(params.isDryRun()).setForce(params.isForce());

        if (params.isPushAll()) {
            pushCmd.setPushAll();
        }

        if (params.isPushTags()) {
            pushCmd.setPushTags();
        }

        if (params.getProgressListener().isPresent()) {
            pushCmd.setProgressMonitor(
                    new ProgressMonitorAdapter(params.getProgressListener().get(), PushTask.UNKNOWN_TASK));
        }

        pushCmd.add(branchConfig.getMerge());

        Iterable<PushResult> pushRes = null;
        try {
            pushRes = pushCmd.call();
            model.getRepositoryModel().sendManualRepositoryUpdate();
        } catch (final Exception e) {
            error = Optional.of(RemoteError.getByJgitMessage(e.getMessage()));
            return CommandStatus.ERROR;
        }

        for (final PushResult result : pushRes) {
            Log.getLogger().info(result.getTrackingRefUpdates().toString());
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
            throw new GitGudUnckeckedException("Failed to obtain BranchConfig: " + e.getMessage());
        }
    }

    private RemoteConfig getRemoteConfig(final String remoteName) {
        final Repository repository = RepositoryUtils.getRepo(repo).getRepository();

        try {
            return new RemoteConfig(repository.getConfig(), remoteName);
        } catch (final Exception e) {
            throw new GitGudUnckeckedException("Failed to obtain RemoteConfig: " + e.getMessage());
        }
    }

}
