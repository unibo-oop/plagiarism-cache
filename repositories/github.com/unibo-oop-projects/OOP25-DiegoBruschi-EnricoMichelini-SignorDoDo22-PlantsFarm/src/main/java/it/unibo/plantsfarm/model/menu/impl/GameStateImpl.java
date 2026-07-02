package it.unibo.plantsfarm.model.menu.impl;

import it.unibo.plantsfarm.controller.player.ManagerSavingPlayer;
import it.unibo.plantsfarm.model.garden.SoilSaving;
import it.unibo.plantsfarm.model.menu.api.Coin;
import it.unibo.plantsfarm.model.menu.api.Encyclopedia;
import it.unibo.plantsfarm.model.menu.api.GameState;
import it.unibo.plantsfarm.model.menu.api.Shop;
import it.unibo.plantsfarm.model.menu.api.Storage;
import it.unibo.plantsfarm.model.plant.PlantImpl;
import it.unibo.plantsfarm.model.plant.PlantType;
import java.util.List;
import java.util.Map;

/**
 * Represents the current state of the game.
 */
public final class GameStateImpl implements GameState {

    private static final int INITIAL_COINS = 250;

    private final Encyclopedia encyclopedia;
    private final Storage storage;
    private final Shop shop;
    private final Coin wallet;
    private final SoilSaving soils;
    private final ManagerSavingPlayer managerSavingPlayer;

    /**
     * Constructs a new GameState initialized with a list of plants.
     *
     * @param plants The list of plants to load into the internal encyclopedia.
     */
    public GameStateImpl(final List<PlantImpl> plants) {
        this.soils = new SoilSaving();
        this.managerSavingPlayer = new ManagerSavingPlayer();
        this.encyclopedia = new EncyclopediaImpl();
        this.storage = new StorageImpl();
        this.shop = new ShopImpl();
        this.wallet = new CoinImpl(INITIAL_COINS);

        for (final PlantImpl p : plants) {
            this.encyclopedia.addPlant(p);
        }
    }

    /**
     * Returns the list of plants contained in encyclopedia.
     *
     * @return The list of plants.
     */
    @Override
    public List<PlantImpl> getAllPlants() {
        return encyclopedia.getPlants();
    }

    /**
     * Returns the list of edible plants contained in encyclopedia.
     *
     * @return The list of edible plants.
     */
    @Override
    public List<PlantImpl> getAllUnlockedEdiblePlants() {
        return encyclopedia.getUnlockedEdiblePlants();
    }

    /**
     * Returns the storage as a map.
     *
     * @return A map of plant types and their quantities.
     */
    @Override
    public Map<PlantType, Integer> getStorageContents() {
        return this.storage.getAllItems();
    }

    /**
     * Returns the shop requests as a Map.
     *
     * @return A map of plant types and their quantities.
     */
    @Override
    public Map<PlantType, Integer> getRequests() {
        return this.shop.generateRequests(this);
    }

    /**
     * Returns the shop instance.
     *
     * @return The shop model.
     */
    @Override
    public Shop getShop() {
        return this.shop;
    }

    /**
     * Returns the player's amount of coins.
     *
     * @return The current coin balance.
     */
    @Override
    public int getWallet() {
        return this.wallet.getValue();
    }

    /**
     * Adds coins to the wallet.
     *
     * @param amount The amount to add.
     */
    @Override
    public void addCoins(final int amount) {
        this.wallet.addAmount(amount);
    }

    /**
     * Spends coins from the wallet if sufficient funds exist.
     *
     * @param amount The amount to spend.
     * @return True if successful, false otherwise.
     */
    @Override
    public boolean spendCoins(final int amount) {
        return this.wallet.removeAmount(amount);
    }

    /**
     * Adds harvested items to storage.
     *
     * @param type   The plant type.
     * @param amount The quantity.
     */
    @Override
    public void addHarvest(final PlantType type, final int amount) {
        this.storage.addItem(type, amount);
    }

    /**
     * Removes items from storage.
     *
     * @param type   The plant type.
     * @param amount The quantity.
     * @return True if successful.
     */
    @Override
    public boolean removeHarvest(final PlantType type, final int amount) {
        return this.storage.removeItem(type, amount);
    }

    /**
     * Saves the current state of the encyclopedia.
     */
    @Override
    public void saveEncyclopedia() {
        this.encyclopedia.save();
    }

    /**
     * Resets the entire game state.
     */
    @Override
    public void resetGame() {
        this.storage.reset();
        this.wallet.reset();
        this.encyclopedia.reset();
        this.soils.reset();
        this.managerSavingPlayer.resetSaving();
    }
}
