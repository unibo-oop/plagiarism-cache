package org.gitgud.model.stage;

import java.util.Optional;
import java.util.Set;

/**
 * contains the information to do a clean operation.
 */
public interface CleanParam {

    /**
     * 
     * @return
     *          if present, the paths to clean
     */
    Optional<Set<String>> getPaths();

    /**
     * 
     * @return
     *          if true, in addition to files, also clean directories
     */
    boolean isDirs();

    /**
     * 
     * @return
     *          if true, directories that are git repositories will also be deleted
     */
    boolean isForce();

    /**
     * 
     * @return
     *          if true, don't report/clean files/directories that are ignored by a .gitignore
     */
    boolean isIgnore();
}
