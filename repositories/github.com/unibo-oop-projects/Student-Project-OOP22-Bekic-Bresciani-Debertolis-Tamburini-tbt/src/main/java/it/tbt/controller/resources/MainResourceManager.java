package it.tbt.controller.resources;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.tbt.controller.SimpleLogger;

/**
 * Manages resource files, combining those in the CalssPath and those in the user home.
 */
public class MainResourceManager extends ResourceFilesManagerImpl {
    private final String className = getClass().getName();
    private final Logger logger = SimpleLogger.getLogger(className);
    private final ResourceFilesManagerImpl resourceFilesManager = new ResourceFilesManagerImpl();
    private final SystemResourceManagerImpl classPathManager = new SystemResourceManagerImpl();

    /**
     * Get a BufferedInputStream to read from the file.
     * If the file does not exists in the user config directory, it will be
     * read from the classpath and written in the user config directory
     * @param filePath resource file path relative to the config directory
     * @return BufferedInputStream
     * @throws FileNotFoundException
     */
    @Override
    public BufferedInputStream getResourceInputStream(final String filePath) throws FileNotFoundException {
        if (!resourceFilesManager.fileExistsInPath(filePath)) {
            try (BufferedInputStream inClassPathStream = classPathManager.getResourceInputStream(filePath)) {
                try {
                    resourceFilesManager.makeDirInPath(filePath);
                    resourceFilesManager.writeResource(filePath, inClassPathStream);
                } catch (IOException e) {
                    logger.log(Level.SEVERE, "Cannot write to " + filePath, e);
                    // the position of the InputStream cannot be known, return a new stream
                    return classPathManager.getResourceInputStream(filePath);
                }
            } catch (FileNotFoundException e) { // NOPMD
                // the catch of IOException will catch FileNotFoundException
                // but the 2 exception have to be handled in different ways
                throw e;
            } catch (IOException e) {
                // Catch only error on closing stream, log and ignore
                logger.throwing(className, "getResourceInputStream", e);
            }
        }
        return resourceFilesManager.getResourceInputStream(filePath);
    }

    /**
     * Get the required resource file as a BufferedOutputStream.
     * @param filePath resource file path relative to the config directory
     * @return a BufferedOutputStream
     * @throws FileNotFoundException
     */
    @Override
    public BufferedOutputStream getResourceOutputStream(final String filePath) throws FileNotFoundException {
        return resourceFilesManager.getResourceOutputStream(filePath);
    }

    /**
     * Write the given string to the file in the user config path.
     * @param filePath resource file path relative to the config directory
     * @param content string that has to be written
     * @throws IOException
     */
    @Override
    public void writeResource(final String filePath, final byte[] content) throws IOException {
        resourceFilesManager.writeResource(filePath, content);
    }
}
