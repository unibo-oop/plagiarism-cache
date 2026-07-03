package org.gitgud.model.services;

import org.gitgud.events.repository.RepositoryUpdatedListener;

/**
 *
 */
public interface Watcher {

    /**
     * @param listener
     *            the listener that should be advised when a new change is made.
     */
    void addRepositoryUpdatedListener(RepositoryUpdatedListener listener);

    /**
     * Start the watching task.
     */
    void startWatching();

    /**
     * Stop the watching task.
     */
    void stopWatching();

}
