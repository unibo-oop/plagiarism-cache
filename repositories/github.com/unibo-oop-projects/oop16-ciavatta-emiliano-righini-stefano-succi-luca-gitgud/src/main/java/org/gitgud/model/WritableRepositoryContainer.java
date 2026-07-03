package org.gitgud.model;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.transport.CredentialsProvider;

/**
 * The writable container that contains the active git instance.
 */
public interface WritableRepositoryContainer extends RepositoryContainer {

    /**
     * Set the CredentialsProvider of the git repository.
     *
     * @param credentialsProvider
     *            the CredentialsProvider of the git repository
     */
    void setCredentialsProvider(CredentialsProvider credentialsProvider);

    /**
     * Set the git instance of the loaded repository.
     *
     * @param repository
     *            the git instance. Set null if no repository is opened
     */
    void setRepository(Git repository);

}
