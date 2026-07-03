package org.gitgud.model.repository;

import java.io.File;
import java.util.Optional;

import org.gitgud.events.ProgressListener;

/**
 * Contains all informations needed by a git clone operation.
 */
public interface CloneParam {

    /**
     * @return the directory
     */
    File getDirectory();

    /**
     * Return the password to access to the repository, if specified.
     *
     * @return the password to access to the repository, if specified
     */
    Optional<String> getPassword();

    /**
     * @return the progress listener
     */
    Optional<ProgressListener> getProgressListener();

    /**
     * Return the remote URI of the repository.
     *
     * @return the remote URI of the repository
     */
    String getRemote();

    /**
     * Return the username to access to the repository, if specified.
     *
     * @return the username to access to the repository, if specified.
     */
    Optional<String> getUsername();

}
