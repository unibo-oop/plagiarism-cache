package controller.settings;

import com.almasb.fxgl.settings.GameSettings;

import model.game.settings.BasicSettings;

/**
 * 
 * Class responsible for manipulating a GameSetting object.
 *
 */
public class GameSetter {

    private final GameSettings settings;
    /**
     *
     * @param settings
     *                  An object storing game settings
     */
    public GameSetter(final GameSettings settings) {
        this.settings = settings;
    }

        /**
         * Provides the GameSettings parameter some basic setting informations.
         * 
         * @param settings
         *                      An object storing game settings
         *
         */
        public void configureBasicSettings(final GameSettings settings) {
            final BasicSettings basicSettings = new BasicSettings();
            settings.setTitle(basicSettings.getGameTitle());
            settings.setVersion(basicSettings.getGameVersion());
            settings.setHeight(basicSettings.getWindowHeight());
            settings.setWidth(basicSettings.getWindowWidth());
        }

        /**
         * @return the settings
         */
        public GameSettings getSettings() {
            return settings;
        }
}
