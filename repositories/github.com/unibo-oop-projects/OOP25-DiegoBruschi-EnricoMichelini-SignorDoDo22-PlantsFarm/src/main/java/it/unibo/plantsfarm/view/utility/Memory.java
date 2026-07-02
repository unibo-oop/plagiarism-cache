package it.unibo.plantsfarm.view.utility;

import java.io.IOException;

/**
 * Interface for handling memory operations such as saving and loading data.
 */
public interface Memory {

    /**
     * Saves the given data to a file with the specified name.
     *
     * @param fileName the name of the file to save the data to
     * @param data the data to be saved
     * @throws IOException if an I/O error occurs during saving
     */
    void save(String fileName, String data) throws IOException;

    /**
     * Loads data from a file with the specified name.
     *
     * @param fileName the name of the file to load the data from
     * @return the data loaded from the file
     * @throws IOException if an I/O error occurs during loading
     */
    String load(String fileName) throws IOException;
}
