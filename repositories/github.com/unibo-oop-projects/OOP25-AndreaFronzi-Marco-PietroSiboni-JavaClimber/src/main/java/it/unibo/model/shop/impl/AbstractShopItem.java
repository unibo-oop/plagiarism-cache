package it.unibo.model.shop.impl;

import java.util.Collections;
import java.util.Map;

import it.unibo.model.shop.api.ShopItem;
import it.unibo.model.shop.api.ShopItemStat;
import it.unibo.model.shop.api.ShopItemType;

/**
 * Abstract implementation of {@link ShopItem} interface.
 */
public abstract class AbstractShopItem implements ShopItem {

    private final String id;
    private final String name;
    private final String description;
    private final int price;
    private final ShopItemType type;
    private final Map<ShopItemStat, Double> stats;

    /**
     * Constructor for AbstractShopItem with all required fields.
     * 
     * @param id          the unique identifier of the shop item
     * @param name        the name of the shop item
     * @param description the description of the shop item
     * @param price       the price of the shop item
     * @param type        the type of the shop item
     * @param stats       the statistics boosted by the shop item
     */
    public AbstractShopItem(final String id, final String name, final String description, final int price,
            final ShopItemType type,
            final Map<ShopItemStat, Double> stats) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.type = type;
        this.stats = Collections.unmodifiableMap(stats);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getId() {
        return this.id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPrice() {
        return this.price;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShopItemType getType() {
        return this.type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<ShopItemStat, Double> getStats() {
        return this.stats;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract int getInitialDuration();

}
