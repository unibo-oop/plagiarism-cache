package org.gitgud.model.repository;

import java.io.File;
import java.util.Optional;

/**
 * Contains all informations needed by a git init operation.
 */
public interface InitParam {

    /**
     * @return the directory
     */
    File getDirectory();

    /**
     * Return the Gitignore template, if specified.
     *
     * @return the Gitignore template, if specified
     */
    Optional<Gitignore> getGitIgnore();

    /**
     * Return the GitLicense type, if specified.
     *
     * @return the GitLicense type, if specified
     */
    Optional<GitLicense> getLicense();

}
