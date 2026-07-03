package org.gitgud.model.diff;

import javafx.scene.image.Image;

/**
 * Manage the binary stream of a file of a git tree.
 */
public interface DiffBinaryManager {

    /**
     * Close the internal resources of DiffBinaryManager.
     */
    void close();

    /**
     * Creates the image from the binary stream. Must be called only once and only when BinaryType is equals to
     * BinaryType.IMAGE
     *
     * @return the new image created
     */
    Image createImage();

    /**
     * @return the binary type of the file that DiffBinaryManager represents
     */
    BinaryType getBinaryType();

}
