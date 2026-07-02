package view;

import java.net.URL;
import java.text.MessageFormat;
import java.util.ResourceBundle;

import controller.HistoryHandler;
import controller.SaveWindowHandler;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import view.util.language.LanguageManagerUtils;

/**
 * Controller of SaveWindow.
 */
public class SaveWindowController implements Initializable {

    private final SaveWindowHandler saveW = new SaveWindowHandler();

    @FXML
    private Button buttonSave;
    @FXML
    private Button buttonDSave;
    @FXML
    private Button buttonCancel;
    @FXML
    private Label labelNoSave;

    /**
     * Open save file dialog.
     */
    public void manageSave() {
        saveW.save();
    }

    /**
     * exit without saving.
     */
    public void manageDSave() {
        saveW.dSave();
    }

    /**
     * close SaveWindow.
     */
    public void manageCancel() {
        saveW.cancel();
    }

    /**
     * @see javafx.fxml.Initializable#initialize(java.net.URL,
     *      java.util.ResourceBundle)
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        final StringBinding saveMessage = Bindings.createStringBinding(() -> createSaveMessage("label.nosave"),
                LanguageManagerUtils.getLocaleProperty());
        labelNoSave.textProperty().bind(saveMessage);
    }

    private String createSaveMessage(final String key) {
        return MessageFormat.format(LanguageManagerUtils.get(key),
                HistoryHandler.getHistoryHandler().getNickNameOfHistory());
    }

}
