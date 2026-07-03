package org.gitgud.model;

import java.util.Optional;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.transport.CredentialsProvider;

/**
 * Contains the git instance of the active repository.
 */
public interface RepositoryContainer {

    /**
     * @return the current CredentialsProvider, if present
     */
    Optional<CredentialsProvider> getCredentialsProvider();

    /**
     * @return the git instance of the current repository
     */
    Optional<Git> getRepository();

}
