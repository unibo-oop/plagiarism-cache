package it.unibo.plantsfarm.model.plant;

import java.io.Serializable;

/**
 * Represents the type of a plant with game statistics.
 */
public final class PlantType implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String name;
    private final int maxGrowthStage;
    private final int resetStage;
    private final Rarity rarity;
    private final PlantBehavior behavior;
    private boolean isDiscovered;

    /**
     * Creates a new PlantType.
     *
     * @param name            The display name.
     * @param maxGrowthStage  The maximum growth stage.
     * @param resetStage      The stage to reset to after harvest.
     * @param rarity          The rarity level.
     * @param behavior        The strategy behavior.
     */
    public PlantType(final String name, final int maxGrowthStage, final int resetStage,
                     final Rarity rarity, final PlantBehavior behavior) {
        this.name = name;
        this.maxGrowthStage = maxGrowthStage;
        this.resetStage = resetStage;
        this.rarity = rarity;
        this.behavior = behavior;
        this.isDiscovered = false;
    }

    /**
     * @return true if the plant is edible.
     */
    public boolean isEdible() {
        return behavior.isEdible();
    }

    /**
     * @return the selling price.
     */
    public int getSellPrice() {
        return behavior.getSellPrice();
    }

    /**
     * @return the generated harvest amount.
     */
    public int generateHarvest() {
        return behavior.generateHarvest();
    }

    /**
     * Gets the name of the plant.
     *
     * @return The name string.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the max growth stage of the plant.
     *
     * @return The max growth stage.
     */
    public int getMaxGrowthStage() {
        return maxGrowthStage;
    }

    /**
     * Gets the stage the plant reverts to after harvest.
     *
     * @return The reset stage index.
     */
    public int getResetStage() {
        return resetStage;
    }

    /**
     * Gets the rarity of the plant.
     *
     * @return The Rarity value.
     */
    public Rarity getRarity() {
        return rarity;
    }

    /**
     * Gets the behavior strategy of the plant.
     *
     * @return the behavior strategy.
     */
    public PlantBehavior getBehavior() {
        return behavior;
    }

    /**
     * Unlocks this plant type in the encyclopedia.
     */
    public void unlock() {
        this.isDiscovered = true;
    }

    /**
     * Locks this plant type.
     */
    public void lock() {
        this.isDiscovered = false;
    }

    /**
     * Checks if this plant type has been discovered.
     *
     * @return true if discovered, false otherwise.
     */
    public boolean isDiscovered() {
        return isDiscovered;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PlantType that = (PlantType) o;
        return java.util.Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(name);
    }
}
