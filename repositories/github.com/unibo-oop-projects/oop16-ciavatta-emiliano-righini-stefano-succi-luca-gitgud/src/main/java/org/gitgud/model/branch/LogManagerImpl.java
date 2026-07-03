package org.gitgud.model.branch;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.jgit.errors.AmbiguousObjectException;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.MissingObjectException;
import org.eclipse.jgit.errors.RevisionSyntaxException;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.PersonIdent;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.gitgud.exceptions.GitGudUnckeckedException;
import org.gitgud.model.RepositoryContainer;
import org.gitgud.model.commons.AuthorFactory;
import org.gitgud.model.commons.Commit;
import org.gitgud.model.commons.CommitFactory;
import org.gitgud.model.commons.CommitFactory.CommitConstructor;
import org.gitgud.model.utils.RepositoryUtils;

/**
 *
 *
 */
public class LogManagerImpl implements LogManager {

    /**
     * The max output of getLogs method.
     */
    public static final int STD_MAX_OUTPUT = 50;
    private static final String INC_OBJ_MSG = "The Object type was incorrect during string resolving";
    private static final String MISSING_MSG = "The Object expected was missing";
    private static final String IO_EXCEPT = "IO Exception occurred";
    private final RepositoryContainer repo;
    private final RevWalk commits;
    private int maxCommitOutput = STD_MAX_OUTPUT;

    LogManagerImpl(final RepositoryContainer repo) {
        this.repo = repo;
        commits = new RevWalk(RepositoryUtils.getRepo(repo).getRepository());
    }

    private Optional<RevCommit> getApiCommit(final String identifier) {
        try {
            return Optional.ofNullable(commits.parseCommit(resolveId(identifier)));
        } catch (final MissingObjectException e) {
            throw new GitGudUnckeckedException(MISSING_MSG);
        } catch (final IncorrectObjectTypeException e) {
            throw new GitGudUnckeckedException(INC_OBJ_MSG);
        } catch (final IOException e) {
            throw new GitGudUnckeckedException(IO_EXCEPT);
        }
    }

    @Override
    public Optional<Commit> getCommit(final String identifier) {
        return translateRev(getApiCommit(identifier));
    }

    @Override
    public Iterator<Commit> getLogs() {
        final List<Commit> logs = new LinkedList<>();
        try {
            Optional<Commit> oc;
            boolean stop = false;
            int i = 0;
            while (!stop && i <= maxCommitOutput) {
                oc = translateRev(Optional.ofNullable(commits.next()));
                if (oc.isPresent()) {
                    logs.add(oc.get());
                } else {
                    stop = true;
                }
                i++;
            }
        } catch (final MissingObjectException e) {
            throw new GitGudUnckeckedException(MISSING_MSG);
        } catch (final IncorrectObjectTypeException e) {
            throw new GitGudUnckeckedException(INC_OBJ_MSG);
        } catch (final IOException e) {
            throw new GitGudUnckeckedException(IO_EXCEPT);
        }
        return logs.iterator();
    }

    @Override
    public LogManager markStart(final String identifier) {
        try {
            commits.markStart(getApiCommit(identifier).get());
        } catch (final MissingObjectException e1) {
            throw new GitGudUnckeckedException(MISSING_MSG);
        } catch (final IncorrectObjectTypeException e1) {
            throw new GitGudUnckeckedException(INC_OBJ_MSG);
        } catch (final IOException e1) {
            throw new GitGudUnckeckedException(IO_EXCEPT);
        }
        return this;
    }

    private ObjectId resolveId(final String id) {
        Objects.requireNonNull(id);
        try {
            return RepositoryUtils.getRepo(repo).getRepository().resolve(id);
        } catch (final RevisionSyntaxException e) {
            throw new GitGudUnckeckedException("The string could not be resolved due to a syntax error");
        } catch (final AmbiguousObjectException e) {
            throw new GitGudUnckeckedException("The Object'id was ambigous");
        } catch (final IncorrectObjectTypeException e) {
            throw new GitGudUnckeckedException(INC_OBJ_MSG);
        } catch (final IOException e) {
            throw new GitGudUnckeckedException(IO_EXCEPT);
        }
    }

    @Override
    public LogManager setMaxCount(final int max) {
        maxCommitOutput = max;
        return this;
    }

    private Optional<Commit> translateRev(final Optional<RevCommit> commit) {
        if (!commit.isPresent()) {
            return Optional.empty();
        }
        final PersonIdent pi = commit.get().getAuthorIdent();
        final CommitConstructor cb = CommitFactory.createCommitBuilder();
        cb.addParents(Arrays.asList(commit.get().getParents())
                .stream()
                .map(c -> c.getName())
                .collect(Collectors.toList()))
                .setID(commit.get().getName())
                .setShortMessage(commit.get().getShortMessage())
                .setFullMessage(commit.get().getFullMessage())
                .setDate(pi.getWhen())
                .setAuthor(AuthorFactory.getAuthorProfiler()
                        .setName(pi.getName())
                        .setMail(pi.getEmailAddress())
                        .build());

        return Optional.ofNullable(cb.build());
    }

}
