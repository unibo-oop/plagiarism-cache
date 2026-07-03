package org.gitgud.model.remote.push;

import java.util.Optional;

import org.gitgud.events.ProgressListener;

/**
 *
 */
public interface PushParam {

    /**
     * @return the progress listener
     */
    Optional<ProgressListener> getProgressListener();

    /**
     * @return true if dry run is requested
     */
    boolean isDryRun();

    /**
     * @return true if force is requested
     */
    boolean isForce();

    /**
     * @return true if push all is requested
     */
    boolean isPushAll();

    /**
     * @return true if push tags is requested
     */
    boolean isPushTags();

}
