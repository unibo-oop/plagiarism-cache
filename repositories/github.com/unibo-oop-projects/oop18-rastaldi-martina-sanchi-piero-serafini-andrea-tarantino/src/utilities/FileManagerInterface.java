package utilities;

import java.io.Serializable;

/**
 * Interface of a file manager.
 * Andrea Serafini.
 *
 * @param <T> the type of element to be written on the file
 */
public interface FileManagerInterface<T extends Serializable> {

    /**
     * Delete the file if present.
     */
    void deleteFile();

    /**
     *
     * @return the object written on the file
     */
    T get();

    /**
     *
     * @return true if the file is present
     */
    boolean isPresent();

    /**
     *
     */
    void loadFile();

    /**
     *
     * @param object
     *            the object to be saved
     */
    void saveFile(T object);

    /**
     * @param object
     *            the object to be saved in the previous existing file
     */
    void updateFile(T object);

}
