package utility;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.tuple.ImmutablePair;

import model.data.GlobalData;
import settings.SettingsImpl;
import settings.utilities.SettingsToSave;

/**
 * Utility Class that provides the general utility for the all application.
 */
public final class StaticUtilities {

    private static final String CURRENT_FOLDER = System.getProperty("user.dir");
    private static final String STORED_DATA_FILE_NAME = "MagnumChaosData";
    private static final String STORED_SETTINGS_FILE_NAME = "MagnumChaosSettings";
    private static final String STORED_DATA_FILE_PATH = CURRENT_FOLDER + File.separator + STORED_DATA_FILE_NAME;
    private static final String STORED_SETTINGS_FILE_PATH = CURRENT_FOLDER + File.separator + STORED_SETTINGS_FILE_NAME;

    private StaticUtilities() {

    }

    /**
     * 
     * @param period
     *            total time available to perform the cycle phase.
     * @param start
     *            start time of the cycle phase.
     */
    public static void waitForNextFrame(final int period, final long start) {
        final long dt = System.currentTimeMillis() - start;
        if (dt < period) {
            try {
                Thread.sleep(period - dt);
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Read an object from file.
     * 
     * @param fileName
     *            name of the file to read
     * @return the object read
     */
    public static Optional<Object> readFromFile(final String fileName) {
        try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)))) {
            return Optional.of(in.readObject());
        } catch (FileNotFoundException e) {
            return Optional.empty();
        } catch (IOException | ClassNotFoundException e) {
            return Optional.empty();
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    /**
     *
     * Write an object to file.
     * 
     * @param fileName
     *            file to write
     * @param data
     *            object written
     * @param append
     *            if true append object to file
     */
    public static void writeToFile(final String fileName, final Object data, final boolean append) {
        final File outputFile = new File(fileName);
        try {
            outputFile.createNewFile();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try (ObjectOutputStream out = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(outputFile, append)))) {
            out.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read global Data from file.
     * 
     * @return the global data read
     */
    public static Optional<GlobalData> readGlobalDataFromFile() {
        final Optional<Object> obj = StaticUtilities.readFromFile(StaticUtilities.STORED_DATA_FILE_PATH);
        final Optional<GlobalData> globalData;
        if (obj.isPresent()) {
            globalData = Optional.of((GlobalData) obj.get());
        } else {
            globalData = Optional.empty();
        }
        return globalData;
    }

    /**
     * Read settings from file.
     */
    public static void readSettingsFromFile() {
        final Optional<Object> obj = StaticUtilities.readFromFile(StaticUtilities.STORED_SETTINGS_FILE_PATH);

        final Optional<Map<?, ?>> settingsToLoad;

        if (obj.isPresent()) {
            settingsToLoad = Optional.of((Map<?, ?>) obj.get());
            StaticUtilities.loadSavedSettings(settingsToLoad.get());
        }
    }

    /**
     * 
     * @param globalData
     *            global data to write
     */
    public static void writeGlobalDataToFile(final GlobalData globalData) {
        StaticUtilities.writeToFile(STORED_DATA_FILE_PATH, globalData, false);
    }

    /**
     * 
     * @param settingsToSave
     *            the settings that must be saved.
     */
    public static void writeSettingsToFile(final Map<SettingsToSave, Object> settingsToSave) {
        StaticUtilities.writeToFile(STORED_SETTINGS_FILE_PATH, settingsToSave, false);
    }

    /*
     * Loads the saved settings.
     */
    private static void loadSavedSettings(final Map<?, ?> savedSettings) {
        SettingsImpl.getSettings().setSelectedResolution(
                new ImmutablePair<Integer, Integer>((int) savedSettings.get(SettingsToSave.RESOLUTION_WIDTH),
                        (int) savedSettings.get(SettingsToSave.RESOLUTION_HEIGHT)));
        SettingsImpl.getSettings().setSelectedFPS((int) savedSettings.get(SettingsToSave.FPS));
        SettingsImpl.getSettings().setFullScreen((boolean) savedSettings.get(SettingsToSave.FULLSCREEN));
        SettingsImpl.getSettings().setBackGroundAudio((boolean) savedSettings.get(SettingsToSave.BACKGROUND_AUDIO));
        SettingsImpl.getSettings().setTrainingMode((boolean) savedSettings.get(SettingsToSave.TRAININGMODE));
    }

}
