package it.unibo.the100dayswar.model.savedata;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import it.unibo.the100dayswar.model.savedata.impl.GameSaverImpl;

import org.junit.jupiter.api.AfterEach;

/**
 * Test suite for GameSaverImpl.
 */
class GameSaverTest extends AbstractGameTest {
    private static final String TEST_CUSTOM_PATH = generateSavePath("test_data");
    private static final String DEFAULT_PATH = System.getProperty("user.home") + "/saved_game.ser";

    /**
     * Cleans up the files.
     * 
     * @throws IOException
     */
    @AfterEach
    void cleanUp() throws IOException {
        Files.deleteIfExists(Paths.get(TEST_CUSTOM_PATH));
        Files.deleteIfExists(Paths.get(DEFAULT_PATH));
    }

    /**
     * Test saving to a custom path.
     * @throws IOException
     */
    @Test
    void testSaveGameWithCustomPath() throws IOException {
        final GameSaverImpl saver = new GameSaverImpl(getMockGameData(), TEST_CUSTOM_PATH);
        saver.saveGame();

        // Verify file creation
        assertTrue(Files.exists(Paths.get(TEST_CUSTOM_PATH)), "File should exist at the custom path.");
    }

    /**
     * Test saving to the default path.
     * @throws IOException
     */
    @Test
    void testSaveGameWithDefaultPath() throws IOException {
        final GameSaverImpl saver = new GameSaverImpl(getMockGameData());
        saver.saveGame();

        // Verify file creation
        assertTrue(Files.exists(Paths.get(DEFAULT_PATH)), "File should exist at the default path.");
    }

    /**
     * Test that an IOException is logged when saving fails.
     */
    @Test
    void testSaveGameThrowsIOException() {
        final String invalidPath = "/invalid_path/saved_game.ser";
        final GameSaverImpl saver = new GameSaverImpl(getMockGameData(), invalidPath);

        final Exception exception = assertThrows(IOException.class, saver::saveGame);

        assertTrue(exception.getMessage().contains("Error saving game at path: " + invalidPath));
    }

    /**
     * Test saving null game data throws an exception.
     */
    @Test
    void testConstructorWithNullGameDataThrowsException() {
        final Exception exception = assertThrows(IllegalArgumentException.class, () ->
            new GameSaverImpl(null, TEST_CUSTOM_PATH)
        );

        assertTrue(exception.getMessage().contains("Game data must be non-null"));
    }

    /**
     * Test that saving to a custom path overwrites an existing file.
     */
    @Test
    void testSaveGameOverwritesExistingFile() throws IOException {
        Files.createFile(Paths.get(TEST_CUSTOM_PATH));

        final GameSaverImpl saver = new GameSaverImpl(getMockGameData(), TEST_CUSTOM_PATH);
        saver.saveGame();

        // Verify file is still present (i.e. overwritten)
        assertTrue(Files.exists(Paths.get(TEST_CUSTOM_PATH)), "File should be overwritten at the custom path.");
    }

    /**
     * Generate a custom path.
     * 
     * @param gameName the name of the game
     * @return the save path
     */
    static String generateSavePath(final String gameName) {
        final String userHome = System.getProperty("user.home");
        return Paths.get(userHome, "Documents", gameName + ".sav").toString();
    }
}
