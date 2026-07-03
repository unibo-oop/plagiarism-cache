package org.gitgud.events.repository;

/**
 * Classes implementing this interface receive updates when the repository has updates.
 */
public interface RepositoryUpdatedListener {

    /**
     * Invoked when there are new content updates.
     */
    void onContentUpdated();

    /**
     * Invoked when there are new manual updates.
     */
    void onManualUpdated();

    /**
     * Invoked when there are new repository updates.
     */
    void onRepositoryUpdated();

}
