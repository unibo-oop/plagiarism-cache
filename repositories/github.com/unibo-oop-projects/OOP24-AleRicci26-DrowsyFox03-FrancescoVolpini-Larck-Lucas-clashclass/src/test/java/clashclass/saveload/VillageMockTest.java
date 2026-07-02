package clashclass.saveload;

import clashclass.commons.VectorInt2D;
import clashclass.ecs.GameObject;
import clashclass.elements.ComponentFactoryImpl;
import clashclass.elements.buildings.BattleBuildingFactoryImpl;
import clashclass.elements.buildings.BuildingFactoryMapper;
import clashclass.elements.buildings.PlayerBuildingFactoryImpl;
import clashclass.elements.buildings.VillageElementData;
import clashclass.resources.Player;
import clashclass.village.Village;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VillageMockTest {

    @TempDir
    Path tempDir;

    private VillageSaveLoadManager saveLoadManager;
    private FileWriter fileWriter;
    private VillageEncoder encoder;
    private PlayerVillageDecoderImpl playerDecoder;
    private BattleVillageDecoderImpl battleDecoder;
    private BuildingFactoryMapper<PlayerBuildingFactoryImpl> playerMapper;
    private BuildingFactoryMapper<BattleBuildingFactoryImpl> battleMapper;


    @BeforeEach
    void setUp() {
        fileWriter = new SimpleFileWriterImpl();
        encoder = new VillageEncoderImpl();

        PlayerBuildingFactoryImpl playerBuildingFactory = new PlayerBuildingFactoryImpl();
        BattleBuildingFactoryImpl battleBuildingFactory = new BattleBuildingFactoryImpl();

        playerDecoder = new PlayerVillageDecoderImpl();
        playerDecoder.setComponentFactory(new ComponentFactoryImpl());

        battleDecoder = new BattleVillageDecoderImpl();
        battleDecoder.setComponentFactory(new ComponentFactoryImpl());

        playerMapper = new BuildingFactoryMapper<>(playerBuildingFactory);
        battleMapper = new BuildingFactoryMapper<>(battleBuildingFactory);

        saveLoadManager = new VillageSaveLoadManager(
                encoder,
                playerDecoder,
                battleDecoder,
                fileWriter,
                tempDir
        );
    }

    @Test
    void testFileNotFound() {
        String nonExistentFile = "does_not_exist";

        // Test both loading methods
        assertThrows(IOException.class, () ->
                saveLoadManager.loadPlayerVillage(nonExistentFile));

        assertThrows(IOException.class, () ->
                saveLoadManager.loadBattleVillage(nonExistentFile));
    }

    @Test
    void testSaveLoadVillage() throws IOException {
        Village originalVillage = createTestVillage(playerMapper);
        String villageName = "default_village";

        // Save the village
        saveLoadManager.saveVillage(originalVillage, villageName);

        // Verify save file exists
        Path savePath = tempDir.resolve(villageName + ".csv");
        assertTrue(Files.exists(savePath));

        // Load and verify the village
        final var loadedObjects = saveLoadManager.loadPlayerVillage(villageName).getBuildings();
        assertEquals(originalVillage.getBuildings().size(), loadedObjects.size());
    }

    private Village createTestVillage(BuildingFactoryMapper<?> factoryMapper) {
        Player testPlayer = new Player();
        Village village = new Village(testPlayer);

        // Create buildings using the factory mapper and place them in the village
        GameObject archerTower = factoryMapper.getFactoryFor(VillageElementData.ARCHER_TOWER)
                .apply(new VectorInt2D(5, 5));
        GameObject goldStorage = factoryMapper.getFactoryFor(VillageElementData.GOLD_STORAGE)
                .apply(new VectorInt2D(6, 6));
        GameObject wall = factoryMapper.getFactoryFor(VillageElementData.WALL)
                .apply(new VectorInt2D(7, 7));
        GameObject elixirExtractor = factoryMapper.getFactoryFor(VillageElementData.ELIXIR_EXTRACTOR)
                .apply(new VectorInt2D(8, 8));

        village.placeBuilding(archerTower);
        village.placeBuilding(goldStorage);
        village.placeBuilding(wall);
        village.placeBuilding(elixirExtractor);

        return village;
    }
    @Test
    void testBattleVillageLoading() throws IOException {
        // Create some game objects
        Village originalVillage = createTestVillage(battleMapper);

        // Save the village
        String fileName = "campaign_village";
        saveLoadManager.saveVillage(originalVillage, fileName);

        // Load as battle village
        final var loadedObjects = saveLoadManager.loadBattleVillage(fileName).getBuildings();

        // Check that we have the same number of objects
        assertEquals(originalVillage.getBuildings().size(), loadedObjects.size());
    }
}
