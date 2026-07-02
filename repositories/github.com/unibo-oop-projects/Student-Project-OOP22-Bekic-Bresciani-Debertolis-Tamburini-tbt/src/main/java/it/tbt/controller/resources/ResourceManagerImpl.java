package it.tbt.controller.resources;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Incomplete implementation of ResourceManager.
 */
abstract class ResourceManagerImpl implements ResourceManager {

    /**
     * Get a BufferedInputStream to read from the file.
     * If the file does not exists in the user home directory, it will be
     * read from the classpath and written in the user home directory
     * @param filePath resource file path relative to the config directory
     * @return BufferedInputStream
     * @throws FileNotFoundException
     */
    abstract BufferedInputStream getResourceInputStream(String filePath) throws FileNotFoundException, IOException;

    /**
     * Read the required resource.
     * @param filePath resource file path relative to the config directory
     * @return the bytes read, empty if error encountered
     */
    @Override
    public byte[] readResource(final String filePath) throws IOException {
        final BufferedInputStream reader = getResourceInputStream(filePath);
        final byte[] ret = reader.readAllBytes();
        reader.close();
        return ret;
    }
}
