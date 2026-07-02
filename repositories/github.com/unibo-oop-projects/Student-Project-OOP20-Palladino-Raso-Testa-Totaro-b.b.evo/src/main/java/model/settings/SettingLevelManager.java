package model.settings;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import launcher.BrickBreakerEvo;
import model.settings.SettingLevel.SettingLevelBuilder;

/**
 * Manage the setting of the level (save, load, init levelOption).
 *
 */
public final class SettingLevelManager {

    private SettingLevelManager() {
    }

    /**
     * 
     * Load the default setting of level(load first level).
     */
    public static void init() {
        final SettingLevelBuilder initialSetting = new SettingLevelBuilder();

        try (ObjectOutputStream ostream = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(BrickBreakerEvo.SETTING_LEVEL)))) {
            ostream.writeObject(initialSetting.build());
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }
    /**
     * Save the current settings in the default game folder.
     * @param settings to save
     */
    public static void saveOption(final SettingLevel settings) {
        try (ObjectOutputStream ostream = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(BrickBreakerEvo.SETTING_LEVEL)))) {
            ostream.writeObject(settings);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    /**
     * Load the saves that are in the default game folder.
     * @return current selected settings
     */
    public static SettingLevel loadOption() {
        try (ObjectInputStream istream = new ObjectInputStream(
        new BufferedInputStream(new FileInputStream(BrickBreakerEvo.SETTING_LEVEL)))) {
            return (SettingLevel) istream.readObject();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (ClassNotFoundException e3) {
            e3.printStackTrace();
        }
        return null;
    }

}
