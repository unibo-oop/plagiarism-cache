package it.unibo.plantsfarm.model.menu.api;

import java.util.List;
import java.util.Map;

import it.unibo.plantsfarm.model.plant.PlantImpl;
import it.unibo.plantsfarm.model.plant.PlantType;

/**
 * Represents the current state of the game.
 */
public interface GameState {

    /**
     * Returns the list of plants contained in encyclopedia.
     *
     * @return The list of plants.
     */
    List<PlantImpl> getAllPlants();

    /**
     * Returns the list of edible plants contained in encyclopedia.
     *
     * @return The list of edible plants.
     */
    List<PlantImpl> getAllUnlockedEdiblePlants();

    /**
     * Returns the storage as a map.
     *
     * @return A map of plant types and their quantities.
     */
    Map<PlantType, Integer> getStorageContents();

    /**
     * Returns the shop requests as a Map.
     *
     * @return A map of plant types and their quantities.
     */
    Map<PlantType, Integer> getRequests();

    /**
     * Returns the shop instance.
     *
     * @return The shop model.
     */
    Shop getShop();

    /**
     * Returns the player's amount of coins.
     *
     * @return The current coin balance.
     */
    int getWallet();

    /**
     * Adds coins to the wallet.
     *
     * @param amount The amount to add.
     */
    void addCoins(int amount);

    /**
     * Spends coins from the wallet if sufficient funds exist.
     *
     * @param amount The amount to spend.
     * @return True if successful, false otherwise.
     */
    boolean spendCoins(int amount);

    /**
     * Adds harvested items to storage.
     *
     * @param type   The plant type.
     * @param amount The quantity.
     */
    void addHarvest(PlantType type, int amount);

    /**
     * Removes items from storage.
     *
     * @param type   The plant type.
     * @param amount The quantity.
     * @return True if successful.
     */
    boolean removeHarvest(PlantType type, int amount);

    /**
     * Saves the current state of the encyclopedia.
     */
    void saveEncyclopedia();

    /**
     * Resets the entire game state.
     */
    void resetGame();

}
