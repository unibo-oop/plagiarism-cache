package controller;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import org.apache.commons.io.FilenameUtils;

import controller.exceptions.HistoryNotInitializedException;
import javafx.stage.FileChooser;
import model.effects.EffectsClassName;
import model.effects.VoidEffect;
import model.effects.convolution.EdgeDetection;
import model.util.format.Format;
import model.util.format.FormatManager;
import model.util.history.History;
import model.util.history.HistoryImpl;
import model.util.storage.HistoryManager;

/**
 * Controller for history and file system interaction. Allow to save and load
 * history and image handling eventually problem by catching it in Exception
 */
public final class IOHistoryHandler {

    // control variables
    private static final boolean SET_HISTORY_SAVED = true;
    private static final String LOADER_TITLE = "Open existing project or image";
    private static final String SAVER_TITLE = "Select where to save image or project (change in *.mshi extension to save project)";
    private static final String DEF_HISTORIES_PATH = FormatManager.getDefaultHistoriesPath();
    private static final String HISTORY_FORMAT = "*" + Format.mshi.toString();
    private static final String PNG = Format.png.toString().substring(2);
    private static final boolean CHOOSER_OPENED = true;
    private static final boolean CHOOSER_CLOSED = false;
    private static final boolean STOP_OPERATION = false;
    private static final boolean END_CORRECTLY = true;

    private static HistoryHandler historyHandler;
    private static File selectedFile;
    private static String ext;
    private static String absoluteFilePath;
    private static boolean chooserOpened;

    /*
     * Initialize all non default effect to add into EffectClassName Map
     */
    private IOHistoryHandler() {
        new ConvolutionHandler().initializePersonalizedEffect();
    }

    /**
     * show image/project loader.
     * 
     * @return true if history or image are correctly loaded and HistoryHandler
     *         instantiated, false if not (unselected file)
     */
    public static boolean loadImage() {
        if (chooserOpened) {
            return STOP_OPERATION;
        }
        selectedFile = new IOHistoryHandler().openLoadDialog();
        return addHistory(selectedFile);
    }

    /**
     * Add history by another existing one or by new image.
     * 
     * @param file File to load
     * @return true if load correctly
     */
    public static boolean addHistory(final File file) {

        if (file == null) {
            return STOP_OPERATION; // nothing setted
        }

        // initialize needed fields
        updateHandler();
        selectedFile = file;
        ext = "*." + FilenameUtils.getExtension(selectedFile.getName()).toLowerCase(Locale.getDefault());
        absoluteFilePath = FilenameUtils.removeExtension(selectedFile.getAbsolutePath()) + ext.substring(1);

        if (ext.equals(HISTORY_FORMAT)) {
            // MSHI FORMAT

            try {

                historyHandler.addHistory(new HistoryManager(absoluteFilePath).loadExistingHistory(),
                        secureMshiNameMaker(absoluteFilePath));

            } catch (IOException e) {
                System.out.println("Error encountered in history loading [loading aborted]" + e.getMessage());
                return STOP_OPERATION;
            }
        } else if (FormatManager.isAllowedMustashiFormat(ext)) {
            // IMAGE FORMAT

            try {

                historyHandler.addHistory(new HistoryManager(absoluteFilePath).loadNewImage(),
                        new HistoryManager(absoluteFilePath).getVoidEffect(), secureMshiNameMaker(absoluteFilePath));

            } catch (IOException e) {
                System.out.println("Error encountered in image loading, corrupted format "
                        + selectedFile.getAbsolutePath() + " [loading aborted]" + e.getMessage());
                return STOP_OPERATION;
            }

        } else {
            return STOP_OPERATION; // wrong format
        }
        return END_CORRECTLY;
    }

