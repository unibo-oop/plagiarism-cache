package spacesurvival.model.gui.settings;

/**
 *  Enumeration of units of settings.
 */
public enum UnitSettingsGUI {
    /**
     * Unit skin.
     */
    UNIT_SKIN("Skin");
    private String title;

    /**
     * Implement enumeration of unit of setting with title.
     * @param title
     */
    UnitSettingsGUI(final String title) {
        this.title = title;
    }

    /**
     * Get title unit of setting.
     * @return title of unit setting.
     */
    public String getTitle() {
        return this.title;
    }
}
