package starcatraz.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import starcatraz.model.Settings;
import starcatraz.model.Statistics;

/**
 * Used to read and write statistics and settings files.
 */
public final class FileReadWrite {

    private static final String DEFAULT_PATH = System.getProperty("user.home");
    private static final String DEFAULT_SEPARATOR = System.getProperty("file.separator");
    private static final String GAME_PATH = DEFAULT_PATH + DEFAULT_SEPARATOR + ".starcatraz";
    private static final String SETTINGS_PATH = GAME_PATH + DEFAULT_SEPARATOR + "settings.properties";
    private static final String STATISTICS_PATH = GAME_PATH + DEFAULT_SEPARATOR + "statistics.properties";

    private static final String STAT_VICTORIES = "VICTORIES";
    private static final String STAT_DEFEATS = "DEFEATS";
    private static final String STAT_PLAYED_GAMES = "PLAYED_GAMES";
    private static final String STAT_DEFEATED_ROBOTS = "DEFEATED_ROBOTS";
    private static final String STAT_CARD_STREAK = "CARD_STREAK";
    private static final String STAT_TIME_RECORD = "TIME_RECORD";
    private static final String STAT_DECK_RECORD = "DECK_RECORD";
    private static final String SETT_SFX_VOLUME = "SFX_VOLUME";
    private static final String SETT_MUSIC_VOLUME = "MUSIC_VOLUME";
    private static final String SETT_RES_HEIGHT = "RES_HEIGHT";
    private static final String SETT_RES_WIDTH = "RES_WIDTH";

    private static Properties statisticsProp = new Properties();
    private static Properties settingsProp = new Properties();

    private FileReadWrite() { }

