package it.unibo.the100dayswar.model.tower.api;

/**
 * Enum which represents the different types of towers and their cost.
 */
public enum TowerType {
    /**
     * Basic tower type, which has a price of 50.
     */
    BASIC(50),

    /**
     * Advanced tower type, which has a price of 100.
     */
    ADVANCED(100);

    /**
     * The price associated with the tower type.
     */
    private final int price;

    /**
     * Constructs a TowerType with the specified price.
     *
     * @param price the price of the tower type
     */
    TowerType(final int price) {
        this.price = price;
    }

    /**
     * Gets the price of the tower type.
     *
     * @return the price of the tower type
     */
    public int getPrice() {
        return price;
    }
}