    /**
     * Save HistoryHandler's current history. If already set as saved, can force
     * saving if need to update history's index to reload history in updated index
     * position
     * 
     * @param forceSave if TRUE, force to saving file if is already set as saved to
     *                  update current index
     * @return true if save correctly
     */
    public static boolean saveActualHistoryStatus(final boolean forceSave) {

        // secure update of history handler, not work if not history exists
        try {
            if (!canSave() && !forceSave) {
                return STOP_OPERATION;
            }
        } catch (HistoryNotInitializedException e) {
            System.out.println(e.getMessage() + "[SAVE ABORTED]");
            return STOP_OPERATION;
        }

        final History tmpHistory = historyHandler.getCurrentHistory();
        final Integer tmpCurrentIndex = tmpHistory.getCurrentIndex();
        final Integer tmpSavedIndex = ((VoidEffect) tmpHistory.getOriginal().getEffect()).getSavedIndex();
        final boolean isModified = !tmpHistory.getLast().getEffect().getEffectName()
                .equals(new VoidEffect().getEffectName());
        ((HistoryImpl) tmpHistory).setIndex(tmpCurrentIndex);
        ext = FilenameUtils.getExtension(historyHandler.getCurrentNameOfHistory());

        // control history status
        if (isModified && !tmpCurrentIndex.equals(tmpSavedIndex)) {

            ((VoidEffect) tmpHistory.getOriginal().getEffect()).setSavedIndex(tmpCurrentIndex);
            new HistoryManager(tmpHistory.getNameHistory()).saveHistory(tmpHistory);

            ((HistoryImpl) tmpHistory).setIndex(tmpCurrentIndex);
            historyHandler.setSaveStatusCurrentHistory(SET_HISTORY_SAVED);
        }

        return END_CORRECTLY;
    }

    /**
     * Save HistoryHandler's current history. Is not linked to default actual
     * history path, can duplicate current history or current image in file system.
     * A secure control rename file selected to save, adding the first free index,
     * only if it was selected the same path of original file, and original file
     * doesn't been saved in it's default position yet.
     * 
     * @return true if save correctly
     */
    public static boolean saveAs() {
        if (chooserOpened) {
            return STOP_OPERATION;
        }
        selectedFile = new IOHistoryHandler().openSaveDialog(historyHandler.getNickNameOfHistory());
        try {
            if (!canSaveAs() || selectedFile == null) {
                return STOP_OPERATION;
            }
        } catch (HistoryNotInitializedException e) {
            System.out.println(e.getMessage() + "['SAVE AS' ABORTED]");
            return STOP_OPERATION;
        }

        // initialize needed fields
        ext = "*." + FilenameUtils.getExtension(selectedFile.getName()).toLowerCase(Locale.getDefault());
        absoluteFilePath = FilenameUtils.removeExtension(selectedFile.getAbsolutePath()) + ext.substring(1);
        final History tmpHistory = historyHandler.getCurrentHistory();
        String tmpName = tmpHistory.getNameHistory();
        final Integer tmpCurrentIndex;

        if (ext.equals(HISTORY_FORMAT)) {

            tmpCurrentIndex = historyHandler.getCurrentHistory().getCurrentIndex();
            tmpHistory.setNameHistory(absoluteFilePath);

            ((VoidEffect) tmpHistory.getOriginal().getEffect()).setSavedIndex(tmpCurrentIndex);
            ((HistoryImpl) tmpHistory).setIndex(tmpCurrentIndex);

            // control to not override file in default saving path if choosed it in saveAs
            // without save file in default position first
            if (FilenameUtils.getFullPath(absoluteFilePath)
                    .equals(FilenameUtils.getFullPath(tmpHistory.getNameHistory()))) {
                tmpName = secureMshiNameMaker(absoluteFilePath);
            }
            new HistoryManager(tmpName).saveHistory(tmpHistory);

        } else if (FormatManager.isAllowedMustashiFormat(ext)) {

            new HistoryManager(absoluteFilePath).exportImage(historyHandler.getCurrentImage());
        }
        return END_CORRECTLY;
    }

    /*
     * return: true if current history is instantiated and modified
     * 
     * throws: HistoryNotInitializedException if no history added in
     * HistoryHandler's Map
     */
    private static boolean canSave() throws HistoryNotInitializedException {
        updateHandler();
        if (!historyHandler.exist()) {
            throw new HistoryNotInitializedException("No history loaded in HistoryHandler!");
        }
        return !historyHandler.currentHistoryIsSaved();
    }

