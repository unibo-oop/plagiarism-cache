package org.hsm.view.dialog;

import java.io.File;
import java.util.Optional;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * The Save file dialog with a file chooser.
 *
 */
public class SaveFileDialog implements FileDialog {

    private final JFileChooser fileChooser;
    private final JFrame frame;
    private final String documentExtension;

    /**
     * Create the save file dialog.
     * 
     * @param frame
     *            the frame for the dialog
     * @param documentFormat
     *            the document format
     * @param documentExtension
     *            the document extension
     */
    public SaveFileDialog(final JFrame frame, final String documentFormat, final String documentExtension) {
        this.frame = frame;
        this.documentExtension = documentExtension;
        this.fileChooser = new JFileChooser();
        this.fileChooser.setAcceptAllFileFilterUsed(false);
        this.fileChooser.setFileFilter(new FileNameExtensionFilter(documentFormat, documentExtension));
        this.fileChooser.setSelectedFile(new File("New." + documentExtension));
    }

    @Override
    public Optional<String> getPath() {
        if (this.fileChooser.showSaveDialog(this.frame) == JFileChooser.APPROVE_OPTION) {
            String fileName = this.fileChooser.getSelectedFile().getAbsolutePath();
            if (!fileName.endsWith(this.documentExtension)) {
                fileName = new StringBuffer(fileName).append(this.documentExtension).toString();
            }
            return Optional.of(fileName);
        } else {
            return Optional.empty();
        }
    }

}
