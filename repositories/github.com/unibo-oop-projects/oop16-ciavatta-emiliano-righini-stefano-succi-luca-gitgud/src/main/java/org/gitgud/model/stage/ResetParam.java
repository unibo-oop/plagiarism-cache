package org.gitgud.model.stage;

import java.util.Optional;
import java.util.Set;

import org.eclipse.jgit.api.ResetCommand;

/**
 * contains the information to do a clean operation.
 */
public interface ResetParam {

    /**
     * 
     * @return
     *          if present, the paths to reset
     */
    Optional<Set<String>> getPaths();

    /**
     * 
     * @return
     *          the ref to reset to
     */
    Optional<String> getRef();

    /**
     * 
     * @return
     *          the mode of the reset command
     */
    Optional<ResetCommand.ResetType> getMode();

    /**
     * 
     * @return
     *          if true disables writing a reflog entry for this reset command
     */
    boolean isDisableRefLog();
}
