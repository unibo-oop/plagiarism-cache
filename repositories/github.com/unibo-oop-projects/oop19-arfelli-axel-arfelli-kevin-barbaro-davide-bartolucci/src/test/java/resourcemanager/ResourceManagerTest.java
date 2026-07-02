package resourcemanager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import filetypes.Leaderboard;
import filetypes.Settings;
import javafx.util.Pair;

/**
 * This is a JUnit test class used for testing ResourceManagerAlpha,
 * as well as LeaderboardImpl and SettingsV1.
 */
public class ResourceManagerTest {

    private static ResourceManager mgr;
    private static final Integer CUSTOM_WIDTH = 1920;
    private static final Integer CUSTOM_HEIGHT = 1080;
    private static final Integer IMAGE_SIZE = 512;
    private static final Integer SCORE_1 = 50;
    private static final Integer SCORE_2 = 35;
    private static final Integer SCORE_3 = 20;
    private static final Integer SCORE_4 = 10;

    @org.junit.jupiter.api.BeforeAll
    public static void init() {
        mgr = ResourceManagerAlpha.getIstance();
    }

    /**
     * Checks if getSettingsAsObject and SettingsV1 work as intended.
     */
    @org.junit.jupiter.api.Test
    public void testSettings() {
        Settings settings = mgr.getSettingsAsObject();
        assertNotNull(settings);
        settings.setWindowSize(CUSTOM_WIDTH, CUSTOM_HEIGHT);
        mgr.saveSettings(settings);
        settings = null;
        settings = mgr.getSettingsAsObject();
        assertEquals(settings.getWindowSize(), new Pair<Integer, Integer>(CUSTOM_WIDTH, CUSTOM_HEIGHT));
    }

    /**
     * Checks if LeaderboardImpl works as intended (especially the list sorting).
     * This method uses mgr.saveLeaderboard(null) to "delete" the leaderboard file
     * content, so that the next time the test is ran, the method getLeaderboardAsObject
     * returns a brand new Leaderboard object, without loading the old one from file.
     */
    @org.junit.jupiter.api.Test
    public void testLeaderboard() {
        Leaderboard leaderboard = mgr.getLeaderboardAsObject();
        assertNotNull(leaderboard);
        leaderboard.addRecord(new Pair<String, Integer>("Mario", SCORE_3));
        leaderboard.addRecord(new Pair<String, Integer>("Andrea", SCORE_1));
        leaderboard.addRecord(new Pair<String, Integer>("Kevin", SCORE_2));
        leaderboard.addRecord(new Pair<String, Integer>("Aster", SCORE_4));
        mgr.saveLeaderboard(leaderboard);
        leaderboard = null;
        leaderboard = mgr.getLeaderboardAsObject();
        assertEquals(leaderboard.getList().get(0), new Pair<String, Integer>("Andrea", SCORE_1));
        assertEquals(leaderboard.getList().get(leaderboard.getList().size() - 1), new Pair<String, Integer>("Aster", 10));
        assertEquals(leaderboard.getList().size(), 4);
        mgr.saveLeaderboard(null);
        System.out.println("Leaderboard: " + leaderboard.getList().toString());
    }

    /**
     * Checks if ResourceManagerAlpha is able to correctly generate
     * a Image object if the requested picture doesn't exist.
     */
    @org.junit.jupiter.api.Test
    public void testMissingImages() {
        assertNotNull(mgr.getImage("missing_image.png"));
        assertEquals((int) mgr.getImage("missing_image.png").getHeight(), IMAGE_SIZE);
    }

    /**
     * Checks if the method getFXMLFileURL is able to correctly
     * return an optional containing the wanted URL (if the file exists)
     * or an empty optional (if the file doesn't exist).
     */
    @org.junit.jupiter.api.Test
    public void testFXML() {
        assertTrue(mgr.getFXMLFileURL("MainMenu.fxml").isPresent());
        assertFalse(mgr.getFXMLFileURL("NonExisting.fmxl").isPresent());
    }
}
