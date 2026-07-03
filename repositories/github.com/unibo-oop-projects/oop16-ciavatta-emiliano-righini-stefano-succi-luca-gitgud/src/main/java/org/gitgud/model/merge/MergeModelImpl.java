package org.gitgud.model.merge;

import java.util.Optional;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.MergeCommand;
import org.eclipse.jgit.api.MergeCommand.FastForwardMode;
import org.eclipse.jgit.api.MergeResult;
import org.eclipse.jgit.merge.MergeStrategy;
import org.gitgud.model.RepositoryContainer;
import org.gitgud.model.utils.CommandStatus;
import org.gitgud.model.utils.ProgressMonitorAdapter;
import org.gitgud.model.utils.RepositoryUtils;
import org.gitgud.utils.Log;

/**
 *
 */
public class MergeModelImpl implements MergeModel {

    private final RepositoryContainer repo;
    private Optional<MergeError> error = Optional.empty();

    /**
     * @param repo
     *            the repository container instance
     */
    public MergeModelImpl(final RepositoryContainer repo) {
        this.repo = repo;
    }

    @Override
    public MergeError getError() {
        if (!error.isPresent()) {
            throw new IllegalStateException();
        }

        return error.get();
    }

    @Override
    public CommandStatus merge(final MergeParam param) {
        final Git git = RepositoryUtils.getRepo(repo);
        final MergeCommand mergeCmd = git.merge();

        if (param.isMergeAlways()) {
            mergeCmd.setFastForward(FastForwardMode.NO_FF);
        } else if (param.isFastForwardOnly()) {
            mergeCmd.setFastForward(FastForwardMode.FF_ONLY);
        } else {
            mergeCmd.setFastForward(FastForwardMode.FF);
        }

        if (param.getProgressListener().isPresent()) {
            mergeCmd.setProgressMonitor(
                    new ProgressMonitorAdapter(param.getProgressListener().get(), MergeTask.UNKNOWN_TASK));
        }

        if (param.getMessage().isPresent()) {
            mergeCmd.setCommit(true);
            mergeCmd.setMessage(param.getMessage().get());
        } else {
            mergeCmd.setCommit(false);
        }

        mergeCmd.setSquash(false);
        mergeCmd.setStrategy(MergeStrategy.RECURSIVE);

        MergeResult mergeRes = null;
        try {
            for (final String ref : param.getRefs()) {
                mergeCmd.include(git.getRepository().resolve(ref));
            }
            mergeRes = mergeCmd.call();
        } catch (final Exception e) {
            error = Optional.of(MergeError.getByJgitMessage(e.getMessage()));
            return CommandStatus.ERROR;
        }

        /* START DEBUG: */
        if (mergeRes.getCheckoutConflicts() != null) {
            Log.getLogger().info("Merge checkout conflicts: " + mergeRes.getCheckoutConflicts());
        }
        if (mergeRes.getConflicts() != null) {
            Log.getLogger().info("Merge checkout: " + mergeRes.getConflicts().keySet());
        }
        if (mergeRes.getFailingPaths() != null) {
            Log.getLogger().info("Merge failing path: " + mergeRes.getFailingPaths());
        }
        Log.getLogger().info("Merge status: " + mergeRes.getMergeStatus());
        /* STOP DEBUG: */

        clearError();
        return CommandStatus.SUCCESS;
    }

    private void clearError() {
        error = Optional.empty();
    }

}
