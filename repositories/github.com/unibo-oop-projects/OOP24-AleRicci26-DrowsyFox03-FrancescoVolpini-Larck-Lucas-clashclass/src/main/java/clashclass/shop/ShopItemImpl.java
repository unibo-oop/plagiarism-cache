package clashclass.shop;

import clashclass.elements.buildings.VillageElementData;
import clashclass.resources.Player;
import clashclass.resources.ResourceType;
import clashclass.resources.ResourceManager;


/**
 * Represents a ShopItem implementation.
 */
public class ShopItemImpl implements ShopItem {
    private final VillageElementData building;
    private final double price;
    private final ResourceType type;
    private final Player player;

    /**
     * Construct ShopItemImpl.
     *
     * @param building the building
     * @param type the type of resource needed
     * @param price the price of the ShopItem
     * @param player the player reference
     */
        public ShopItemImpl(
            final VillageElementData building,
            final ResourceType type,
            final double price,
            final Player player) {
        this.building = building;
        this.type = type;
        this.price = price;
        this.player = player;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResourceManager getResourceManager() {
        return player.getPlayerResources().get(type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getPrice() {
        return this.price;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResourceType getResourceType() {
        return this.type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VillageElementData getBuilding() {
        return this.building;
    }
}
