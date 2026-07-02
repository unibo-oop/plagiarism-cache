package controller;

import utilities.Theme;

/**
* The implementation of Settings.
*/

public class SettingsImpl implements Settings {

    private Theme theme;
    private boolean darkTheme;
    private boolean assists;

    /**
     * Setup the settings.
     */
    public SettingsImpl() {
        theme = Theme.LIGHT;
        this.darkTheme = false;
        this.assists = false;
    }

    @Override
    public final boolean assistsOn() {
        return assists;
    }

    @Override
    public final void setAssists() {
        assists = !assists;
    }

    @Override
    public final boolean isDarkThemeOn() {
        return this.darkTheme;
    }

    @Override
    public final void setDarkTheme() {
        darkTheme = !darkTheme;
        theme = darkTheme ? Theme.DARK : Theme.LIGHT; 
    }

    @Override
    public final Theme getTheme() {
        return theme;
    }
}
