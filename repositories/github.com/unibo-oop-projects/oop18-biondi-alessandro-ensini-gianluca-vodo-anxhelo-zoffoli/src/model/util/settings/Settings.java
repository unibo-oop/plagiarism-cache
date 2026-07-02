package model.util.settings;

/**
 * Define the basic settings.
 */
public class Settings {

    private String theme;
    private String language;

    /**
     * Crate a new Setting with the givven parametres.
     * 
     * @param language to set
     * @param theme    to set
     */
    public Settings(final String language, final String theme) {
        this.language = language;
        this.theme = theme;
    }

    /**
     * @return current theme
     */
    public String getTheme() {
        return theme;
    }

    /**
     * Set the current theme.
     * 
     * @param theme to set
     */
    public void setTheme(final String theme) {
        this.theme = theme;
    }

    /**
     * @return current locale
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Set the current languagee.
     * 
     * @param language to set
     */
    public void setLanguage(final String language) {
        this.language = language;
    }

}
