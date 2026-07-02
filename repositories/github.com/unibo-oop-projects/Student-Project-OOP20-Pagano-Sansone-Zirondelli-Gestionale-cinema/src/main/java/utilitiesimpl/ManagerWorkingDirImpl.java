package utilitiesimpl;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

import javax.swing.ImageIcon;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;

import utilities.ManagerWorkingDir;
/**
 * Manager to create and initialize working directory and to prepare application for running.
 */
public final class ManagerWorkingDirImpl implements ManagerWorkingDir {
    private static final int LENGTHFILENAME = 15; /**It specify length of filename to create. For example to create new image file. */

    /**
        Generates a new filename based on LENGTHFILENAME and file format ext.
        @param ext This is the file format used to create new file.
    */
    @Override
    public String generateNewImageFileName(final String ext) {
        return String.format("%s.%s", RandomStringUtils.randomAlphanumeric(LENGTHFILENAME), ext);
    }

    /**
        Copy source file to destination folder with the same name.
        @param src File to copy
        @param folder Path where src will be copied
    */
    @Override
    public String copyFile(final File src, final String folder) throws IOException {
        final String extSelectedFile = FilenameUtils.getExtension(src.getAbsolutePath());
        String generatedName = this.generateNewImageFileName(extSelectedFile);
        while (!(this.isValidName(GeneralSettings.IMAGESDIR, generatedName))) {
            generatedName = this.generateNewImageFileName(extSelectedFile);
        }
        final File destFile = new File(folder + generatedName);
        FileUtils.copyFile(src, destFile);
        return destFile.getAbsolutePath();
    }

    /**
        Check if input name is valid and available for specific path directory.
        @param dirPath path directory where check will be done
        @param name name to be checked
     */
    public boolean isValidName(final String dirPath, final String name) {
        if (dirPath != null && name != null) {
            final File[] files = new File(dirPath).listFiles();
            if (files != null) {
                return !(Arrays.asList(files).stream().anyMatch(f -> f.getName().equals(name))); 
            }
        }
        return false;
    }

    /**
        Copy source file to destination folder with specific name.
        @param src File to copy
        @param folder Path where src will be copied
        @param name name using during copying operation
     */
    @Override
    public String copyFileWithSpecificName(final File src, final String folder, final String name) throws IOException {
        final String extSelectedFile = FilenameUtils.getExtension(src.getAbsolutePath());
        final File destFile = new File(folder + name + extSelectedFile);
        FileUtils.copyFile(src, destFile);
        return destFile.getAbsolutePath();
    }

    /**
        Initialize working directory where application will run .
     */
    @Override // TODO
    public void initWorkingDir(final String workingDir) {
            try {
                GeneralSettings.setStartPath(workingDir);
                this.createDIR(GeneralSettings.getWorkingDIR());
                this.createDIR(GeneralSettings.DATADIR);
                this.createDIR(GeneralSettings.IMAGESDIR);
                this.createDIR(GeneralSettings.IMAGESSELECTEDDIR);
                this.initData();
            } catch (Exception e) { // TODO must be handled
                e.printStackTrace();
            }
    }

    /**
        Creates directory with specific name.
        @throws Exception 
     */
    private void createDIR(final String pathWithDIRname) {
        final File rootDIR = new File(pathWithDIRname);
        try {
            rootDIR.mkdir();
        } catch (final SecurityException se) {
            se.printStackTrace();
        }
    }

    /**
       {@inheritDoc}
     */
    @Override
    public void deleteFileWithSpecificName(final File toDelete) {
        try {
            FileUtils.forceDelete(toDelete);
        } catch (IOException e) {
            System.out.println("Errore while deleting specified file");
            e.printStackTrace();
        }
    }

    private void initData() {
        final URL url = ClassLoader.getSystemResource(GeneralSettings.ACCOUNTSTANDARD);
        final File file = new File(GeneralSettings.ACCOUNT_FILE_PATH);
        try {
            if (!file.exists()) {
                FileUtils.copyURLToFile(url, file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void fillWorkingDir() {
        try {
             URL url = ClassLoader.getSystemResource(GeneralSettings.ACCOUNTSTANDARD);
             FileUtils.copyURLToFile(url, new File(GeneralSettings.ACCOUNT_FILE_PATH));
             url = ClassLoader.getSystemResource(GeneralSettings.FILMSSTANDARD);
             FileUtils.copyURLToFile(url, new File(GeneralSettings.FILMSPATH));
             url = ClassLoader.getSystemResource(GeneralSettings.PROGRAMMEDFILMSTANDARD);
             FileUtils.copyURLToFile(url, new File(GeneralSettings.PROGRAMMEDFILMSPATH));
             url = ClassLoader.getSystemResource(GeneralSettings.MANAGERIDSFILMSSTANDARD);
             FileUtils.copyURLToFile(url, new File(GeneralSettings.MANAGERIDSFILMSPATH));
             url = ClassLoader.getSystemResource(GeneralSettings.TICKETSSTANDARD);
             FileUtils.copyURLToFile(url, new File(GeneralSettings.TICKET_FILE_PATH));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
