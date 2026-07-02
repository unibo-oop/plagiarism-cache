package it.unibo.model.shop.impl;

import java.util.Map;

import it.unibo.model.shop.api.ShopItemStat;
import it.unibo.model.shop.api.ShopItemType;

/**
 * Implementation for skins that provide permenent upgrades.
 */
public class SkinItemImpl extends AbstractShopItem {

    /**
     * Constructor for SkinItemImpl with all required fields.
     * 
     * @param id          the id of the shop item
     * @param name        the name of the shop item
     * @param description the description of the shop item
     * @param price       the price of the shop item
     * @param stats       the stats boosted by the shop item
     */
    public SkinItemImpl(final String id, final String name, final String description, final int price,
            final Map<ShopItemStat, Double> stats) {
        super(id, name, description, price, ShopItemType.SKIN, stats);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getInitialDuration() {
        return 0;
    }

}
