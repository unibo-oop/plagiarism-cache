package it.unibo.the100dayswar.model.loaddata;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import it.unibo.the100dayswar.model.bot.api.BotPlayer;
import it.unibo.the100dayswar.model.bot.impl.SimpleBot;
import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.gamedata.api.GameData;
import it.unibo.the100dayswar.model.gamedata.impl.GameDataImpl;
import it.unibo.the100dayswar.model.loaddata.api.GameLoader;
import it.unibo.the100dayswar.model.loaddata.impl.GameLoaderImpl;
import it.unibo.the100dayswar.model.savedata.api.GameSaver;
import it.unibo.the100dayswar.model.savedata.impl.GameSaverImpl;
import it.unibo.the100dayswar.model.player.api.HumanPlayer;
import it.unibo.the100dayswar.model.player.impl.HumanPlayerImpl;
import it.unibo.the100dayswar.model.map.api.GameMap;
import it.unibo.the100dayswar.model.map.api.MapManager;
import it.unibo.the100dayswar.model.map.impl.GameMapBuilderImpl;
import it.unibo.the100dayswar.model.map.impl.GameMapImpl;
import it.unibo.the100dayswar.model.map.impl.MapManagerImpl;
import it.unibo.the100dayswar.model.turn.api.GameTurnManager;
import it.unibo.the100dayswar.model.turn.impl.GameTurnManagerImpl;

/**
 * Test suite for GameLoaderImpl class.
 */
class GameLoaderTest {
    private static final String TEST_CUSTOM_PATH = "test_saved_game.ser";
    private static final String DEFAULT_PATH = System.getProperty("user.home") + "/saved_game.ser";
    private static final int MAP_HEIGHT = 10;
    private static final int MAP_WIDTH = 10;

    private static final int MAP_DIMENSION = 10;

    private final MapManager mockGameMapManager = new MapManagerImpl(new GameMapBuilderImpl(MAP_DIMENSION, MAP_DIMENSION));
    private final BotPlayer mockBotPlayer = new SimpleBot(mockGameMapManager);
    private final HumanPlayer mockHumanPlayer = new HumanPlayerImpl("Mock human player", mockGameMapManager.getPlayerSpawn());
    private final GameTurnManager mockGameTurnManager = new GameTurnManagerImpl(List.of(mockBotPlayer, mockHumanPlayer));
    private final GameDataImpl mockGameData = new GameDataImpl(
            mockHumanPlayer,
            mockBotPlayer,
            mockGameMapManager,
            mockGameTurnManager
        );

    /**
     * Cleanup files after each test.
     *
     * @throws IOException if file deletion fails
     */
    @AfterEach
    void cleanUp() throws IOException {
        Files.deleteIfExists(Paths.get(TEST_CUSTOM_PATH));
        Files.deleteIfExists(Paths.get(DEFAULT_PATH));
    }

    /**
     * Test loading game data from a custom path.
     */
    @Test
    void testLoadGameWithCustomPath() throws IOException, ClassNotFoundException {
        final GameSaver saver = new GameSaverImpl(mockGameData, TEST_CUSTOM_PATH);
        saver.saveGame();

        final GameLoaderImpl loader = new GameLoaderImpl(TEST_CUSTOM_PATH);
        final Optional<GameData> loadedData = loader.loadGame();

        verifyLoadedGameData(mockGameData, loadedData, MAP_WIDTH, MAP_HEIGHT);
    }

    /**
     * Test loading game data from the default path.
     */
    @Test
    void testLoadGameWithDefaultPath() throws IOException, ClassNotFoundException {
        final GameSaver saver = new GameSaverImpl(mockGameData, null);
        saver.saveGame();

        final GameLoader loader = new GameLoaderImpl();
        final Optional<GameData> loadedData = loader.loadGame();

        verifyLoadedGameData(mockGameData, loadedData, MAP_WIDTH, MAP_HEIGHT);
    }

    /**
     * Test loading game data when the file does not exist.
     */
    @Test
    void testLoadGameFileDoesNotExist() {
        final GameLoaderImpl loader = new GameLoaderImpl(TEST_CUSTOM_PATH);
        final Optional<GameData> loadedData = loader.loadGame();

        assertTrue(!loadedData.isPresent(), "Game data should not be loaded from a non-existent file.");
    }