    /*
     * return: true if current history is an image loaded but not modified yet
     * 
     * throws: HistoryNotInitializedException if no history added in
     * HistoryHandler's Map
     */
    private static boolean canSaveAs() throws HistoryNotInitializedException {
        updateHandler();
        if (!historyHandler.exist()) {
            throw new HistoryNotInitializedException("No history loaded in HistoryHandler!");
        }
        final History tmpHistory = historyHandler.getCurrentHistory();
        final Integer currentIndex = tmpHistory.getCurrentIndex();
        final boolean isModified = !tmpHistory.getLast().getEffect().getEffectName()
                .equals(new VoidEffect().getEffectName());
        ((HistoryImpl) tmpHistory).setIndex(currentIndex);
        return isModified;
    }

    /*
     * update HistoryHandler before use it
     */
    private static void updateHandler() {
        historyHandler = HistoryHandler.getHistoryHandler();
    }

    /*
     * open loader dialog of FileChooser, with selected format setted using
     * FormatManager
     */
    private File openLoadDialog() {
        setChooserAsOpened();
        final FileChooser tmpChooser = new FormatManager().getLoaderChooser();
        final File tmpFile;
        tmpChooser.setTitle(LOADER_TITLE);
        tmpChooser.setInitialDirectory(new File(DEF_HISTORIES_PATH));
        tmpFile = tmpChooser.showOpenDialog(null);
        setChooserAsClosed();
        return tmpFile;
    }

    /*
     * open saver dialog of FileChooser, with selected format setted using
     * FormatManager
     */
    private File openSaveDialog(final String startName) {
        setChooserAsOpened();
        final FileChooser tmpChooser;
        try {
            tmpChooser = new FormatManager().getSaverChooser(getInitialImageExtension());
        } catch (HistoryNotInitializedException e) {
            System.out.println(e.getMessage() + "[OPENING SAVE DIALOG ABORTED]");
            return null;
        }
        tmpChooser.setTitle(SAVER_TITLE);
        tmpChooser.setInitialFileName(startName);
        final File tmpFile = tmpChooser.showSaveDialog(null);
        setChooserAsClosed();
        return tmpFile;
    }

    private static void setChooserAsOpened() {
        chooserOpened = CHOOSER_OPENED;
    }

    private static void setChooserAsClosed() {
        chooserOpened = CHOOSER_CLOSED;
    }

    /*
     * search intial image format in VoidEffect history component to set selected
     * format in file dialog. if EdgeDetection founded, invite user to save image in
     * png format to preserve transparency
     */
    private String getInitialImageExtension() throws HistoryNotInitializedException {
        if (!historyHandler.exist()) {
            throw new HistoryNotInitializedException("No history loaded in HistoryHandler!");
        }
        final HistoryImpl tmpHistory = (HistoryImpl) historyHandler.getCurrentHistory();
        final Integer tmpCurrentIndex = tmpHistory.getCurrentIndex();
        final Integer tmpLastIndex = tmpHistory.getLastIndex();
        tmpHistory.setIndex(tmpLastIndex);
        String imgExt;

        if (tmpHistory.getCurrent().getEffect() instanceof EdgeDetection) {
            ((VoidEffect) tmpHistory.getOriginal().getEffect()).setImageFormat(PNG);
        } else {

            while (!(tmpHistory.getCurrent().getEffect() instanceof VoidEffect)) {
                if (tmpHistory.getPrevious().getEffect() instanceof VoidEffect) {
                    imgExt = ((VoidEffect) tmpHistory.getCurrent().getEffect()).getImageFormat();
                    tmpHistory.setIndex(tmpCurrentIndex);
                    return "*." + imgExt;
                } else if (tmpHistory.getCurrent().getEffect() instanceof EdgeDetection) {
                    ((VoidEffect) tmpHistory.getOriginal().getEffect()).setImageFormat(PNG);
                    break;
                }
            }
        }
        imgExt = ((VoidEffect) tmpHistory.getOriginal().getEffect()).getImageFormat();

        tmpHistory.setIndex(tmpCurrentIndex);
        return "*." + imgExt;
    }

