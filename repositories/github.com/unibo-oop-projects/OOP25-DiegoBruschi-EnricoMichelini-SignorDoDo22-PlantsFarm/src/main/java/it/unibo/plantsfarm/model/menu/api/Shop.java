package it.unibo.plantsfarm.model.menu.api;

import java.util.Map;

import it.unibo.plantsfarm.model.menu.impl.GameStateImpl;
import it.unibo.plantsfarm.model.plant.PlantType;

/**
 * Interface representing the shop model of the game.
 */
public interface Shop {

    /**
     * Generates random sell requests based on unlocked edible plants.
     *
     * @param gameState The current state of the game.
     * @return A map of PlantType and the requested quantity.
     */
    Map<PlantType, Integer> generateRequests(GameStateImpl gameState);

    /**
     * Tries to sell the requested items and adds coins to the wallet.
     *
     * @param gameState The current state of the game.
     * @param requests The map of plants and quantities requested.
     * @return The amount of coins earned, or 0 if the transaction failed.
     */
    int sellProducts(GameStateImpl gameState, Map<PlantType, Integer> requests);

    /**
     * Tries to buy a mystery box to unlock a new plant.
     *
     * @param gameState The current state of the game.
     * @param cost      The cost of the item.
     * @return The unlocked PlantType, or null if transaction failed.
     */
    PlantType buyMysteryBox(GameStateImpl gameState, int cost);

    /**
     * Checks if all plants have been unlocked.
     *
     * @return True if there are no locked plants left.
     */
    boolean areAllPlantsUnlocked();

    /**
     * Resets the current active requests.
     */
    void resetRequests();

}
