package utilities.test;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.junit.Test;

import model.settings.SettingsManager;
import model.settings.SettingsManagerInterface;

/**
 * This class can be used to test the correct working of the settings manager.
 * Andrea Serafini.
 *
 */
public class SettingsTest {

    private static final String STARTING_SETTINGS_FILE = "startingSettings.dat";
    private static final String SP = File.separator;
    private final SettingsManagerInterface manager = SettingsManager.getLog();

    /**
     *
     * @return true if file is present
     */
    public boolean isPresent() {
        String jarPath = "";
        String completePath = "";
        try {
            jarPath = URLDecoder.decode(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath(),
                    "UTF-8");
        } catch (final UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        completePath = jarPath.substring(0, jarPath.lastIndexOf('/')) + SP + STARTING_SETTINGS_FILE;
        final File f = new File(completePath);
        return f.exists();
    }

    /**
     *
     */
    @Test
    public void test() {
        assertTrue("Is present", this.manager.isPresent());
        System.out.println(this.manager.getSettings().toString());

        // manager.loadSettings();
        // manager.getSettings();
        // initializeSettings();
        // System.out.println(manager.getSettings().getMatchPlayers().toString());
        // System.out.println(manager.getSettings().string());
        // updateSettings();

        // manager.getSettings().setMatchPlayers(createList());
        // manager.getSettings().setBoardLimit(19);
        // manager.saveSettings();
        // System.out.println(manager.getSettings().string());
        // System.out.println(manager.getSettings().getMatchPlayers().toString());

        // manager.loadSavedSettings();
        // System.out.println(manager.getSettings().string());
        // System.out.println(manager.getSettings().getMatchPlayers().toString());
    }

}
