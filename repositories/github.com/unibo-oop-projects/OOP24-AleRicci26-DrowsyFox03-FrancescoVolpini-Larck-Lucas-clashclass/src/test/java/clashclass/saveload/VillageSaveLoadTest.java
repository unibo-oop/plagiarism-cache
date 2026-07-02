package clashclass.saveload;

import clashclass.commons.BuildingTypeComponent;
import clashclass.elements.ComponentFactoryImpl;
import clashclass.elements.buildings.VillageElementData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

class VillageSaveLoadTest {

    private PlayerVillageDecoderImpl playerDecoder;
    private BattleVillageDecoderImpl battleDecoder;

    @BeforeEach
    void setUp() {
        playerDecoder = new PlayerVillageDecoderImpl();
        playerDecoder.setComponentFactory(new ComponentFactoryImpl());
        
        battleDecoder = new BattleVillageDecoderImpl();
        battleDecoder.setComponentFactory(new ComponentFactoryImpl());
    }
    @Test
    void loadPlayerVillageFromResources() throws IOException {
        Path resourcePath = Path.of("src/main/resources/Villages-Data/player-village.csv");
        String csv = Files.readString(resourcePath);

        final var objs = playerDecoder.decode(csv).getBuildings();

        assertFalse(objs.isEmpty(), "Il villaggio caricato dal file non deve essere vuoto");

        long townHallCount = objs.stream()
                .filter(go -> go.getComponentOfType(BuildingTypeComponent.class)
                        .map(BuildingTypeComponent::getBuildingType)
                        .filter(type -> type == VillageElementData.TOWN_HALL)
                        .isPresent())
                .count();

        assertTrue(townHallCount >= 1, "Almeno un Town Hall deve essere presente nel villaggio caricato");
    }

    @Test
    void loadBattleVillageFromResources() throws IOException {
        Path resourcePath = Path.of("src/main/resources/Villages-Data/battle-village.csv");
        String csvContent = Files.readString(resourcePath);

        final var village = battleDecoder.decode(csvContent);
        final var buildings = village.getBuildings();

        assertFalse(buildings.isEmpty(),
                "Il villaggio di battaglia caricato dal file non deve essere vuoto");
    }

}
