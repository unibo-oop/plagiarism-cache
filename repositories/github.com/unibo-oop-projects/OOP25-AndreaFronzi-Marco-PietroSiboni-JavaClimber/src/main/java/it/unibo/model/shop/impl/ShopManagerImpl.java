package it.unibo.model.shop.impl;

import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.model.score.api.ScoreManager;
import it.unibo.model.shop.api.Inventory;
import it.unibo.model.shop.api.ShopItem;
import it.unibo.model.shop.api.ShopItemFactory;
import it.unibo.model.shop.api.ShopItemType;
import it.unibo.model.shop.api.ShopManager;

/**
 * Implementation of {@link ScoreManager} interface.
 */
public class ShopManagerImpl implements ShopManager {

    private final ShopItemFactory itemFactory;
    private final Inventory inventory;

    /**
     * Constructor for ShopManagerImpl with required factory and inventory.
     * 
     * @param itemFactory the factory for shop items
     * @param inventory   the inventory to manage purchases and ownership
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "The ShopManager is designed"
            + "to directly manage the Inventory instance.")
    public ShopManagerImpl(final ShopItemFactory itemFactory, final Inventory inventory) {
        this.itemFactory = itemFactory;
        this.inventory = inventory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ShopItem> getCatalog() {
        return this.itemFactory.getAllItems();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean buyItem(final ShopItem item) {
        if (canBuyItem(item)) {
            final boolean transactionSuccess = inventory.spendCoins(item.getPrice());
            if (!transactionSuccess) {
                return false;
            }

            switch (item.getType()) {
                case SKIN:
                    inventory.addItem(item.getId());
                    inventory.equipSkin(item.getId());
                    break;
                case PERMANENT_UPGRADE:
                    inventory.addItem(item.getId());
                    break;
                case TEMPORARY_UPGRADE:
                    inventory.addConsumable(item.getId(), item.getInitialDuration());
                    break;
            }
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAlreadyOwned(final ShopItem item) {
        return inventory.hasItem(item.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canBuyItem(final ShopItem item) {
        final boolean hasEnough = inventory.getTotalCoins() >= item.getPrice();

        if (item.getType() == ShopItemType.SKIN || item.getType() == ShopItemType.PERMANENT_UPGRADE) {
            return hasEnough && !isAlreadyOwned(item)
                    && (item.getType() != ShopItemType.PERMANENT_UPGRADE || checkSequentialPermanentUpgrade(item));
        }

        return hasEnough;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "The ShopManager is designed"
            + "to directly manage the Inventory instance")
    @Override
    public Inventory getInventory() {
        return this.inventory;
    }

    /**
     * Check if we're following the right sequence of purchasing of progressive Id
     * for permanent power ups.
     * 
     * @param item the object to check
     * @return true if the sequence is correct, false otherwise
     */
    private boolean checkSequentialPermanentUpgrade(final ShopItem item) {
        final String id = item.getId();
        final int lastUnderscore = id.lastIndexOf('_');
        if (lastUnderscore == -1) {
            return true;
        }
        try {
            final String prefix = id.substring(0, lastUnderscore + 1);
            final String levelPart = id.substring(lastUnderscore + 1);
            final int level = Integer.parseInt(levelPart);
            if (level > 1) {
                final String prevLevel = prefix + (level - 1);
                return inventory.hasItem(prevLevel);
            }
        } catch (final NumberFormatException e) {
            return true;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ShopItem> getSkins() {
        return this.itemFactory.getSkins();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ShopItem> getPermanentUpgrades() {
        return this.itemFactory.getPowerUpsPermanent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ShopItem> getTemporaryUpgrades() {
        return this.itemFactory.getPowerUpsTemporary();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCoins() {
        return inventory.getTotalCoins();
    }

}
