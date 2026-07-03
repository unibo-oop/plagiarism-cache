package org.gitgud.model.stage;

import java.util.Optional;
import java.util.Set;

import org.gitgud.model.utils.ChangeType;
import org.gitgud.model.utils.CommandStatus;
import org.gitgud.utils.Pair;

/**
 * Staging Area model interface.
 */
public interface StageModel {

    /**
     * Perform an add operation.
     *
     * @param paths
     *            the list of the files to add
     * @return the status of the operation
     */
    CommandStatus add(Set<String> paths);

    /**
     * Remove files from the index and the file system.
     *
     * @param paths
     *            the list of the files to remove
     * @param cached
     *            if true files should only be removed from index, if false files should also be deleted from the
     *            working
     * @return the status of the operation
     */
    CommandStatus remove(Set<String> paths, boolean cached);

    /**
     * Perform a commit operation.
     *
     * @param cp
     *            contains the information for do the commit
     * @return the status of the operation
     */
    CommandStatus commit(CommitParam cp);

    /**
     * Perform a clean operation: remove untracked files from the working tree.
     *
     * @param cp
     *            contains the information for do the clean operation
     * @return the status of the operation
     */
    CommandStatus clean(CleanParam cp);

    /**
     * Perform a reset operation: reset current HEAD to the specified state.
     *
     * @param rp
     *            contains the information for do the clean operation
     * @return the status of the operation
     */
    CommandStatus reset(ResetParam rp);

    /**
     * @return a set of pair with the name and the status of the untracked files
     */
    Set<Pair<String, ChangeType>> getStatusUntracked();

    /**
     * @return a set of pair with the name and the status of the not staged files
     */
    Set<Pair<String, ChangeType>> getStatusNotStaged();

    /**
     * @return a set of pair with the name and the status of the staged files
     */
    Set<Pair<String, ChangeType>> getStatusStaged();

    /**
     * @return if present, the error
     */
    Optional<StageError> getError();

    /**
     * @return if present, the warning
     */
    Optional<StageError> getWarning();
}
