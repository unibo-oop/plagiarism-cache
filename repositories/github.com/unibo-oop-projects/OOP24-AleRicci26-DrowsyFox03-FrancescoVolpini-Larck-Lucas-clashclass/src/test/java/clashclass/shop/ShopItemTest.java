package clashclass.shop;

import clashclass.elements.buildings.VillageElementData;
import clashclass.resources.ResourceManager;
import clashclass.resources.ResourceType;
import clashclass.resources.Player;

import java.util.EnumMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ShopItemTest {

    private Player mockPlayer;
    private ResourceManager mockResourceManager;
    private ShopItem item;

    @BeforeEach
    void setUp() {
        EnumMap<ResourceType, ResourceManager> resources = new EnumMap<>(ResourceType.class);
        resources.put(ResourceType.GOLD, mockResourceManager);

        mockPlayer = new Player() { };
        mockResourceManager = mockPlayer.getPlayerResources().get(ResourceType.GOLD);
        item = new ShopItemImpl(VillageElementData.WALL, ResourceType.GOLD, 50.0, mockPlayer);
    }

    @Test
    void TestGetResourceManager () {
        assertSame(mockResourceManager, item.getResourceManager());
    }

    @Test
    void TestGetPrice () {
        assertEquals(50.0, item.getPrice());
    }

    @Test
    void TestGetResourceType () {
        assertEquals(ResourceType.GOLD, item.getResourceType());
    }
}
