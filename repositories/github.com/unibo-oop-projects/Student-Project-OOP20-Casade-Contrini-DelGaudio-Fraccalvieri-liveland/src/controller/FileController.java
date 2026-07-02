package controller;

import java.io.File;
import java.io.IOException;

public interface FileController {

    /**
     * Saves the textual analysis on a chosen file.
     * @throws IOException
     *             if the writing fails
     */
    void save() throws IOException;

    /**
     * Sets the file chosen by user.
     * @param file
     *            the file where to write the analysis
     */
    void setDestination(File file);

}
