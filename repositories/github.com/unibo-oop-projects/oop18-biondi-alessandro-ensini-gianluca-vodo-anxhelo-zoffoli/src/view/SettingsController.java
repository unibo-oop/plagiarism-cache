package view;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import controller.SettingsHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import view.util.language.LanguageManagerUtils;
import view.util.theme.ThemeUtils;
import view.util.theme.Themes;

/**
 * Controller of Settings.
 */
public class SettingsController implements Initializable {

    private SettingsHandler settingsHandler;

    @FXML
    private Button buttonApply;
    @FXML
    private Button buttonCancel;
    @FXML
    private ComboBox<String> cmbLang;
    @FXML
    private ComboBox<String> cmbTheme;

    /**
     * Apply the changed settings.
     */
    public void applySettings() {
        applyLanguage();
        applyTheme();
        settingsHandler.closeSettings();
    }

    /**
     * Cancel the changed settings.
     */
    public void cancel() {
        settingsHandler.closeSettings();
    }

    /**
     * @see javafx.fxml.Initializable#initialize(java.net.URL,
     *      java.util.ResourceBundle)
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        settingsHandler = new SettingsHandler();
        initializeLanguage();
        initializeTheme();
    }

    private void initializeLanguage() {
        for (final Locale l : LanguageManagerUtils.getSupportedLocales()) {
            cmbLang.getItems().add(l.getDisplayLanguage(LanguageManagerUtils.getLocale()));
        }
        cmbLang.getSelectionModel()
                .select(LanguageManagerUtils.getLocale().getDisplayLanguage(LanguageManagerUtils.getLocale()));
    }

    private void initializeTheme() {
        for (final Themes theme : Themes.values()) {
            cmbTheme.getItems().add(LanguageManagerUtils.createStringBinding(theme.toString()).getValue());
        }
        cmbTheme.getSelectionModel().select(ThemeUtils.getCurrentThemes().ordinal());
    }

    private void applyLanguage() {
        final Locale newLocale = LanguageManagerUtils.getSupportedLocales()
                .get(cmbLang.getSelectionModel().getSelectedIndex());
        settingsHandler.changeLanguage(newLocale);
    }

    private void applyTheme() {
        settingsHandler.changeTheme(Themes.values()[cmbTheme.getSelectionModel().getSelectedIndex()]);
    }

}
