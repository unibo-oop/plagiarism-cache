package it.unibo.model.shop.impl;

import java.util.Map;

import it.unibo.model.shop.api.ShopItemStat;
import it.unibo.model.shop.api.ShopItemType;

/**
 * Implementation for stat upgrades, permanent or consumable.
 */
public class PowerUpItemImpl extends AbstractShopItem {

    private final int initialDuration;

    /**
     * Constructor for PowerUpItemImpl with all required fields.
     * 
     * @param id          the id of the shop item
     * @param name        the name of the shop item
     * @param description the description of the shop item
     * @param price       the price of the shop item
     * @param type        the type of the shop item
     * @param stats       the stats boosted by the shop item
     * @param duration    the initial duration of the item in matches (0 for
     *                    permanent items)
     */
    public PowerUpItemImpl(final String id, final String name, final String description, final int price,
            final ShopItemType type,
            final Map<ShopItemStat, Double> stats, final int duration) {
        super(id, name, description, price, type, stats);
        this.initialDuration = duration;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getInitialDuration() {
        return this.initialDuration;
    }

}
