package org.gitgud.application.stage;

import java.util.Set;

import org.gitgud.application.node.Panel;

/**
 *
 */
public interface StagingAreaController extends Panel {

    /**
     * Perform an add operation.
     * 
     * @param paths
     *          a set contains files to add to the staging area
     */
    void doAddOperation(Set<String> paths);

    /**
     * Perform a remove operation.
     * 
     * @param paths
     *          a set contains files to remove from the index and the file system
     * @param cached
     *          if true: remove from the index
     *          if false: remove from file system
     */
    void doRemoveOperation(Set<String> paths, boolean cached);

    /**
     * Perform a clean operation.
     * 
     * @param paths
     *          if set, only these paths are affected by the cleaning, if empty remove all untracked files
     */
    void doCleanOperation(Set<String> paths);

    /**
     * Perform a reset operation.
     * 
     * @param paths
     *          if set, only this paths will be reset
     */
    void doResetOperation(Set<String> paths);

    /**
     * update the staging area view.
     */
    void updateView();
}
