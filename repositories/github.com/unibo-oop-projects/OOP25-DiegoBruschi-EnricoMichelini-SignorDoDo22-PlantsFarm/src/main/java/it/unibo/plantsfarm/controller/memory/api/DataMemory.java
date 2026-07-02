package it.unibo.plantsfarm.controller.memory.api;

import java.io.IOException;

/**
 * A system to save and load data from files.
 */
public interface DataMemory {

    /**
     * Saves data to a specific file.
     *
     * @param fileName      The name of the file.
     * @param data          The object to save.
     * @throws IOException  If the writing operation fails.
     */
    void save(String fileName, Object data) throws IOException;

    /**
     * Loads data from a specific file.
     *
     * @param fileName      The name of the file.
     * @return              The content, or null if file doesn't exist.
     * @throws IOException  If the reading operation fails.
     */
    String load(String fileName) throws IOException;

}
