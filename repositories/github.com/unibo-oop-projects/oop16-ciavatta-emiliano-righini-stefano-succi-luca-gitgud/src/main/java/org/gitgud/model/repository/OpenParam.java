package org.gitgud.model.repository;

import java.io.File;

/**
 * Represents a generic repository.
 */
public interface OpenParam {

    /**
     * Return the path where the repository should operate.
     *
     * @return the path where the repository should operate
     */
    File getDirectory();

}
