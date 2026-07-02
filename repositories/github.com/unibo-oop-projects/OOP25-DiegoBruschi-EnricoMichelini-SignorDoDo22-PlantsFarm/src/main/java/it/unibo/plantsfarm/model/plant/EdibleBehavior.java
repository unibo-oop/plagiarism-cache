package it.unibo.plantsfarm.model.plant;

import java.util.Random;

/**
 * Strategy implementation for edible plants.
 */
public final class EdibleBehavior implements PlantBehavior {

    private static final long serialVersionUID = 1L;
    private final int sellPrice;
    private final int minItem;
    private final int maxItem;
    private final Random random;

    /**
     * Creates a new EdibleBehavior.
     *
     * @param sellPrice The value in coins of a single item of this plant.
     * @param minItem  The minimum quantity obtained when harvesting.
     * @param maxItem  The maximum quantity obtained when harvesting.
     */
    public EdibleBehavior(final int sellPrice, final int minItem, final int maxItem) {
        this.sellPrice = sellPrice;
        this.minItem = minItem;
        this.maxItem = maxItem;
        this.random = new Random();
    }

    @Override
    public boolean isEdible() {
        return true;
    }

    @Override
    public int getSellPrice() {
        return this.sellPrice;
    }

    @Override
    public int generateHarvest() {
        if (minItem >= maxItem) {
            return minItem;
        }
        return minItem + random.nextInt(maxItem - minItem + 1);
    }
}
