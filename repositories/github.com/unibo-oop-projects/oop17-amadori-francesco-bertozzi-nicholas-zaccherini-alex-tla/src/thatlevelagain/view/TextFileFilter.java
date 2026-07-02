package thatlevelagain.view;

import java.io.File;
import java.util.Locale;

import javax.swing.filechooser.FileFilter;

/**
 * 
 * this class accept only txt file for load state.
 *
 */
public class TextFileFilter extends FileFilter {

    /**
     * @param file
     *         file that will checked.
     * @return
     *         return if file end with txt
     */
    public boolean accept(final File file) {
        if (file.isDirectory()) {
            return true;
        }
        final String fname = file.getName().toLowerCase(Locale.ENGLISH);
        return fname.endsWith("txt");
    }
    /**
     * @return
     *         return description for file filter.
     */
    public String getDescription() {
        return "File di testo";
    }
}
