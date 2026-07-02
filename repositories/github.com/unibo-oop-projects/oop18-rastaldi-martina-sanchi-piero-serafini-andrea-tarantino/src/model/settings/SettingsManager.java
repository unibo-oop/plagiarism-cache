package model.settings;

import utilities.FileManager;
import utilities.FileManagerInterface;

/**
 * This class manipulate the file for the game settings.
 * Andrea Serafini.
 *
 */
public final class SettingsManager implements SettingsManagerInterface {
    private static SettingsManager manager;
    private final FileManagerInterface<GameSettings> savedFileManager = new FileManager<>(
            GameSettings.getSavedSettingsFileName());
    private final FileManagerInterface<GameSettings> fileManager = new FileManager<>(GameSettings.getSettingsFileName());

    private GameSettings settings;

    /**
     * Constructor.
     */
    public SettingsManager() {
        this.loadSettings();
    }

    private void createSettingsFile() {
        this.settings = new GameSettings();
        this.settings.initialize();
        this.fileManager.saveFile(this.settings);
    }

    @Override
    public void deleteSavedSettings() {
        this.savedFileManager.deleteFile();
    }

    @Override
    public GameSettings getSettings() {
        return this.settings;
    }

    @Override
    public boolean isPresent() {
        return this.fileManager.isPresent();
    }

    @Override
    public boolean isSavedPresent() {
        return this.savedFileManager.isPresent();
    }

    @Override
    public void loadSavedSettings() {
        if (this.savedFileManager.isPresent()) {
            this.savedFileManager.loadFile();
            this.settings = this.savedFileManager.get();
        } else {
            this.fileManager.loadFile();
        }
    }

    private void loadSettings() {
        if (this.fileManager.isPresent()) {
            this.fileManager.loadFile();
            this.settings = this.fileManager.get();
        } else {
            this.createSettingsFile();
        }
    }

    @Override
    public void saveSettings() {
        this.settings.decrementTurn();
        this.savedFileManager.saveFile(this.settings);
    }

    @Override
    public void updateSavedSettings() {
        if (!this.savedFileManager.isPresent()) {
            this.savedFileManager.saveFile(null);
        }
        this.savedFileManager.updateFile(this.settings);
    }

    /**
     *
     * @return a settingsManager instance
     */
    public static synchronized SettingsManager getLog() {
        if (manager == null) {
            manager = new SettingsManager();
        }
        return manager;
    }
}
