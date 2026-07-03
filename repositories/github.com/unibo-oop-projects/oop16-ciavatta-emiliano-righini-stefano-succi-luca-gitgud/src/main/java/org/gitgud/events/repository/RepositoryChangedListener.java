package org.gitgud.events.repository;

/**
 * Classes implementing this interface receive updates when the repository changed.
 */
public interface RepositoryChangedListener {

    /**
     * Invoked when the repository changed.
     */
    void onRepositoryChanged();

}