    /**
     * Test loading game data from a corrupted file.
     */
    @Test
    void testLoadGameFromCorruptedFile() throws IOException {
        try (
            BufferedWriter corruptedFile = new BufferedWriter(
                new OutputStreamWriter(
                    new FileOutputStream(TEST_CUSTOM_PATH), StandardCharsets.UTF_8
                )
            )
        ) {
            corruptedFile.write("Corrupted_File.");
        }

        final GameLoaderImpl loader = new GameLoaderImpl(TEST_CUSTOM_PATH);
        final Optional<GameData> loadedData = loader.loadGame();

        assertTrue(!loadedData.isPresent(), "Game data should not be loaded from a corrupted file.");
    }

    /**
     * Helper method that verify that the LoadedGameData are the
     * same as the saved (expected) ones.
     * 
     * @param expected the saved data
     * @param actualOptional the loaded data
     * @param expectedWidth the expected width of the map
     * @param expectedHeight the expected Height of the map
     */
    private void verifyLoadedGameData(
        final GameData expected, 
        final Optional<GameData> actualOptional, 
        final int expectedWidth, 
        final int expectedHeight
    ) {
        // Verify that the data are present
        assertTrue(actualOptional.isPresent(), "Game data should be loaded successfully.");

        // Extract the actual GameData
        final GameData actual = actualOptional.get();

        // Verify the Players data
        assertAll("Verify player data and turns",
            () -> assertEquals(expected.getHumanData(), actual.getHumanData(), "PlayerData1 does not match"),
            () -> assertEquals(expected.getBotData(), actual.getBotData(), "PlayerData2 does not match"),
            () -> assertEquals(expected.getGameTurnManager().getCurrentPlayer(), 
                actual.getGameTurnManager().getCurrentPlayer(), 
                "CurrentPlayer does not match"
            ),
            () -> assertEquals(expected.getGameTurnManager().getCurrentPlayerIndex(), 
                actual.getGameTurnManager().getCurrentPlayerIndex(), 
                "CurrentPlayerIndex does not match"
            ),
            () -> assertEquals(expected.getGameTurnManager().getCurrentTurn(), 
                actual.getGameTurnManager().getCurrentTurn(), 
                "CurrentTurn does not match"
            )
        );

        // Verify the map data
        assertAll("Verify map data",
            () -> assertEquals(expected.getMapManager().getBotSpawn(), 
                actual.getMapManager().getBotSpawn(), 
                "BotSpawn does not match"
            ),
            () -> assertEquals(expected.getMapManager().getMapDimension(), 
                actual.getMapManager().getMapDimension(), 
                "MapDimension does not match"
            ),
            () -> assertEquals(expected.getMapManager().getPlayerSpawn(), 
                actual.getMapManager().getPlayerSpawn(), 
                "PlayerSpawn does not match"
            ),
            () -> assertEquals(expected.getMapManager().getPlayersCells(), 
                actual.getMapManager().getPlayersCells(), 
                "PlayersCells do not match"
            )
        );

        // Verify the dimensions of the map
        assertAll("Verify map dimensions",
            () -> assertEquals(expectedWidth, 
                (int) actual.getMapManager().getMapDimension().getWidth(), 
                "The map width does not match"
            ),
            () -> assertEquals(expectedHeight, 
                (int) actual.getMapManager().getMapDimension().getHeight(), 
                "The map height does not match"
            )
        );

        // Verify every cell
        final GameMap expectedMap = new GameMapImpl(
            expectedWidth, 
            expectedHeight, 
            MapManager.createMapFromStream(expectedWidth, expectedHeight, expected.getMapManager().getMapAsAStream())
        );
        final GameMap actualMap = new GameMapImpl(
            expectedWidth, 
            expectedHeight, 
            MapManager.createMapFromStream(expectedWidth, expectedHeight, actual.getMapManager().getMapAsAStream())
        );

        final Cell[][] expectedMatrix = expectedMap.getMap();
        final Cell[][] actualMatrix = actualMap.getMap();
        IntStream.range(0, MAP_HEIGHT).forEach(i ->
            IntStream.range(0, MAP_WIDTH).forEach(j ->
                assertEquals(
                    expectedMatrix[i][j], 
                    actualMatrix[i][j], 
                    "The cell at (" + i + "," + j + ") does not match"
                )
            )
        );
    }
}
