package com.biaren.utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.biaren.sportclubmanager.utility.enums.TextStrings;

/**
 * Class for some file writer operations.
 * @author nbrunetti
 *
 */
public class BiarenFileWriter {
    
    /**
     * Create first opening file for setup operation. System procedure.
     */
    public static void createFirstOpeningFile() {
        createFile(TextStrings.FIRST_OPENING_FILENAME.toString());
//        hiddenFile(BiarenPathHandler.getRootDirPath() + File.separator + TextStrings.FIRST_OPENING_FILENAME.toString());
    }
    
    /**
     * Create setup file fort setup operation. System procedure.
     */
    public static void createSetupFile() {
        createFile(TextStrings.DO_SETUP_FILENAME.toString());
//      hiddenFile(BiarenPathHandler.getRootDirPath() + File.separator + TextStrings.DO_SETUP_FILENAME.toString());
    }
    
    /**
     * Create new File from file name
     * @param fileName of file to create
     */
    public static void createFile(final String fileName) {
       BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(new File(BiarenPathHandler.getRootDirPath() + File.separator + fileName)));
            System.out.println(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Hide file from file path
     * @param path file path
     */
    public static void hiddenFile(final String path) {
        Path p = Paths.get(path);
        try {
            Files.setAttribute(p, "dos:hidden", true, LinkOption.NOFOLLOW_LINKS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
