package pvz.utilities;

/**
 * Enum representing different types of plants in the game.
 * Each type is associated with a life value and planting cost
 */
public enum PlantType {

    /**
     * Represents a Peashooter plant with his life and price.
     */
    PEASHOOTER(50, 100),

    /**
     * Represents a Sunflower plant with his life and price.
     */
    SUNFLOWER(25, 75),

    /**
     * Represents a Wallnut plant with his life and price.
     */
    WALLNUT(75, 200);

    private final int price;
    private final int life;

    /**
     * Constructs a {@code PlantType} with the specified price and life values.
     *
     * @param price The cost in sun points to plant this type.
     * @param life  The initial life (health) of the plant.
     * @throws IllegalArgumentException if {@code price] or [@code life] are negative.
     */
    PlantType(final int price, final int life) {
        if (life < 0) {
            throw new IllegalArgumentException("Life cannot be negative.");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
        this.price = price;
        this.life = life;
    }

    /**
     * Returns the cost to place this plant in the game.
     *
     * @return The price of the plant.
     */
    public int getPrice() {
        return this.price;
    }

    /**
     * Returns the maximum life value of this plant.
     *
     * @return The initial life of the plant.
     */
    public int getLife() {
        return this.life;
    }
}
