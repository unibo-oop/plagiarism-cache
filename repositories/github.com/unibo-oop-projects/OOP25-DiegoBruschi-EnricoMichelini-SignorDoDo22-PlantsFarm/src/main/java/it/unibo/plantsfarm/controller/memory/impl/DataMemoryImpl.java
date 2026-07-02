package it.unibo.plantsfarm.controller.memory.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import it.unibo.plantsfarm.controller.memory.api.DataMemory;

/**
 * A system to save and load data from files.
 */
public class DataMemoryImpl implements DataMemory {

    private static final String FOLDER_NAME = ".plantsfarm";

    /**
     * Gets the file by the file name.
     * Creates the main folder if it doesn't exist.
     *
     * @param fileName      The name of the file.
     * @return              The file object.
     * @throws IOException  If the directory can't be created.
     */
    private File getFile(final String fileName) throws IOException {
        final String userHome = System.getProperty("user.home");
        final File folder = new File(userHome, FOLDER_NAME);

        if (!folder.exists() && !folder.mkdirs()) {
            throw new IOException("Couldn't create directory: " + folder.getAbsolutePath());
        }

        return new File(folder, fileName);
    }

    /**
     * Saves data to a specific file.
     *
     * @param fileName      The name of the file.
     * @param data          The object to save.
     * @throws IOException  If the writing operation fails.
     */
    @Override
    public void save(final String fileName, final Object data) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(getFile(fileName).toPath(), StandardCharsets.UTF_8)) {
            writer.write(data.toString());
        }
    }

    /**
     * Loads data from a specific file.
     *
     * @param fileName      The name of the file.
     * @return              The content, or null if file doesn't exist.
     * @throws IOException  If the reading operation fails.
     */
    @Override
    public String load(final String fileName) throws IOException {
        final File file = getFile(fileName);

        if (!file.exists()) {
            return null;
        }

        try (BufferedReader reader = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8)) {
            return reader.readLine();
        }
    }
}
