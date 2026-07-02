package org.hsm.view.dialog;

import java.util.Optional;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * The Open file dialog with a file chooser.
 *
 */
public class OpenFileDialog implements FileDialog {

    private final JFileChooser fileChooser;
    private final JFrame frame;

    /**
     * Create the open file dialog.
     * 
     * @param frame
     *            the frame for the dialog
     * @param documentFormat
     *            the document format
     * @param documentExtension
     *            the document extension
     */
    public OpenFileDialog(final JFrame frame, final String documentFormat, final String documentExtension) {
        this.frame = frame;
        this.fileChooser = new JFileChooser();
        this.fileChooser.setAcceptAllFileFilterUsed(false);
        this.fileChooser.setFileFilter(new FileNameExtensionFilter(documentFormat, documentExtension));
    }

    @Override
    public Optional<String> getPath() {
        if (this.fileChooser.showOpenDialog(this.frame) == JFileChooser.APPROVE_OPTION) {
            return Optional.of(this.fileChooser.getSelectedFile().getAbsolutePath());
        } else {
            return Optional.empty();
        }
    }
}
