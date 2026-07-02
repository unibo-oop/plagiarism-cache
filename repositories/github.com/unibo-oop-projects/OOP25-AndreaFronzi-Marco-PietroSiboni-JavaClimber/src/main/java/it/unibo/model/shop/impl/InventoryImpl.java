package it.unibo.model.shop.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import it.unibo.model.persistence.api.SaveState;
import it.unibo.model.shop.api.Inventory;
import it.unibo.model.shop.api.ShopItem;
import it.unibo.model.shop.api.ShopItemFactory;
import it.unibo.model.shop.api.ShopItemStat;

/**
 * Implementation of {@link Inventory} interface.
 */
public class InventoryImpl implements Inventory {

    private static final String DEFAULT_SKIN = "s_basic";
    private final ShopItemFactory factory;
    private final Set<String> ownedItems;
    private final Map<String, Integer> consumables;
    private String selectedSkin;
    private int selectedJumpLevel;
    private int selectedSpeedLevel;
    private final Set<String> activeConsumables;
    private int totalCoins;

    /**
     * Initializes internal collections for tracking owned items, active
     * consumables, and sets the default skin.
     * 
     * @param factory the factory for items
     */
    public InventoryImpl(final ShopItemFactory factory) {
        this.factory = factory;
        this.ownedItems = new HashSet<>();
        this.consumables = new HashMap<>();
        this.selectedSkin = DEFAULT_SKIN;
        this.ownedItems.add(DEFAULT_SKIN);
        this.activeConsumables = new HashSet<>();
        this.totalCoins = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addItem(final String itemId) {
        this.ownedItems.add(itemId);

        factory.getItemById(itemId).ifPresent(item -> {
            switch (item.getType()) {
                case SKIN:
                    this.selectedSkin = itemId;
                    break;
                case PERMANENT_UPGRADE:
                    final int level = extractLevel(itemId);
                    if (itemId.startsWith("pp_jump")) {
                        this.selectedJumpLevel = level;
                    } else if (itemId.startsWith("pp_speed")) {
                        this.selectedSpeedLevel = level;
                    }
                    break;
                default:
                    break;
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasItem(final String itemId) {
        return this.ownedItems.contains(itemId) || this.consumables.containsKey(itemId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<String> getOwnedItems() {
        return Set.copyOf(this.ownedItems);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void equipSkin(final String itemId) {
        if (this.ownedItems.contains(itemId)) {
            this.selectedSkin = itemId;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deselectSkin() {
        this.selectedSkin = DEFAULT_SKIN;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSelectedSkin() {
        return this.selectedSkin;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addConsumable(final String itemId, final int matchesDuration) {
        this.consumables.put(itemId, matchesDuration);
        toggleConsumable(itemId, this.factory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Integer> getConsumablesStatus() {
        return Map.copyOf(this.consumables);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateConsumables() {
        final Iterator<Map.Entry<String, Integer>> it = this.consumables.entrySet().iterator();
        while (it.hasNext()) {
            final Map.Entry<String, Integer> entry = it.next();
            final String id = entry.getKey();
            if (activeConsumables.contains(id)) {
                final int remaining = entry.getValue() - 1;
                if (remaining <= 0) {
                    it.remove();
                } else {
                    entry.setValue(remaining);
                }
            }
        }
        activeConsumables.removeIf(id -> !consumables.containsKey(id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadState(final SaveState state) {
        this.activeConsumables.clear();
        this.ownedItems.clear();
        this.consumables.clear();

        this.ownedItems.addAll(state.getOwnedItems());
        this.consumables.putAll(state.getConsumables());
        this.ownedItems.add(DEFAULT_SKIN);
        this.selectedJumpLevel = state.getSelectedJumpLevel();
        this.selectedSpeedLevel = state.getSelectedSpeedLevel();

        if (state.getSelectedSkin() != null && !state.getSelectedSkin().isEmpty()) {
            this.selectedSkin = state.getSelectedSkin();
        } else {
            this.selectedSkin = DEFAULT_SKIN;
        }
        this.totalCoins = state.getCoins();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSelectedJumpLevel() {
        return this.selectedJumpLevel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSelectedJumpLevel(final int level) {
        this.selectedJumpLevel = level;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSelectedSpeedLevel() {
        return this.selectedSpeedLevel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSelectedSpeedLevel(final int level) {
        this.selectedSpeedLevel = level;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void toggleConsumable(final String itemId, final ShopItemFactory itemfactory) {
        if (activeConsumables.contains(itemId)) {
            activeConsumables.remove(itemId);
            return;
        }
        final ShopItem item = itemfactory.getItemById(itemId).orElseThrow();
        final ShopItemStat statCategory = item.getStats().keySet().iterator().next();
        activeConsumables.removeIf(id -> {
            final ShopItem activeItem = itemfactory.getItemById(id).orElse(null);
            return activeItem != null && activeItem.getStats().containsKey(statCategory);
        });
        activeConsumables.add(itemId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<String> getActiveConsumables() {
        return Set.copyOf(this.activeConsumables);
    }

    /**
     * Private method to extract the number of the power up from the id of the item.
     * 
     * @param itemId id of the item
     * @return number of current level
     */
    private int extractLevel(final String itemId) {
        return Integer.parseInt(itemId.substring(itemId.lastIndexOf('_') + 1));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getTotalCoins() {
        return this.totalCoins;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addCoins(final int amount) {
        if (amount > 0) {
            this.totalCoins += amount;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean spendCoins(final int amount) {
        if (amount > 0 && this.totalCoins >= amount) {
            this.totalCoins -= amount;
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTotalCoins(final int coins) {
        this.totalCoins = coins;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShopItemFactory getFactory() {
        return this.factory;
    }

}
