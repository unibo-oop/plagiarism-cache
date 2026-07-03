package org.gitgud.model.stage;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.errors.NoWorkTreeException;
import org.gitgud.model.utils.ChangeType;
import org.gitgud.utils.Pair;

class GitStatusImpl implements GitStatus {

    private Optional<StageError> error;

    GitStatusImpl() {
        this.error = Optional.empty();
    }

    @Override
    public Optional<StageError> getError() {
        return this.error;
    }

    @Override
    public Set<Pair<String, ChangeType>> getStatusNotStaged(final Git git) {
        final Set<Pair<String, ChangeType>> unstaged = new HashSet<>();
        final Optional<Status> status = this.getStatus(git);
        if (status.isPresent() && !status.get().isClean()) {
            unstaged.addAll(this.putFileProperty(status.get().getModified(), ChangeType.MODIFIED));
            unstaged.addAll(this.putFileProperty(status.get().getMissing(), ChangeType.DELETED));
            return unstaged;
        }
        return Collections.emptySet();
    }

    @Override
    public Set<Pair<String, ChangeType>> getStatusStaged(final Git git) {
        final Set<Pair<String, ChangeType>> staged = new HashSet<>();
        final Optional<Status> status = this.getStatus(git);
        if (status.isPresent() && !status.get().isClean()) {
            staged.addAll(this.putFileProperty(status.get().getAdded(), ChangeType.ADDED));
            staged.addAll(this.putFileProperty(status.get().getChanged(), ChangeType.MODIFIED));
            staged.addAll(this.putFileProperty(status.get().getRemoved(), ChangeType.DELETED));
            return staged;
        }
        return Collections.emptySet();
    }

    @Override
    public Set<Pair<String, ChangeType>> getStatusUntracked(final Git git) {
        final Optional<Status> status = this.getStatus(git);
        if (status.isPresent() && !status.get().isClean()) {
            return this.putFileProperty(status.get().getUntracked(), ChangeType.ADDED);
        }
        return Collections.emptySet();
    }

    private Optional<Status> getStatus(final Git git) {
        try {
            this.error = Optional.empty();
            return Optional.of(git.status().call());
        } catch (final NoWorkTreeException e) {
            this.error = Optional.of(StageError.MISSING_WORK_TREE);
            return Optional.empty();
        } catch (final GitAPIException e) {
            this.error = Optional.of(StageError.GIT_API);
            return Optional.empty();
        }
    }

    private Set<Pair<String, ChangeType>> putFileProperty(final Set<String> files, final ChangeType ct) {
        return files.stream().map(f -> new Pair<>(f, ct)).collect(Collectors.toSet());
    }
}
