package it.tbt.controller.resources;

import java.io.IOException;

/**
 * Manages resource files.
 */
interface ResourceManager {

    /**
     * Read the required file.
     * @param filePath resource file path relative to the config directory
     * @return the bytes read
     * @throws IOException
     */
    byte[] readResource(String filePath) throws IOException;
}
