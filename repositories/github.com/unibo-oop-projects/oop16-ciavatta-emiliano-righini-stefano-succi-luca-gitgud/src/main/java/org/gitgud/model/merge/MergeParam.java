package org.gitgud.model.merge;

import java.util.List;
import java.util.Optional;

import org.gitgud.events.ProgressListener;

/**
 *
 */
public interface MergeParam {

    /**
     * @return the merge message
     */
    Optional<String> getMessage();

    /**
     * @return the progress listener associated with the merge operation
     */
    Optional<ProgressListener> getProgressListener();

    /**
     * @return a list of all refs to merge
     */
    List<String> getRefs();

    /**
     * @return true if fast forward is requested
     */
    boolean isFastForwardOnly();

    /**
     * @return true if merge is requested
     */
    boolean isMergeAlways();

}
