package it.unibo.coinquify.file;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.JOptionPane;

import it.unibo.coinquify.utils.Messages;
import it.unibo.coinquify.utils.PhoneNumberPresentException;

/**
 * Main file resurces.
 */
public final class MainFile {

    private MainFile() {
    }

    /**
     * Check resources and creates them.
     */
    public static void checkResources() {
        final boolean madeDir = createDirIfNotExist(FilePathsConstants.DIR_PATH);
        FilePathsConstants.getFiles().forEach(MainFile::createFileIfNotExist);
        if (madeDir) {
            try {
                Populates.all();
            } catch (IOException | ParseException | IllegalArgumentException | PhoneNumberPresentException e) {
                JOptionPane.showMessageDialog(null, Messages.getMessages().getString("ERROR"));
            }
        }
    }

    // returns true if it have to create new folder
    private static boolean createDirIfNotExist(final String path) {
        final File f = new File(path);
        if (!f.exists()) {
            f.mkdir();
            return true;
        }
        return false;
    }

    private static boolean createFileIfNotExist(final String path) {
        final File f = new File(path);
        if (!f.exists()) {
            try {
                return f.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return true;
        }
    }
}
