package org.gitgud.model.diff;

import java.util.List;

import org.gitgud.model.utils.ChangeType;
import org.gitgud.utils.Pair;

/**
 * Manage the differences between two stages.
 */
public interface DiffManager {

    /**
     * Close the DiffManager resources.
     */
    void close();

    /**
     * Compute the differences of a file between two stages.
     *
     * @param filePath
     *            - the file path of the file to calculate differences
     * @param ignoreWhiteSpace
     *            - true if white spaces must be ignored in the differences calculation
     * @return the FileDifference
     */
    FileDifference computeFileDifference(String filePath, boolean ignoreWhiteSpace);

    /**
     * @return the list of all changes
     */
    List<Pair<String, ChangeType>> getChanges();

}
