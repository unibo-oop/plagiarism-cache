package clashclass.shop;

/**
 * Represents a building price.
 */
public enum BuildingPrice {
    GOLD_EXTRACTOR(1000),
    GOLD_STORAGE(3000),
    ELIXIR_EXTRACTOR(1000),
    ELIXIR_STORAGE(3000),
    WALL(500),
    ARMY_CAMP(3000),
    CANNON(3000),
    ARCHER_TOWER(3500);

    private final int price;

    /**
     * Constructs the building price.
     *
     * @param price the price
     */
    BuildingPrice(final int price) {
        this.price = price;
    }

    /**
     * Gets the price.
     *
     * @return the price
     */
    public int getPrice() {
        return this.price;
    }
}
