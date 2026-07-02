package utils;

import java.util.Set;

/**
 * FileManager read and write multiple object into a file. Objects must be of
 * the same type.
 * 
 * @param <T> the objects type
 */
public interface FileManager<T> {

    /**
     * Read file of T objects.
     * 
     * @param fileName - the file path and name.
     * @return a Set of T objects. The Set is empty in case of Exception.
     */
    Set<T> load(String fileName);

    /**
     * Write a Set of T objects on a file. If the file already exist, it will be
     * overwritten.
     * 
     * @param fileName - the file path and name.
     * @param set      - the Set of T object to be written.
     */
    void save(String fileName, Set<T> set);

}
