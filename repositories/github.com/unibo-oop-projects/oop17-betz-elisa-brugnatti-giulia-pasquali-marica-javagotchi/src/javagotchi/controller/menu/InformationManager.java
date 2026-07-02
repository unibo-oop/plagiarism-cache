package javagotchi.controller.menu;

import java.util.List;


/**
 * This interface models a manager to save a list of objects on a file in the home directory of the user.
 *  It waits the user choice in order to decide if it must look for an existing file, or create a new one.
 *  If the user choose to resume the file, but it doesn't exist, or it is corrupted it creates a new file.
 */
public interface InformationManager {
    /**
     * Writes the list passed as argument to user home directory.
     * @param list to write on the file
     */
    void writeOnFile(List<?> list);
     /**
     * This method simply returns the list saved on the file.
     * @return the list saved on the file
     * */
    List<?> resumeFile();
     /**
     * This method create a new file, if it not exist, or override it, if it exists.
     * */
    void writeNewInfoFile();
    }
