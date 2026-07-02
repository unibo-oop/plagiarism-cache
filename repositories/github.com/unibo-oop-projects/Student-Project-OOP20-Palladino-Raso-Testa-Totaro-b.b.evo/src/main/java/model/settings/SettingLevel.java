package model.settings;

import java.io.Serializable;

import model.mapeditor.Level;
import model.mapeditor.LevelStandard;


/**
 * Prepare the level to be saved or load in future.
 *
 */
public final class SettingLevel implements Serializable {

    private static final long serialVersionUID = -2472026492104740049L;
    private final Level selectedLevel;

    /**
     * @param selectedLevel that you want to put on setting to process
     */
    private SettingLevel(final Level selectedLevel) {
        this.selectedLevel = selectedLevel;
    }

    /**
     * @return the selectedLevel
     */
    public Level getSelectedLevel() {
        return selectedLevel;
    }

    public static final class SettingLevelBuilder {

        private Level selectedLevel;

        public SettingLevelBuilder() {
            this.selectedLevel = LevelStandard.LEVEL1.getLevel();
        }

        /**
         * Set the settings by copying the parameters from other settings.
         * @param settings to copy
         * @return returns himself following the pattern builder
         */
        public SettingLevelBuilder fromSettings(final SettingLevel settings) {
            this.selectedLevel = settings.getSelectedLevel();
            return this;
        }

        /**
         * @param selectedLevel that you want store into SettingLevel
         * @return the level selected wrapped into SettingLevelBuilder
         */
        public SettingLevelBuilder selectLevel(final Level selectedLevel) {
            this.selectedLevel = selectedLevel;
            return this;
        }

        /**
         * 
         * @return build settings making sure that the values entered are correct
         */
        public SettingLevel build() {
            if (this.selectedLevel == null) {
                throw new IllegalStateException();
            }
            return new SettingLevel(selectedLevel);
        }
    }
}
