package view.util.theme;

import controller.SettingsHandler;
import javafx.stage.Stage;

/**
 * Manage GUI theme .
 */
public final class ThemeUtils {

    private static String currentTheme = Themes.JMetroDarkTheme.toString();

    static {
        try {
            currentTheme = SettingsHandler.loadSettings().getTheme();
        } catch (Exception e) {
            currentTheme = Themes.JMetroDarkTheme.toString();
        }
    }

    private ThemeUtils() {
    }

    /**
     * @param stage the stage to get the scene to apply a style sheet theme
     */
    public static void setTheme(final Stage stage) {
        stage.getScene().getStylesheets().add(getFullThemeName(currentTheme));
    }

    /**
     * @param stage the stage to get the scene to change a style sheet theme
     * @param theme the theme to apply for the GUI
     */
    public static void switchTheme(final Stage stage, final Themes theme) {
        stage.getScene().getStylesheets().remove(getFullThemeName(currentTheme));
        stage.getScene().getStylesheets().add(getFullThemeName(theme.toString()));
        currentTheme = theme.toString();
    }

    /**
     * @return the current theme of the GUI
     */
    public static Themes getCurrentThemes() {
        return Themes.valueOf(currentTheme);
    }

    private static String getFullThemeName(final String name) {
        return ("/styles/" + name + ".css");
    }

}
