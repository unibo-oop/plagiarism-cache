package utilities;

import java.io.IOException;

/**
 * UserPreferences interface.
 *
 */
public interface UserPreferences {

    /**
     * Loads the given property from the file.
     * 
     * @param key
     *            property to load
     * @return a string that contains the value of the given property
     * @throws IOException
     *             if the reading fails
     */
    String loadProperty(String key) throws IOException;

    /**
     * Saves the given property and the given value on the file.
     * 
     * @param key
     *            property to save
     * @param value
     *            to save
     * @throws IOException
     *             if the writing fails
     */
    void saveProperty(String key, String value) throws IOException;
}
