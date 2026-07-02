package it.tbt.controller.resources;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Manage resource file in ClassPath.
 */
class SystemResourceManagerImpl extends ResourceManagerImpl {

    /**
     * Get a BufferedInputStream to read from the file.
     * If the file does not exists in the user home directory, it will be
     * read from the classpath and written in the user home directory
     * @param filePath resource file path relative to the root of the class path
     * @throws FileNotFoundException
     * @return BufferedInputStream
     */
    @Override
    protected BufferedInputStream getResourceInputStream(final String filePath) throws FileNotFoundException {
        final InputStream reader =
            getClass().getClassLoader().getResourceAsStream(
                filePath.replace("\\", "/")
            );
        if (reader == null) {
            throw new FileNotFoundException();
        } else {
            return new BufferedInputStream(reader);
        }
    }
}
