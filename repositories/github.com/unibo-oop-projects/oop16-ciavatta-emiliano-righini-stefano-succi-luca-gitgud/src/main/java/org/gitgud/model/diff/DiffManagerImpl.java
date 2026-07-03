package org.gitgud.model.diff;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.diff.RawTextComparator;
import org.eclipse.jgit.dircache.DirCacheIterator;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.ProgressMonitor;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.patch.FileHeader;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.AbstractTreeIterator;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;
import org.eclipse.jgit.treewalk.EmptyTreeIterator;
import org.eclipse.jgit.treewalk.FileTreeIterator;
import org.gitgud.exceptions.GitGudUnckeckedException;
import org.gitgud.model.RepositoryContainer;
import org.gitgud.model.diff.FileDifference.PatchType;
import org.gitgud.model.utils.ChangeType;
import org.gitgud.model.utils.RepositoryUtils;
import org.gitgud.utils.Pair;

/**
 *
 */
public class DiffManagerImpl implements DiffManager {

    private final RepositoryContainer repo;
    private final DiffStreamOutput diffStream = new DiffStreamOutput();
    private final DiffFormatter diffFormatter = new DiffFormatter(diffStream);

    private AbstractTreeIterator newTreeIterator;
    private AbstractTreeIterator oldTreeIterator;

    private boolean alreadyCalled;
    private boolean alreadyClosed;
    private List<DiffEntry> diffList;

    /**
     * @param repo
     *            the repository container
     */
    public DiffManagerImpl(final RepositoryContainer repo) {
        this.repo = repo;
    }

    @Override
    public void close() {
        if (alreadyClosed) {
            throw new IllegalStateException("Already closed");
        }

        alreadyClosed = true;
        diffFormatter.close();
    }

    @Override
    public FileDifference computeFileDifference(final String filePath, final boolean ignoreWhiteSpace) {
        final Optional<DiffEntry> diffEntry = diffList.stream()
                .filter(de -> de.getChangeType() == DiffEntry.ChangeType.DELETE && de.getOldPath().equals(filePath)
                        || de.getChangeType() != DiffEntry.ChangeType.DELETE && de.getNewPath().equals(filePath))
                .findAny();

        if (!diffEntry.isPresent()) {
            throw new IllegalArgumentException("The file specified is not included in the output of diff command");
        }

        if (ignoreWhiteSpace) {
            diffFormatter.setDiffComparator(RawTextComparator.WS_IGNORE_ALL);
        } else {
            diffFormatter.setDiffComparator(RawTextComparator.DEFAULT);
        }

        FileDifferenceImpl fileDifference = null;
        try {
            final FileHeader fileHeader = diffFormatter.toFileHeader(diffEntry.get());
            diffFormatter.format(diffEntry.get());
            fileDifference = diffStream.buildFileDifference(fileHeader);
            if (fileDifference.getPatchType() == PatchType.BINARY) {
                if (fileDifference.getChangeType() != ChangeType.DELETED) {
                    fileDifference.setNewFileDiffBinaryManager(
                            new DiffBinaryManagerImpl(RepositoryUtils.getRepo(repo).getRepository(),
                                    fileDifference.getPostImageShortHash(), filePath));
                }
                if (fileDifference.getChangeType() != ChangeType.ADDED) {
                    fileDifference
                            .setOldFileDiffBinaryManager(
                                    new DiffBinaryManagerImpl(RepositoryUtils.getRepo(repo).getRepository(),
                                            fileDifference.getPreImageShortHash(), filePath));
                }
            }
        } catch (final Exception e) {
            throw new GitGudUnckeckedException("Can't compute differences for file " + filePath);
        }

        return fileDifference;
    }

    @Override
    public List<Pair<String, ChangeType>> getChanges() {
        if (diffList == null) {
            throw new IllegalStateException("Can't obtain differences");
        }

        return diffList.stream().map(diffEntry -> {
            switch (diffEntry.getChangeType()) {
                case ADD:
                    return new Pair<>(diffEntry.getNewPath(), ChangeType.ADDED);
                case COPY:
                    return new Pair<>(diffEntry.getNewPath(), ChangeType.COPIED);
                case DELETE:
                    return new Pair<>(diffEntry.getOldPath(), ChangeType.DELETED);
                case MODIFY:
                    return new Pair<>(diffEntry.getNewPath(), ChangeType.MODIFIED);
                case RENAME:
                    return new Pair<>(diffEntry.getNewPath(), ChangeType.RENAMED);
                default:
                    return new Pair<>("", ChangeType.ADDED);
            }
        }).collect(Collectors.toList());
    }

    void computeDifferences() { // NOPMD: package private
        if (alreadyCalled) {
            throw new IllegalStateException("findDifferences must be called once");
        } else if (newTreeIterator == null || oldTreeIterator == null) {
            throw new IllegalStateException("Set a diff comparison mode before call findDifferences");
        }

        alreadyCalled = true;
        diffFormatter.setRepository(RepositoryUtils.getRepo(repo).getRepository());
        diffFormatter.setDiffComparator(RawTextComparator.DEFAULT);
        diffFormatter.setDetectRenames(true);

        try {
            diffList = diffFormatter.scan(oldTreeIterator, newTreeIterator);
        } catch (final Exception e) {
            throw new GitGudUnckeckedException("Can't scan for differences");
        }
    }

    void setDiffCommits(final String oldCommitRevStr, final String newCommitRevStr) { // NOPMD: package private
        if (oldCommitRevStr.isEmpty()) {
            oldTreeIterator = new EmptyTreeIterator();
        } else {
            oldTreeIterator = getCommitTreeIterator(oldCommitRevStr);
        }

        newTreeIterator = getCommitTreeIterator(newCommitRevStr);
    }

    void setDiffStaged() { // NOPMD: package private
        oldTreeIterator = getCommitTreeIterator(Constants.HEAD);
        newTreeIterator = getStagedFileIterator();
    }

    void setDiffUnstaged() { // NOPMD: package private
        oldTreeIterator = getStagedFileIterator();
        newTreeIterator = getUnstagedFileIterator();
    }

    void setProgressMonitor(final ProgressMonitor progressMonitor) { // NOPMD: package private
        diffFormatter.setProgressMonitor(progressMonitor);
    }

    private AbstractTreeIterator getCommitTreeIterator(final String revstr) {
        final Repository git = RepositoryUtils.getRepo(repo).getRepository();
        AbstractTreeIterator treeIterator = null;

        try (RevWalk walk = new RevWalk(git)) {
            final RevCommit commit = walk.parseCommit(git.resolve(revstr));
            final ObjectId treeId = commit.getTree().getId();

            final ObjectReader reader = git.newObjectReader();
            treeIterator = new CanonicalTreeParser(null, reader, treeId);
            reader.close();
        } catch (final Exception e) {
            throw new GitGudUnckeckedException("Can't create a tree iterator for specified commit");
        }

        return treeIterator;
    }

    private AbstractTreeIterator getStagedFileIterator() {
        AbstractTreeIterator treeIterator = null;

        try {
            treeIterator = new DirCacheIterator(RepositoryUtils.getRepo(repo).getRepository().readDirCache());
        } catch (final Exception e) {
            throw new GitGudUnckeckedException("Can't create a tree iterator for tracked files");
        }

        return treeIterator;
    }

    private AbstractTreeIterator getUnstagedFileIterator() {
        return new FileTreeIterator(RepositoryUtils.getRepo(repo).getRepository());
    }

}
