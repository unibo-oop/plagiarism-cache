package model.util.storage;

import model.util.format.Format;
import model.util.format.FormatManager;
import model.util.settings.Settings;

/**
 * 
 */
public class SettingsManager extends ObjectManagerImpl<Settings> {

    private static final String SETTINGS_PATH = FormatManager.getDefaultSettingsPath();
    private static final String FORMAT = Format.json.toString();

    /**
     * Setts the Settings save path.
     */
    public SettingsManager() {
        super(SETTINGS_PATH + "config" + FORMAT);
    }
}
