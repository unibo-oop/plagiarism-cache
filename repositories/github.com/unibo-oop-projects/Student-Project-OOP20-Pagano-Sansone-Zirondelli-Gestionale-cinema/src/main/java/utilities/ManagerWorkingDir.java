package utilities;

import java.io.File;
import java.io.IOException;

public interface ManagerWorkingDir {
    /**
        Generates a new filename based on LENGTHFILENAME and file format ext.
        @param ext This is the file format used to create new file.@param ext
        @return generated file name 
     */
    String generateNewImageFileName(String ext);
    /**
        Copy source file to destination folder with the same name.
        @param src File to copy
        @param folder Path where src will be copied
        @return  path of destFile
        @throws IOException IOException
     */
    String copyFile(File src, String folder) throws IOException; 
    /**
        Copy source file to destination folder with specific name.
        @param src File to copy
        @param folder Path where src will be copied
        @param name name using during copying operation
        @return  path of destFile
        @throws IOException IOException
     */
    String copyFileWithSpecificName(File src, String folder, String name) throws IOException;

    /**
        Initialize working directory where application will run .
        @param workingDir working directory where application will work
     */
    void initWorkingDir(String workingDir);
    /**
        Check if input name is valid and available for specific path directory.
        @param dirPath path directory where check will be done
        @param name name to be checked
        @return true if it's a valid name , false otherwise
     */
    boolean isValidName(String dirPath, String name);
    /**
        Deletes specific file name.
        @param toDelete file to delete
     */
    void deleteFileWithSpecificName(File toDelete);

    /**
     * Fill working dir if it's demo application.
     * @param workingDir
     */
    void fillWorkingDir();

}
