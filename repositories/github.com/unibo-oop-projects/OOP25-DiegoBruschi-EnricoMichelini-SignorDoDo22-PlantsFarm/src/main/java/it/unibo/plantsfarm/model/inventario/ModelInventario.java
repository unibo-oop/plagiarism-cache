package it.unibo.plantsfarm.model.inventario;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import it.unibo.plantsfarm.model.items.api.ItemsFarm;
import it.unibo.plantsfarm.model.items.api.ItemsFarm.Tooltype;
import it.unibo.plantsfarm.model.plant.Rarity;

/**
 * Model of the inventory: stores the player's tools/items inside a map.
 * Every item can be upgraded (if the player is baseFarmer).
 *
 */
public final class ModelInventario {

    private static final String TYPE = "type";
    private final Map<Tooltype, ItemsFarm> inventario;

    /**
     * Creates an empty inventory.
     */
    public ModelInventario() {
        this.inventario = new LinkedHashMap<>(Map.of());
    }

    /**
     * Creates an inventory initialized with the given items.
     *
     * @param initialItems initial items to add
     */
    public ModelInventario(final Map<Tooltype, ItemsFarm> initialItems) {
        this();
        Objects.requireNonNull(initialItems, "initialItems");
        initialItems.forEach(this::putItem);
    }

    /**
     * Adds/Replaces an item in the inventory.
     *
     * @param type tool type key
     * @param item item instance
     */
    public void putItem(final Tooltype type, final ItemsFarm item) {
        Objects.requireNonNull(type, TYPE);
        inventario.put(type, item);
    }

    /**
     * Returns a read-only snapshot of the inventory map.
     * External code cannot modify the internal map.
     *
     * @return unmodifiable copy of the inventory
     */
    public Map<Tooltype, ItemsFarm> getInventorySnapshot() {
        return Map.copyOf(inventario);
    }

    /**
     * For see if a item is upgredable.
     *
     * @param tool type of tool
     *
     * @return true if the item is upgredable or false if it is not
     */
    public boolean isUpgredableItem(final Tooltype tool) {
        return getItem(tool).getExperience() >= getItem(tool).getExperienceForLevel()
               && getItem(tool).getLevel() <= getItem(tool).getMaxLevel();
    }

    /**
     * Returns the item for a given tool type, if present.
     *
     * @param type tool type
     * @return optional item
     */
    public ItemsFarm getItem(final Tooltype type) {
        Objects.requireNonNull(type, TYPE);
        return inventario.get(type);
    }

    /**
     * Upgrades the selected tool/item (model-side operation).
     *
     * @param type tool to upgrade
     * @return true if the item existed and was upgraded
     */
    public boolean upgrade(final Tooltype type) {
        Objects.requireNonNull(type, TYPE);
        final ItemsFarm item = inventario.get(type);
        if (item == null) {
            return false;
        }

        item.upgrade();
        return true;
    }

    /**
     * Uses the specified tool if its rarity matches the plant rarity.
     *
     * @param rarityPlant if the iteme has the same level of plant, the item can be used.
     * @param tool  tool to be  used.
     * @return {@code true} if the item was successfully used, {@code false} otherwise
     */
    public boolean useItem(final Tooltype tool, final Rarity rarityPlant) {
        if (rarityPlant != null && canBeUsed(tool, rarityPlant)) {
            this.inventario.get(tool).useItem();
            return true;
        }
        return false;
    }

    /**
     * Function for apply an upgrade to the player.
     *
     */
    public void applyUpgrade() {
        for (final Tooltype tool : Tooltype.values()) {
            inventario.get(tool).useItem();
        }
    }

    /**
     * Loads the given level into the specified tool item.
     * This method is typically used when restoring the inventory state
     * from a saved game.
     *
     * @param tool the tool whose level has to be loaded
     * @param level the level to assign to the tool
     * @throws IllegalArgumentException if {@code level} is negative
     * @throws NullPointerException if {@code tool} is {@code null}
     */
    public void loadItem(final Tooltype tool, final int level) {
        final ItemsFarm itemFarm = inventario.get(tool);
        itemFarm.setLevel(level);
    }

    private boolean canBeUsed(final Tooltype tool, final Rarity planRarity) {
        return inventario.get(tool).getLevelBaseOnRarity(planRarity) <= inventario.get(tool).getLevel();
    }
}
