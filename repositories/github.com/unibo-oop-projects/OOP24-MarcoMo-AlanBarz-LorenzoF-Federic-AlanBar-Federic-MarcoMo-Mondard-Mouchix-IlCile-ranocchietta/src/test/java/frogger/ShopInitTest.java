package frogger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import frogger.controller.GameController;
import frogger.controller.GameControllerImpl;
import frogger.controller.ShopController;
import frogger.model.implementations.PurchasableObjectFactoryImpl;
import frogger.model.implementations.Skin;
import frogger.model.interfaces.PurchasableObjectFactory;
import frogger.view.GameScene;

class ShopInitTest {

    private static final String FILE_NAME = "/shop.txt";
    private static final GameController GAME_CONTROLLER = new GameControllerImpl();
    private ShopController shopController;
    private PurchasableObjectFactory factoryPurchasable;

    @BeforeEach
    void setUp() {
        final ShopController shopController = new ShopController(GAME_CONTROLLER);
        shopController.init(new GameScene());
        this.factoryPurchasable = new PurchasableObjectFactoryImpl();
        shopController.shopInit();
        this.shopController = shopController;
    }

    @Test
    void testShopInit() {
        try (BufferedReader r = new BufferedReader(
                new InputStreamReader(getClass().getResourceAsStream(FILE_NAME), java.nio.charset.StandardCharsets.UTF_8))) {
            final String line = r.readLine();
            assertEquals("Skin 1 ranocchietta.png true", line);
            final String[] values = line.split(" ");
            assertEquals("Skin", values[0]);
            assertEquals(1, Integer.parseInt(values[1]));
            assertEquals("ranocchietta.png", values[2]);
            assertTrue(Boolean.parseBoolean(values[3]));

            final Skin skin = this.factoryPurchasable.createSkin(
                Integer.parseInt(values[1]),
                values[2],
                Boolean.parseBoolean(values[3])
            );

            assertEquals(1, skin.getPrize());
            assertTrue(skin.isAvailable());

            GAME_CONTROLLER.setCoins(10);
            assertEquals(10, GAME_CONTROLLER.getCoins());
        } catch (final IOException e) {
            java.util.logging.Logger.getLogger(
                ShopInitTest.class.getName()).log(java.util.logging.Level.SEVERE, "Error reading shop.txt", e);
        }
    }

    @Test
    void testShopPersistence() {
        final var objects = shopController.getPurchasableObject();
        final Skin skin = (Skin) objects.get(0);
        skin.setAvailable(false);

        shopController.shopInit();
        final var reloadedObjects = shopController.getPurchasableObject();
        final Skin reloadedSkin = (Skin) reloadedObjects.get(0);
        assertFalse(reloadedSkin.isAvailable());
    }
}
