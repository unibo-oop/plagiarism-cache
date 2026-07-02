package it.tbt.controller.resources;

import java.io.IOException;

/**
 * Manage resource files in the user config directory.
 */
interface ResourceFilesManager extends ResourceManager {

    /**
     * Write the given data to the resource file.
     * @param filePath resource file path relative to the config directory
     * @param content data that has to be written
     * @throws IOException
     */
    void writeResource(String filePath, byte[] content) throws IOException;
}
