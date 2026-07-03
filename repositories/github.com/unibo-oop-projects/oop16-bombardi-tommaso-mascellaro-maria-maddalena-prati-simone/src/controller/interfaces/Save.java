package controller.interfaces;

import java.io.File;
import java.util.Optional;

/**
 * Interface for save utilities.
 */
public interface Save {

    /**
     * Create new file if it doesn't exist.
     * 
     * @param f
     *              file that will be create
     * @return the created file
     * @throws IllegalStateException
     *              if an error occurs during the creation of the file
     */
    File createFile(File f) throws IllegalStateException;
    /**
     * Create new directory if it doesn't exist.
     * 
     * @param d
     *              directory that will be create
     * @return the created directory
     * @throws IllegalStateException
     *              if an error occurs during the creation of the directory
     */
    File createDir(File d) throws IllegalStateException;
    /**
     * Write object on file.
     * 
     * @param obj
     *              object that will be written on file
     * @param f
     *              file where the object will be written 
     * @return an optional
     *              empty if an error occurs while writing object on file
     *              with the written file if there is no error
     */
    Optional<File> writeFile(Object obj, File f);
    /**
     * Read object from file.
     * 
     * @param f
     *              file that will be read
     * @return an optional
     *              empty if an error occurs while reading from file
     *              with the read object if there is no error
     */
    Optional<Object> readFile(File f);

}
