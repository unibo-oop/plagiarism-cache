package it.trashwarecesena.trustalodesktopclient.repository.adapter.concreteness;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import it.trashwarecesena.trustalodesktopclient.repository.adapter.FileConnectionStrategy;
import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;

/**
 * A concrete implementation for a {@link FileConnectionStrategy}.
 * 
 * @author Manuel Bonarrigo
 *
 */
public final class SimpleFileConnectionStrategy implements FileConnectionStrategy {

    private final String filepath;

    /**
     * Instantiate a ConcreteFileConnectionStrategy to the location specified.
     * 
     * @param filepath
     *            a {@link String} containing the filepath used to locate the file.
     */
    public SimpleFileConnectionStrategy(final String filepath) {
        Objects.requireNonNull(filepath, ErrorString.STRING_NULL);
        final File file = new File(filepath);
        if (!file.exists()) {
            throw new IllegalArgumentException("The directory " + filepath + " does not exists");
        } else if (!file.isDirectory()) {
            throw new IllegalArgumentException(filepath + " is not a directory, and that's not allowed");
        }
        this.filepath = filepath;
    }

    @Override
    public File createConnection() throws IOException {
        return new File(filepath);
    }

}
