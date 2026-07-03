package org.gitgud.model.stage;

import java.util.Optional;
import java.util.Set;

import org.eclipse.jgit.api.AddCommand;
import org.eclipse.jgit.api.CleanCommand;
import org.eclipse.jgit.api.CommitCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ResetCommand;
import org.eclipse.jgit.api.RmCommand;
import org.eclipse.jgit.api.errors.CheckoutConflictException;
import org.eclipse.jgit.api.errors.ConcurrentRefUpdateException;
import org.eclipse.jgit.api.errors.EmtpyCommitException;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.api.errors.WrongRepositoryStateException;
import org.eclipse.jgit.errors.NoWorkTreeException;
import org.gitgud.model.RepositoryContainer;
import org.gitgud.model.utils.ChangeType;
import org.gitgud.model.utils.CommandStatus;
import org.gitgud.model.utils.RepositoryUtils;
import org.gitgud.utils.Pair;

/**
 *
 */
public class StageModelImpl implements StageModel {

    private final RepositoryContainer repo;
    private final GitStatus gs;
    private Optional<StageError> error;
    private Optional<StageError> warning;

    /**
     * @param repo
     *            repository container
     */
    public StageModelImpl(final RepositoryContainer repo) {
        this.repo = repo;
        this.gs = new GitStatusImpl();
        this.emptyErrorWarning();
    }

    @Override
    public CommandStatus add(final Set<String> paths) {
        if (paths.isEmpty()) {
            this.warning = Optional.of(StageError.MISSING_PATHS);
            return CommandStatus.ERROR;
        }
        final AddCommand add = this.getGit().add();
        paths.stream().forEach((p) -> add.addFilepattern(p));
        try {
            add.call();
        } catch (final GitAPIException e) {
            this.error = Optional.of(StageError.GIT_API);
            return CommandStatus.ERROR;
        }
        this.emptyErrorWarning();
        return CommandStatus.SUCCESS;
    }

    @Override
    public CommandStatus clean(final CleanParam cp) {
        if (!cp.getPaths().isPresent()) {
            this.warning = Optional.of(StageError.MISSING_PATHS);
            return CommandStatus.ERROR;
        }
        final CleanCommand cc = this.getGit().clean();
        cc.setPaths(cp.getPaths().get());
        cc.setCleanDirectories(cp.isDirs()).setForce(cp.isForce()).setIgnore(cp.isIgnore());
        try {
            cc.call();
        } catch (final NoWorkTreeException e) {
            this.error = Optional.of(StageError.MISSING_WORK_TREE);
            return CommandStatus.ERROR;
        } catch (final GitAPIException e) {
            this.error = Optional.of(StageError.GIT_API);
            return CommandStatus.ERROR;
        }
        this.emptyErrorWarning();
        return CommandStatus.SUCCESS;
    }

    @Override
    public CommandStatus commit(final CommitParam cp) {
        if (cp.getMessage().isEmpty()) {
            this.warning = Optional.of(StageError.MISSING_MESSAGE);
            return CommandStatus.ERROR;
        }
        if (!cp.getAuthor().isPresent()) {
            this.warning = Optional.of(StageError.MISSING_AUTHOR);
            return CommandStatus.ERROR;
        }
        final CommitCommand cc = this.getGit().commit();
        cc.setMessage(cp.getMessage())
                .setAuthor(cp.getAuthor().get().getX(), cp.getAuthor().get().getY())
                .setCommitter(cp.getCommitter().get().getX(), cp.getCommitter().get().getY())
                .setAmend(cp.isAmend())
                .setAll(cp.isAll())
                .setAllowEmpty(false);
        try {
            cc.call();
        } catch (final NoHeadException e) {
            this.error = Optional.of(StageError.MISSING_HEAD);
            return CommandStatus.ERROR;
        } catch (final ConcurrentRefUpdateException e) {
            this.error = Optional.of(StageError.CONCURRENT_REF_UPDATE);
            return CommandStatus.ERROR;
        } catch (final WrongRepositoryStateException e) {
            this.error = Optional.of(StageError.WRONG_REPO);
            return CommandStatus.ERROR;
        } catch (final EmtpyCommitException e) {
            this.warning = Optional.of(StageError.EMPTY_COMMIT);
            return CommandStatus.ERROR;
        } catch (final GitAPIException e) {
            this.error = Optional.of(StageError.GIT_API);
            return CommandStatus.ERROR;
        }
        this.emptyErrorWarning();
        return CommandStatus.SUCCESS;
    }

    @Override
    public Optional<StageError> getError() {
        return this.error;
    }

    private Git getGit() {
        return RepositoryUtils.getRepo(this.repo);
    }

    @Override
    public Set<Pair<String, ChangeType>> getStatusNotStaged() {
        this.emptyErrorWarning();
        final Set<Pair<String, ChangeType>> files = this.gs.getStatusNotStaged(this.getGit());
        this.error = this.gs.getError();
        return files;
    }

    @Override
    public Set<Pair<String, ChangeType>> getStatusStaged() {
        this.emptyErrorWarning();
        final Set<Pair<String, ChangeType>> files = this.gs.getStatusStaged(this.getGit());
        this.error = this.gs.getError();
        return files;
    }

    @Override
    public Set<Pair<String, ChangeType>> getStatusUntracked() {
        this.emptyErrorWarning();
        final Set<Pair<String, ChangeType>> files = this.gs.getStatusUntracked(this.getGit());
        this.error = this.gs.getError();
        return files;
    }

    @Override
    public Optional<StageError> getWarning() {
        return this.warning;
    }

    @Override
    public CommandStatus remove(final Set<String> paths, final boolean cahced) {
        if (paths.isEmpty()) {
            this.warning = Optional.of(StageError.MISSING_PATHS);
            return CommandStatus.ERROR;
        }
        final RmCommand rm = this.getGit().rm();
        paths.forEach((f) -> rm.addFilepattern(f));
        rm.setCached(cahced);
        try {
            rm.call();
        } catch (final GitAPIException e) {
            this.error = Optional.of(StageError.GIT_API);
            return CommandStatus.ERROR;
        }
        this.emptyErrorWarning();
        return CommandStatus.SUCCESS;
    }

    @Override
    public CommandStatus reset(final ResetParam rp) {
        if (!rp.getPaths().isPresent()) {
            this.warning = Optional.of(StageError.MISSING_PATHS);
            return CommandStatus.ERROR;
        }
        final ResetCommand rc = this.getGit().reset();
        rp.getPaths().get().forEach(p -> rc.addPath(p));
        if (rp.getRef().isPresent()) {
            rc.setRef(rp.getRef().get());
        }
        if (rp.getMode().isPresent()) {
            rc.setMode(rp.getMode().get());
        }
        rc.disableRefLog(rp.isDisableRefLog());
        try {
            rc.call();
        } catch (final CheckoutConflictException e) {
            this.error = Optional.of(StageError.CHECKOUT_CONFLICT);
            return CommandStatus.ERROR;
        } catch (final GitAPIException e) {
            this.error = Optional.of(StageError.GIT_API);
            return CommandStatus.ERROR;
        }
        this.emptyErrorWarning();
        return CommandStatus.SUCCESS;
    }

    private void emptyErrorWarning() {
        this.error = Optional.empty();
        this.warning = Optional.empty();
    }
}
