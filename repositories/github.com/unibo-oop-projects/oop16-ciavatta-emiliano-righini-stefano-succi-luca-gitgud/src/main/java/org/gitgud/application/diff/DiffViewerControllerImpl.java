package org.gitgud.application.diff;

import java.io.File;

import javax.imageio.ImageIO;

import org.gitgud.application.WorkingAreaController;
import org.gitgud.exceptions.GitGudUnckeckedException;
import org.gitgud.model.diff.BinaryType;
import org.gitgud.model.diff.DiffBinaryManager;
import org.gitgud.model.diff.DiffManager;
import org.gitgud.model.diff.FileDifference;
import org.gitgud.model.utils.ChangeType;
import org.gitgud.utils.Utils;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

/**
 * The implementation of the diff viewer controller.
 */
public class DiffViewerControllerImpl implements DiffViewerController {

    private final WorkingAreaController parent;
    private DiffViewerView view;
    private Pane pane;

    private DiffManager manager;
    private String filePath;
    private boolean ignoreWhiteSpace;
    private boolean ignoreFirstLineChar;

    private int linesAdded;
    private int linesRemoved;

    /**
     * @param parent
     *            - the parent controller
     */
    public DiffViewerControllerImpl(final WorkingAreaController parent) {
        this.parent = parent;

        final FXMLLoader loader = new FXMLLoader();
        loader.setResources(Utils.getLabelsBundle());
        try {
            pane = loader.load(getClass().getResourceAsStream("DiffViewer.fxml"));
            view = loader.getController();
            view.attachController(this);
        } catch (final Exception e) {
            throw new GitGudUnckeckedException("Loading failed: DiffViewer.fxml");
        }
    }

    @Override
    public void copyToClipboard(final String text) {
        parent.copyToClipboard(text);
    }

    @Override
    public Pane getPane() {
        return pane;
    }

    @Override
    public void prepareManager(final DiffManager manager, final String filePath) {
        this.manager = manager;
        this.filePath = filePath;
    }

    @Override
    public void saveImageToDisk(final Image image, final String initialName) {
        final String initialFileName = initialName.substring(initialName.lastIndexOf('/') + 1);
        final String[] parts = initialFileName.toLowerCase(Utils.getLocale()).split("\\."); // length >= 2
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName(initialFileName);
        final File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), parts[parts.length - 1], file);
                parent.showSuccessNotification("Image saved", "The image was successfully saved in the selected path");
            } catch (final Exception e) {
                throw new GitGudUnckeckedException("Can't save image");
            }
        }
    }

    @Override
    public void setIgnoreFirstLineChar(final boolean ignoreFirstLineChar) {
        this.ignoreFirstLineChar = ignoreFirstLineChar;
    }

    @Override
    public void setIgnoreWhiteSpace(final boolean ignoreWhiteSpace) {
        this.ignoreWhiteSpace = ignoreWhiteSpace;
    }

    @Override
    public void updateDifferences() {
        final FileDifference fileDifference = manager.computeFileDifference(filePath, ignoreWhiteSpace);
        linesAdded = 0;
        linesRemoved = 0;

        Utils.updateView(() -> {
            if (fileDifference.getChangeType() == ChangeType.DELETED) {
                view.setFileName(fileDifference.getOldFile());
            } else {
                view.setFileName(fileDifference.getNewFile());
            }

            view.setChangeType(fileDifference.getChangeType());
            if (fileDifference.getSimilarityScore().isPresent()) {
                view.setAdditionalsInfo("(" + fileDifference.getSimilarityScore().get() + "% similar)");
            } else {
                view.setAdditionalsInfo("");
            }

            view.clearDifferences();
            fileDifference.getHunks().forEach(this::displayHunk);

            view.setLines("+ " + linesAdded, "- " + linesRemoved);
        });

        if (fileDifference.getNewFileDiffBinaryManager().isPresent()) {
            final DiffBinaryManager binaryManager = fileDifference.getNewFileDiffBinaryManager().get();
            if (binaryManager.getBinaryType() == BinaryType.IMAGE) {
                final Image image = binaryManager.createImage();
                Utils.updateView(() -> view.addNewBinaryImage(image));
                binaryManager.close();
            }
        }

        if (fileDifference.getOldFileDiffBinaryManager().isPresent()) {
            final DiffBinaryManager binaryManager = fileDifference.getOldFileDiffBinaryManager().get();
            if (binaryManager.getBinaryType() == BinaryType.IMAGE) {
                final Image image = binaryManager.createImage();
                Utils.updateView(() -> view.addOldBinaryImage(image));
                binaryManager.close();
            }
        }
    }

    private void displayHunk(final FileDifference.Hunk hunk) {
        view.openHunk(hunk.getHeader().isPresent() ? hunk.getHeader().get() : "");

        int fromFileLine = hunk.getFromFileRangeStartLine();
        int toFileLine = hunk.getToFileRangeStartLine();

        for (final String line : hunk.getLines()) {
            if (line.startsWith("+")) {
                view.addAddedLine(formatLine(line), String.valueOf(toFileLine));
                toFileLine++;
                linesAdded++;
            } else if (line.startsWith("-")) {
                view.addRemovedLine(formatLine(line), String.valueOf(fromFileLine));
                fromFileLine++;
                linesRemoved++;
            } else {
                view.addUnchangedLine(formatLine(line), String.valueOf(fromFileLine), String.valueOf(toFileLine));
                fromFileLine++;
                toFileLine++;
            }
        }

        view.closeHunk();
    }

    private String formatLine(final String line) {
        if (ignoreFirstLineChar) {
            return line.substring(1);
        } else {
            return line;
        }
    }

}