    /*
     * Search in default location and in actual loaded project if there's someone
     * with same name, than modify new name with first free index.
     * 
     * absolutePath absolute path to load updated absolute path with new name if
     * necessary
     */
    private static String secureMshiNameMaker(final String absolutePath) {
        updateHandler();
        final String mshiExt = HISTORY_FORMAT.substring(1); // ".mshi"

        // temp absolute path
        final String path = FilenameUtils.getFullPath(absolutePath);
        String tmpName = FilenameUtils.getBaseName(absolutePath);
        final String ext = "." + FilenameUtils.getExtension(absolutePath);
        String tmpAbsolutePath = path + tmpName + mshiExt;

        // temp File
        File mshiFile = new File(tmpAbsolutePath);
        File imgFile = new File(DEF_HISTORIES_PATH + tmpName + mshiExt);

        // utils
        Integer index = 1; // doesn't create name with index 0
        boolean changeStarded = false;

        /*
         * control if same history name already loaded, or a file already exist in
         * default save path (default save path will be modified only in "saveAs")
         * 
         * Need to make different control about loading history or loading image because
         * history file could have different path by default, instead of image file that
         * will be controlled only with modified path as default saving path
         * 
         * [ "condition" ] "condition explanation" :
         * ----------------------------------------------------------------------------
         * 
         * [ ext.equals(mshiExt) ? ] IF LOADING HISTORY ///////////////////////////////
         * 
         * IF FILE NOT EXIST:
         * 
         * [ !mshiFile.exist() && historyHandler.imageLoaded(tmpAbsolutePath) ] if file
         * not exist in default path, but new image with same name loaded and not
         * already saved in default path.
         * 
         * IF FILE EXIST: [ mshiFile.exists() && ....]
         * 
         * [ historyHandler.imageLoaded(tmpAbsolutePath) ] if file already exist in
         * default path and loaded
         * 
         * OR
         * 
         * [ !historyHandler.imageLoaded(tmpAbsolutePath) && changeStarded ] if file
         * already exist in default path and NOT loaded yet, have to recognize if it's
         * the first opening of this file, or we have started changing. By changing name
         * and adding index, could find existing file's occurrence with superior index
         * even if no inferior index was founded. Than if changed start, means we have
         * to modify name until file exist and not loaded, otherwise, if change not
         * started yet, return pharam "absolutePath" assigned at this method to first
         * opening.
         * 
         * [ : ] IF LOADING IMAGE /////////////////////////////////////////////////////
         * 
         * [ imgFile.exists() ] if image file exist in default saving path
         * 
         * OR
         * 
         * [ historyHandler.imageLoaded(imgFile.getAbsolutePath()) ] if image file not
         * exist in default saving path, but loaded in history
         * 
         */
        while (ext.equals(mshiExt)
                ? (!mshiFile.exists() && historyHandler.imageLoaded(tmpAbsolutePath))
                        || (mshiFile.exists() && (historyHandler.imageLoaded(tmpAbsolutePath)
                                || (!historyHandler.imageLoaded(tmpAbsolutePath) && changeStarded)))
                : (imgFile.exists() || historyHandler.imageLoaded(imgFile.getAbsolutePath()))) {

            changeStarded = true;
            if (new EffectsClassName().isIndexYetCreated(tmpName)) {
                tmpName = tmpName.substring(0, tmpName.length() - (2 + Integer.toString(index - 1).length())) + "("
                        + index + ")";

                index++;
            } else {
                tmpName = tmpName + "(1)";
            }

            tmpAbsolutePath = path + tmpName + mshiExt;
            mshiFile = new File(tmpAbsolutePath);
            imgFile = new File(DEF_HISTORIES_PATH + tmpName + mshiExt);
        }

        return ext.equals(mshiExt) ? tmpAbsolutePath : DEF_HISTORIES_PATH + tmpName + mshiExt;
    }
}