    /**
     * Reads the Statistics from the configuration file into the given object.
     * @param settings
     */
    public static void readStatistics(final Statistics statistics) {
        InputStream statisticsFile = null;
        try {
            if (!checkDir(STATISTICS_PATH)) {
                final OutputStream statisticsFileOut = new FileOutputStream(STATISTICS_PATH);
                initializeStatistics();
                statisticsProp.store(statisticsFileOut, null);
                if (statisticsFileOut != null) {
                    try {
                        statisticsFileOut.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
            statisticsFile = new FileInputStream(STATISTICS_PATH);
            statisticsProp.load(statisticsFile);
            statistics.setVictories(Integer.parseInt(statisticsProp.getProperty(STAT_VICTORIES)));
            statistics.setDefeats(Integer.parseInt(statisticsProp.getProperty(STAT_DEFEATS)));
            statistics.setPlayedGames(Integer.parseInt(statisticsProp.getProperty(STAT_PLAYED_GAMES)));
            statistics.setDefeatedRobotsCount(Integer.parseInt(statisticsProp.getProperty(STAT_DEFEATED_ROBOTS)));
            statistics.setCardStreak(Integer.parseInt(statisticsProp.getProperty(STAT_CARD_STREAK)));
            statistics.setGameTimeRecord(statisticsProp.getProperty(STAT_TIME_RECORD));
            statistics.setVictoryWithFewestCards(Integer.parseInt(statisticsProp.getProperty(STAT_DECK_RECORD)));
        } catch (IOException ex) {
            ex.printStackTrace();
            initializeStatistics();
        } finally {
            if (statisticsFile != null) {
                try {
                    statisticsFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Reads the Settings from the configuration file into the given object.
     * @param settings
     */
    public static void readSettings(final Settings settings) {
        InputStream settingsFile = null;
        try {
            if (!checkDir(SETTINGS_PATH)) {
                final OutputStream settingsFileOut = new FileOutputStream(SETTINGS_PATH);
                initializeSettings();
                settingsProp.store(settingsFileOut, null);
                if (settingsFileOut != null) {
                    try {
                        settingsFileOut.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            settingsFile = new FileInputStream(SETTINGS_PATH);
            settingsProp.load(settingsFile);
            settings.setSoundEffectsVolume(Double.parseDouble(settingsProp.getProperty(SETT_SFX_VOLUME)));
            settings.setMusicVolume(Double.parseDouble(settingsProp.getProperty(SETT_MUSIC_VOLUME)));
            settings.setResolutionHeight(Integer.parseInt(settingsProp.getProperty(SETT_RES_HEIGHT)));
            settings.setResolutionWidth(Integer.parseInt(settingsProp.getProperty(SETT_RES_WIDTH)));
        } catch (IOException ex) {
            ex.printStackTrace();
            initializeSettings();
        } finally {
            if (settingsFile != null) {
                try {
                    settingsFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    /**
     * Writes the Statistics to the configuration file.
     * @param settings
     */
    public static void writeStatistics(final Statistics statistics) {
        OutputStream statisticsFile = null;
        try {
            statisticsFile = new FileOutputStream(STATISTICS_PATH);
            if (!checkDir(STATISTICS_PATH)) {
                initializeStatistics();
            }
            statisticsProp.setProperty(STAT_VICTORIES, Integer.toString(statistics.getVictories()));
            statisticsProp.setProperty(STAT_DEFEATS, Integer.toString(statistics.getDefeats()));
            statisticsProp.setProperty(STAT_PLAYED_GAMES, Integer.toString(statistics.getPlayedGames()));
            statisticsProp.setProperty(STAT_DEFEATED_ROBOTS, Integer.toString(statistics.getDefeatedRobotsCount()));
            statisticsProp.setProperty(STAT_CARD_STREAK, Integer.toString(statistics.getCardStreak()));
            statisticsProp.setProperty(STAT_TIME_RECORD, statistics.getGameTimeRecord().toString());
            statisticsProp.setProperty(STAT_DECK_RECORD, Integer.toString(statistics.getVictoryWithFewestCards()));
            statisticsProp.store(statisticsFile, null);
        } catch (IOException io) {
            io.printStackTrace();
            initializeStatistics();
        } finally {
            if (statisticsFile != null) {
                try {
                    statisticsFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * Writes the Settings to the configuration file.
     * @param settings
     */
    public static void writeSettings(final Settings settings) {
        OutputStream settingsFile = null;
        try {
            settingsFile = new FileOutputStream(SETTINGS_PATH);
            if (!checkDir(SETTINGS_PATH)) {
                initializeSettings();
            }
            settingsProp.setProperty(SETT_SFX_VOLUME, Double.toString(settings.getSoundEffectsVolume()));
            settingsProp.setProperty(SETT_MUSIC_VOLUME, Double.toString(settings.getMusicVolume()));
            settingsProp.setProperty(SETT_RES_HEIGHT, Integer.toString(settings.getResolutionHeight()));
            settingsProp.setProperty(SETT_RES_WIDTH, Integer.toString(settings.getResolutionWidth()));
            settingsProp.store(settingsFile, null);
        } catch (IOException io) {
            io.printStackTrace();
            initializeSettings();
        } finally {
            if (settingsFile != null) {
                try {
                    settingsFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * Check if a game configuration file exists.
     * @param pathToCheck
     * @return true if the p
     * @throws IOException
     */
    private static boolean checkDir(final String pathToCheck) throws IOException {
        boolean ris = true;
        final File dir = new File(GAME_PATH);
        final File fileToCheck = new File(pathToCheck);
        if (!dir.exists()) {
            ris = false;
            if (!dir.mkdir()) {
                throw new IOException();
            }
        } else if (!fileToCheck.exists()) {
            ris = false;
            if (!fileToCheck.createNewFile()) {
                throw new IOException();
            }
        }
        return ris;
    }

    private static void initializeStatistics() {
        statisticsProp.setProperty(STAT_VICTORIES, "0");
        statisticsProp.setProperty(STAT_DEFEATS, "0");
        statisticsProp.setProperty(STAT_PLAYED_GAMES, "0");
        statisticsProp.setProperty(STAT_DEFEATED_ROBOTS, "0");
        statisticsProp.setProperty(STAT_CARD_STREAK, "0");
        statisticsProp.setProperty(STAT_TIME_RECORD, "23:59:59");
        statisticsProp.setProperty(STAT_DECK_RECORD, "76");
    }

    private static void initializeSettings() {
        settingsProp.setProperty(SETT_SFX_VOLUME, Double.toString(Settings.DEFAULT_SOUND_VOLUME));
        settingsProp.setProperty(SETT_MUSIC_VOLUME, Double.toString(Settings.DEFAULT_MUSIC_VOLUME));
        settingsProp.setProperty(SETT_RES_HEIGHT, Integer.toString(AppResolution.DEFAULT.getHeight()));
        settingsProp.setProperty(SETT_RES_WIDTH, Integer.toString(AppResolution.DEFAULT.getWidth()));
    }
}
