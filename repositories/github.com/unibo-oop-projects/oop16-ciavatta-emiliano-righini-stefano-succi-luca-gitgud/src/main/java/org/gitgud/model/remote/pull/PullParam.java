package org.gitgud.model.remote.pull;

import java.util.Optional;

import org.gitgud.events.ProgressListener;

/**
 * An immutable object to pass as argument to a pull action.
 */
public interface PullParam {

    /**
     * @return the commit message to use for merge commit, if present
     */
    Optional<String> getCommitMessage();

    /**
     * @return the progress listener associated with the pull operation
     */
    Optional<ProgressListener> getProgressListener();

    /**
     * @return the remote branch which local branch points to
     */
    String getRemoteBranch();

    /**
     * @return the remote name
     */
    Optional<String> getRemoteName();

    /**
     * @return true if only fast forward merge is accepted
     */
    boolean isFastForwardOnly();

    /**
     * @return true if a merge commit should always been created, even if unnecessary
     */
    boolean isMergeAlways();

}
