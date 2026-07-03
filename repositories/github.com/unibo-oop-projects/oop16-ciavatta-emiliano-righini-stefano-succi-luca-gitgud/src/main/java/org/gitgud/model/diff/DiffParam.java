package org.gitgud.model.diff;

import java.util.Optional;

/**
 * Represents all arguments to pass to the method that calculate the differences.
 */
public interface DiffParam {

    /**
     * @return the new commit hash. Must be present if isDiffCommits() is true
     */
    Optional<String> getNewCommitHash();

    /**
     * @return the old commit hash. Must be present if isDiffCommits() is true
     */
    Optional<String> getOldCommitHash();

    /**
     * @return true if the differences must be calculated between the two commit indicated
     */
    boolean isDiffCommits();

    /**
     * @return true if the differences must be calculated between the staged files and head
     */
    boolean isDiffStaged();

    /**
     * @return true if the differences must be calculated between the unstaged files and staged files
     */
    boolean isDiffUnstaged();

}
