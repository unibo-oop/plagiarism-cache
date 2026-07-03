package org.gitgud.model.remote.fetch;

import java.util.Optional;

import org.gitgud.events.ProgressListener;

/**
 * An immutable object to pass as argument to a fetch action.
 */
public interface FetchParam {

    /**
     * @return true if the objects received will be checked for validity
     */
    boolean checkValidity();

    /**
     * @return the progress listener associated with the fetch operation
     */
    Optional<ProgressListener> getProgressListener();

    /**
     * @return the remote name
     */
    Optional<String> getRemoteName();

    /**
     * @return true if the fetch operation should be a dry run
     */
    boolean isDryRun();

}
