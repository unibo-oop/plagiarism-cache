package org.gitgud.application.diff;

import java.util.List;

import org.gitgud.application.node.Panel;
import org.gitgud.model.commons.Commit;

/**
 * The diff overview controller.
 */
public interface DiffOverviewController extends Panel {

    /**
     * Calculate differences of the specified file.
     *
     * @param filePath
     *            the file path
     * @param id
     *            the id of the pane where the file is displayed
     */
    void computeFileDiff(String filePath, int id);

    /**
     * Show the differences between the first and the last specified commits.
     *
     * @param selectedCommits
     *            the list of the commits to show the differences
     */
    void showDiff(List<Commit> selectedCommits);

    /**
     * Display the differences between staged files and HEAD and unstaged files and staged files.
     */
    void showStagingDiff();

}
