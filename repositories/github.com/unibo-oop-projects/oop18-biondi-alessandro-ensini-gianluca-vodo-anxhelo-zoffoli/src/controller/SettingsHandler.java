package controller;

import java.io.IOException;
import java.util.Locale;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.util.settings.Settings;
import model.util.storage.SettingsManager;
import view.util.language.LanguageManagerUtils;
import view.util.theme.ThemeUtils;
import view.util.theme.Themes;

/**
 * Handler of the view of the settings.
 */
public final class SettingsHandler {

    private static Stage settingsStage;
    private static VBox settingsLayout;
    private static FXMLLoader fxmlLoader;

    /**
     * Show the settings view.
     */
    public static void showSettings() {
        settingsLayout = new VBox();
        fxmlLoader = new FXMLLoader();
        settingsStage = new Stage();
        settingsStage.getIcons().add(new Image("/icons/mustashi.png"));

        settingsStage.initModality(Modality.APPLICATION_MODAL);
        settingsStage.titleProperty().bind(LanguageManagerUtils.createStringBinding("settings.title"));

        try {
            fxmlLoader.setResources(LanguageManagerUtils.getResourceBundele());
            fxmlLoader.setLocation(MainViewHandler.class.getResource("/scene/Settings.fxml"));
            fxmlLoader.setRoot(null);
            settingsLayout = fxmlLoader.load();
            settingsStage.setScene(new Scene(settingsLayout));
            ThemeUtils.setTheme(settingsStage);
            settingsStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Close the settings view.
     */
    public void closeSettings() {
        getStage().close();
    }

    /**
     * Change the current Locale.
     * 
     * @param locale new locale
     */
    public void changeLanguage(final Locale locale) {
        LanguageManagerUtils.setLocale(locale);
        saveSettings();
    }

    /**
     * Change the current theme.
     * 
     * @param theme the selected theme to apply
     */
    public void changeTheme(final Themes theme) {
        ThemeUtils.switchTheme(MainViewHandler.getPrimaryStage(), theme);
        saveSettings();
    }

    /**
     * Load the settings from config file.
     * 
     * @return Settings contained in the config file
     * @throws IOException if load fail
     */
    public static Settings loadSettings() throws IOException {
        return new SettingsManager().load(Settings.class);
    }

    private void saveSettings() {
        new SettingsManager().save(
                new Settings(LanguageManagerUtils.getLocale().getLanguage(), ThemeUtils.getCurrentThemes().name()));
    }

    private static Stage getStage() {
        return settingsStage;
    }
}
