package clashclass.saveload;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Interface for writing data to files.
 */
@FunctionalInterface
public interface FileWriter {
    /**
     * Writes the provided data to a file.
     *
     * @param data the data to write
     * @param filePath the path where to write the file
     * @throws IOException if there's an error writing the file
     */
    void writeToFile(String data, Path filePath) throws IOException;
}

