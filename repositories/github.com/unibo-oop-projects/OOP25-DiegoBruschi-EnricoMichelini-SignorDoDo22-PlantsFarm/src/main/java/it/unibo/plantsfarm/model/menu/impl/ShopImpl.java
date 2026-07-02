package it.unibo.plantsfarm.model.menu.impl;

import it.unibo.plantsfarm.model.menu.api.Shop;
import it.unibo.plantsfarm.model.plant.PlantImpl;
import it.unibo.plantsfarm.model.plant.PlantType;
import it.unibo.plantsfarm.model.plant.PlantRegistry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Manages the business logic for the Shop.
 */
public final class ShopImpl implements Shop {

    private static final int MAX_REQUESTS = 3;
    private static final int MIN_MULTIPLIER = 3;
    private static final int MAX_MULTIPLIER = 7;

    private final Random random;
    private Map<PlantType, Integer> activeRequests;

    /**
     * Creates a new Shop.
     */
    public ShopImpl() {
        this.random = new Random();
    }

    /**
     * Generates random sell requests based on unlocked edible plants.
     *
     * @param gameState The current state of the game.
     * @return A map of PlantType and the requested quantity.
     */
    @Override
    public Map<PlantType, Integer> generateRequests(final GameStateImpl gameState) {
        if (this.activeRequests != null && !this.activeRequests.isEmpty()) {
            return Collections.unmodifiableMap(this.activeRequests);
        }

        final List<PlantImpl> unlocked = gameState.getAllUnlockedEdiblePlants();
        final Map<PlantType, Integer> requests = new HashMap<>();

        if (unlocked.isEmpty()) {
            return requests;
        }

        final List<PlantImpl> shuffled = new ArrayList<>(unlocked);
        Collections.shuffle(shuffled);
        final List<PlantImpl> selected = shuffled.subList(0, Math.min(MAX_REQUESTS, shuffled.size()));

        for (final PlantImpl plant : selected) {
            final int baseHarvest = plant.getType().generateHarvest();
            final int multiplier = MIN_MULTIPLIER + random.nextInt(MAX_MULTIPLIER - MIN_MULTIPLIER + 1);
            final int totalQuantity = baseHarvest * multiplier;

            requests.put(plant.getType(), totalQuantity);
        }

        this.activeRequests = requests;
        return Collections.unmodifiableMap(requests);
    }

    /**
     * Tries to sell the requested items and adds coins to the wallet.
     *
     * @param gameState The current state of the game.
     * @param requests The map of plants and quantities requested.
     * @return The amount of coins earned, or 0 if the transaction failed.
     */
    @Override
    public int sellProducts(final GameStateImpl gameState, final Map<PlantType, Integer> requests) {
        final Map<PlantType, Integer> storage = gameState.getStorageContents();

        for (final Map.Entry<PlantType, Integer> entry : requests.entrySet()) {
            final PlantType type = entry.getKey();
            final int requiredQty = entry.getValue();
            if (storage.getOrDefault(type, 0) < requiredQty) {
                return 0;
            }
        }

        int totalEarnings = 0;

        for (final Map.Entry<PlantType, Integer> entry : requests.entrySet()) {
            final PlantType type = entry.getKey();
            final int quantity = entry.getValue();

            gameState.removeHarvest(type, quantity);
            totalEarnings += type.getSellPrice() * quantity;
        }

        gameState.addCoins(totalEarnings);
        this.activeRequests = null;

        return totalEarnings;
    }

    /**
     * Tries to buy a mystery box to unlock a new plant.
     *
     * @param gameState The current state of the game.
     * @param cost      The cost of the item.
     * @return The unlocked PlantType, or null if transaction failed.
     */
    @Override
    public PlantType buyMysteryBox(final GameStateImpl gameState, final int cost) {
        final List<PlantType> lockedPlants = PlantRegistry.getAll().stream()
            .filter(p -> !p.isDiscovered())
            .collect(Collectors.toList());

        if (lockedPlants.isEmpty()) {
            return null;
        }

        if (!gameState.spendCoins(cost)) {
            return null;
        }

        final PlantType newUnlock = lockedPlants.get(random.nextInt(lockedPlants.size()));
        newUnlock.unlock();

        return newUnlock;
    }

    /**
     * Checks if all plants have been unlocked.
     *
     * @return True if there are no locked plants left.
     */
    @Override
    public boolean areAllPlantsUnlocked() {
        return PlantRegistry.getAll().stream().allMatch(PlantType::isDiscovered);
    }

    /**
     * Resets the current active requests.
     */
    @Override
    public void resetRequests() {
        this.activeRequests = null;
    }
}

