package it.unibo.plantsfarm.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

import it.unibo.plantsfarm.model.menu.api.Shop;
import it.unibo.plantsfarm.model.menu.impl.GameStateImpl;
import it.unibo.plantsfarm.model.menu.impl.ShopImpl;
import it.unibo.plantsfarm.model.plant.PlantImpl;
import it.unibo.plantsfarm.model.plant.PlantRegistry;
import it.unibo.plantsfarm.model.plant.PlantType;

final class ShopTest {

    private static final int INITIAL_COINS = 500;
    private static final int CARROTS_QUANTITY_TO_ADD = 50;
    private static final int CARROT_QUANTITY_TO_SELL = 10;
    private static final int MYSTERY_BOX_COST = 50;
    private static final int STORAGE_DEFAULT = 0;
    private static final int BIG_QUANTITY = 5000;

    private final Shop shop = new ShopImpl();
    private final PlantImpl carrot = new PlantImpl(PlantRegistry.CARROT); 
    private final GameStateImpl gameState = new GameStateImpl(List.of(carrot));

    /**
     * Test that the shop generates valid sell requests based on unlocked plants.
     */
    @Test
    void testGenerateRequests() {
        gameState.resetGame();

        PlantRegistry.CARROT.unlock();

        final Map<PlantType, Integer> requests = shop.generateRequests(gameState);

        assertNotNull(requests);
        assertFalse(requests.isEmpty());
        assertTrue(requests.containsKey(PlantRegistry.CARROT));
    }

    /**
     * Test that selling products correctly increases the wallet and decreases storage.
     */
    @Test
    void testSellProductsSuccess() {
        gameState.resetGame();

        PlantRegistry.CARROT.unlock();
        gameState.addHarvest(PlantRegistry.CARROT, CARROTS_QUANTITY_TO_ADD);

        final Map<PlantType, Integer> request = new HashMap<>();
        request.put(PlantRegistry.CARROT, CARROT_QUANTITY_TO_SELL);

        final int earnings = shop.sellProducts(gameState, request);
        final int expectedEarnings = CARROT_QUANTITY_TO_SELL * PlantRegistry.CARROT.getSellPrice();

        assertTrue(earnings > 0);
        assertEquals(expectedEarnings, earnings);

        assertEquals(INITIAL_COINS + expectedEarnings, gameState.getWallet());

        final int expectedQuantity = STORAGE_DEFAULT + CARROTS_QUANTITY_TO_ADD - CARROT_QUANTITY_TO_SELL;
        assertEquals(expectedQuantity, gameState.getStorageContents().get(PlantRegistry.CARROT));
    }

    /**
     * Test that the shop doesn't sell if there aren't enough items in storage.
     */
    @Test
    void testSellProductsNotEnoughItems() {
        gameState.resetGame();

        PlantRegistry.CARROT.unlock();
        final Map<PlantType, Integer> request = new HashMap<>();
        request.put(PlantRegistry.CARROT, STORAGE_DEFAULT + BIG_QUANTITY);

        final int earnings = shop.sellProducts(gameState, request);

        assertEquals(0, earnings);
        assertEquals(INITIAL_COINS, gameState.getWallet());
    }

    /**
     * Test that buying a mystery box successfully unlocks a new plant and remove coins.
     */
    @Test
    void testMysteryBoxSuccess() {
        gameState.resetGame();

        final PlantType unlocked = shop.buyMysteryBox(gameState, MYSTERY_BOX_COST);

        if (unlocked != null) {
            assertTrue(unlocked.isDiscovered());
            assertEquals(INITIAL_COINS - MYSTERY_BOX_COST, gameState.getWallet());
        }
    }

    /**
     * Test that the mystery box cannot be bought without sufficient funds.
     */
    @Test
    void testMysteryBoxNoMoney() {
        gameState.resetGame();

        gameState.spendCoins(INITIAL_COINS);

        final PlantType unlocked = shop.buyMysteryBox(gameState, MYSTERY_BOX_COST);

        assertEquals(null, unlocked);
    }
}
