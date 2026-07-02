package clashclass.shop;

import clashclass.resources.ResourceType;


import java.util.List;
import java.util.stream.Collectors;

/**
 * An implementation of ShopManager.
 */
public class ShopManagerImpl implements ShopManager {
    private final List<ShopItem> shopItems;

    /**
     * Construct ShopManagerImpl.
     *
     * @param shopItems list of items inside the shop
     */
        public ShopManagerImpl(final List<ShopItem> shopItems) {
        this.shopItems = shopItems;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getBalance(final ShopItem shopItem) {
        return shopItem.getResourceManager().getCurrentValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canAfford(final ShopItem shopItem) {
        return shopItem.getPrice() <= this.getBalance(shopItem);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void buyItem(final ShopItem shopItem) {
        if (this.canAfford(shopItem)) {
            shopItem.getResourceManager().decrease(shopItem.getPrice());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ShopItem> findItemsByResourceType(final ResourceType type) {
        return shopItems.stream()
                .filter(item -> item.getResourceType() == type)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
        public List<ShopItem> getShopItems() {
        return this.shopItems;
    }
}
