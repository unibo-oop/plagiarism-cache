package com.biaren.utility;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.scene.control.Alert.AlertType;

/**
 * Class for some file operations.
 * @author nbrunetti
 *
 */
public final class BiarenFileHandler {
   
    private BiarenFileHandler() {
        
    }
    
    /**
     * Get directory content.
     * @param path of the directory
     * @return an array of {@link String} with the content of specific directory
     */
    public static String[] getDirContent(final String path) {
        File f = new File(path);
        return f.list();
    }
    
    /**
     * Get only files in specific directory ends with specified extension.
     * @param path {@link String} of the directory
     * @param extension {@link String} of the extension of the file, without the '.' 
     * @return an array of {@link String} of all the files with specified extension
     */
    public static List<File> getFileListInDirFromExtension(final Path path, final String extension) {
        final List<File> fileList = new ArrayList<>();
        try (final DirectoryStream<Path> stream = Files.newDirectoryStream(path, "*." + extension)) {
            for (final Path entry : stream) {
              fileList.add(new File(entry.toString()));
           }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileList;
    }
   
    /**
     * Read a single line. 
     * If file is not yet opened, open it.
     * If the file is open and there are no more lines, close file.
     * @param read {@link BiarenReadFile} object
     * @throws IOException if problem with I/O occurs
     * @return String of a single line
     */
    public static String readLine(final BiarenReadFile read) throws IOException {
        if (!read.isOpen()) {
            read.openFile();
        }
        if (read.isOpen() && !Optional.ofNullable(read.readSingleLine()).isPresent()) {
            read.closeFile();
            return null;
        }
        return read.readSingleLine();
    }
    
    /**
     * Copy file to resource dir.
     * @param f file to copy
     * @param pathToSave path of new copied file
     * @param newName {@link String} for new name, empty string for not rename
     */
    public static void copyFileToResourceDir(final File f, final String pathToSave, final String newName) {
        Path newPath = Paths.get(pathToSave + "\\" + (newName.length() > 0 ? newName : f.getName()));
        Path original = f.toPath();
        try {
            Files.copy(original, newPath, StandardCopyOption.REPLACE_EXISTING);
            BiarenUtil.showBasicAlert(AlertType.CONFIRMATION, "Inserito con successo", "", "");
        } catch (IOException e1) {
            BiarenUtil.showBasicAlert(AlertType.ERROR, "Eccezione IO, impossibile salvare il file", "", "");
        }
    }
}
