package org.gitgud.application.diff;

import org.gitgud.application.node.Panel;
import org.gitgud.model.diff.DiffManager;

import javafx.scene.image.Image;

/**
 * The diff viewer controller.
 */
public interface DiffViewerController extends Panel {

    /**
     * Copy a text to the clipboard.
     *
     * @param text
     *            the text to be copied
     */
    void copyToClipboard(String text);

    /**
     * Prepare the viewer to display the differences.
     *
     * @param manager
     *            the diff manager
     * @param filePath
     *            the file to display
     */
    void prepareManager(DiffManager manager, String filePath);

    /**
     * Save an image to the file system.
     *
     * @param image
     *            the image to be saved
     * @param initialName
     *            the initial name of the image
     */
    void saveImageToDisk(Image image, String initialName);

    /**
     * @param ignoreFirstLineChar
     *            true to remote the first line char ('+', '-', ' ')
     */
    void setIgnoreFirstLineChar(boolean ignoreFirstLineChar);

    /**
     * @param ignoreWhiteSpace
     *            true to ignore white space in diff computation
     */
    void setIgnoreWhiteSpace(boolean ignoreWhiteSpace);

    /**
     * Request a diff update.
     */
    void updateDifferences();

}
