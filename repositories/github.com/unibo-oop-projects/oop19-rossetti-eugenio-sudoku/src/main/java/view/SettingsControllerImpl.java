package view;

import java.io.IOException;
import controller.Settings;
import controller.SudokuGameHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import utilities.Scenes;

/**
 * 
 * Controller for SettingsScene.
 *
 */

public class SettingsControllerImpl implements SceneController {

    private static final String ON = "ON";
    private static final String OFF = "OFF";

    @FXML
    private ToggleButton darkThemeBtn;

    @FXML
    private ToggleButton assistsBtn;

    private View view;
    private Settings settings;

    @FXML
    public final void initialize() {
        darkThemeBtn.setSelected(false);
        darkThemeBtn.setText(OFF);
        assistsBtn.setSelected(false);
        assistsBtn.setText(OFF);
    }

    @FXML
    public final void darkThemeBtnOnClick() {
        if (darkThemeBtn.isSelected()) {
            darkThemeBtn.setText(ON);
            settings.setDarkTheme();
        } else {
            darkThemeBtn.setText(OFF);
            settings.setDarkTheme();
        }
        view.setScene(Scenes.SETTINGS);
    }

    @FXML
    public final void assistsBtnOnClick() {
        if (assistsBtn.isSelected()) {
            assistsBtn.setText(ON);
            settings.setAssists();
        } else {
            assistsBtn.setText(OFF);
            settings.setAssists();
        }
    }

    @FXML
    public final void exitBtn() throws IOException {
        view.setScene(Scenes.HOME);
    }

    @Override
    public final void setSudokuGameHandler(final SudokuGameHandler handler) {
        this.settings = handler.getSettings();
        darkThemeBtn.setSelected(settings.isDarkThemeOn());
        darkThemeBtn.setText(darkThemeBtn.isSelected() ? ON : OFF);
        assistsBtn.setSelected(settings.assistsOn());
        assistsBtn.setText(assistsBtn.isSelected() ? ON : OFF);
    }

    @Override
        public final void setView(final View view) {
           this.view = view;
    }
}
