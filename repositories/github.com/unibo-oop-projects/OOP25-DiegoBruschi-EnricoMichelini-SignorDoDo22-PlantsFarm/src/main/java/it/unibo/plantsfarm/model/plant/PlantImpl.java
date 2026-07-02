package it.unibo.plantsfarm.model.plant;

import java.io.Serializable;

import it.unibo.plantsfarm.model.plant.api.Plant;

/**
 * Represents a plant in the game.
 * Holds the dynamic state and pick static info from PlantType.
 */
public class PlantImpl implements Serializable, Plant {

    private static final long serialVersionUID = 2L;

    private final PlantType type;

    private final boolean isPlanted;
    private int harvestedQuantity;
    private long currentStageTime;
    private long lastWateredTime;
    private int growthStage;
    private boolean needsWater;
    private boolean watered;
    private long lastUpdate;
    private double harvestMultiplier;

    /**
     * Creates a new Plant based on a specific type.
     *
     * @param type The type of plant.
     */
    public PlantImpl(final PlantType type) {
        this.type = type;
        this.growthStage = 0;
        this.needsWater = true;
        this.isPlanted = true;
        this.lastWateredTime = System.currentTimeMillis();
        this.lastUpdate = System.currentTimeMillis();
        this.harvestMultiplier = 1.0;
    }

    /**
     * Increases the growth stage of the plant based on time status.
     *
     * @param now The current time in milliseconds.
     * 
     * @return true if the plant grew to the next stage, false otherwise.
     */
    public final boolean increaseGrowthStage(final long now) {
        return this.increaseGrowthStage(now, 1.0);
    }

    /**
     * Increases the growth stage of the plant based on time status, water status and a multiplier.
     *
     * @param now The current time in milliseconds.
     * @param multiplier A multiplier to speed up growth.
     * 
     * @return true if the plant grew to the next stage, false otherwise.
     */
    @Override
    public final boolean increaseGrowthStage(final long now, final double multiplier) {
        final long growthTimeFromLastUpdate = now - lastUpdate;
        lastUpdate = now;
        boolean hasGrown = false;

        if (!isMature() && watered && !needsWater) {
            currentStageTime += (long) (growthTimeFromLastUpdate * multiplier);

            if (currentStageTime >= GROWTH_TIME && growthStage < getMaxGrowthStage()) {
                currentStageTime = 0;
                watered = false;
                growthStage++;
                hasGrown = true;
            }
        }
        return hasGrown;
    }

    /**
     * Waters the plant and changes plant's water status.
     *
     * @param now The current time in milliseconds.
     */
    @Override
    public final void water(final Long now) {
        if (growthStage < type.getMaxGrowthStage() && needsWater) {
            this.lastWateredTime = System.currentTimeMillis();
            watered = true;
            needsWater = false;
            currentStageTime += WATER_REDUCTION_TIME;
        }
    }

    /**
     * Updates the water needs of the plant based on time.
     *
     * @param now The current time in milliseconds.
     */
    @Override
    public final void updateNeedsWater(final Long now) {
        if (this.type.getMaxGrowthStage() > this.growthStage && now - this.lastWateredTime >= WATER_TIME_COOLDOWN) {
            this.needsWater = true;
            this.watered = false;
        }
    }

    /**
     * Checks if the plant is ready to be harvested.
     *
     * @return true if harvestable, false otherwise.
     */
    @Override
    public final boolean isHarvestable() {
        return isMature();
    }

    /**
     * Checks if the plant has reached its max growth.
     *
     * @return true if mature, false otherwise.
     */
    @Override
    public final boolean isMature() {
        return growthStage >= (type.getMaxGrowthStage() - 1);
    }

    /**
     * Gets the selling value of a single unit of this plant.
     *
     * @return the value in coins, or 0 if ornamental.
     */
    @Override
    public final int getSellValue() {
        return type.getSellPrice();
    }

    /**
     * Sets the harvest multiplier for this plant.
     *
     * @param multiplier The multiplier to set.
     */
    @Override
    public final void setHarvestMultiplier(final double multiplier) {
        this.harvestMultiplier = multiplier;
    }

    /**
     * Calculates the amount of items obtained from harvesting this plant.
     *
     * @return a random number between min and max yield, or 0 if ornamental.
     */
    @Override
    public final int harvest() {
        if (!type.isEdible()) {
            return 0;
        }
        if (isMature()) {
            growthStage = this.type.getResetStage();
            currentStageTime = 0;
            final int baseHarvest = type.generateHarvest();
            this.harvestedQuantity = (int) (baseHarvest * this.harvestMultiplier);
            return this.harvestedQuantity;
        }
        return 0;
    }

    /**
     * Gets the quantity harvested from the last harvest action.
     *
     * @return the harvested quantity.
     */
    @Override
    public final int getHarvestedQuantity() {
        return this.harvestedQuantity;
    }

    /**
     * Gets the current stage time of the plant.
     *
     * @return the current stage time in milliseconds.
     */
    @Override
    public final long getCurrentStageTime() {
        return this.currentStageTime;
    }

    /**
     * Sets the current stage time of the plant.
     *
     * @param newTime The new stage time in milliseconds.
     */
    @Override
    public final void setCurrentStageTime(final long newTime) {
        this.currentStageTime = newTime;
    }

    /**
     * Gets the last watered time of the plant.
     *
     * @return the last watered time in milliseconds.
     */
    @Override
    public final long getLastWateredTime() {
        return this.lastWateredTime;
    }

    /**
     * Sets the last watered time of the plant.
     *
     * @param newTime The new last watered time in milliseconds.
     */
    @Override
    public final void setLastWateredTime(final long newTime) {
        this.lastWateredTime = newTime;
    }

    /**
     * Returns the type of this plant.
     *
     * @return The PlantType object.
     */
    @Override
    public final PlantType getType() {
        return type;
    }

    /**
     * Returns the current growth stage.
     *
     * @return the growth stage.
     */
    @Override
    public final int getGrowthStage() {
        return growthStage;
    }

    /**
     * Sets the growth stage of the plant.
     *
     * @param stage The new growth stage to set.
     */
    @Override
    public final void setGrowthStage(final int stage) {
        this.growthStage = stage;
    }

    /**
     * Returns true if the plant is edible.
     *
     * @return true if edible, false otherwise.
     */
    @Override
    public final boolean isEdible() {
        return type.isEdible();
    }

    /**
     * Checks if the plant needs water.
     *
     * @return true if the plant needs water, false otherwise.
     */
    @Override
    public final boolean needsWater() {
        return needsWater;
    }

    /**
     * Checks if the plant is planted.
     *
     * @return true if planted, false otherwise.
     */
    @Override
    public final boolean isPlanted() {
        return isPlanted;
    }

    /**
     * Checks if the plant type has been discovered.
     *
     * @return true if discovered, false otherwise.
     */
    @Override
    public final boolean isDiscovered() {
        return type.isDiscovered();
    }

    /**
     * Gets the rarity of the plant.
     *
     * @return the rarity Enum.
     */
    @Override
    public final Rarity getRarity() {
        return type.getRarity();
    }

    /**
     * Gets the max growth stage.
     *
     * @return the max growth stage.
     */
    @Override
    public final int getMaxGrowthStage() {
        return type.getMaxGrowthStage();
    }

    /**
     * Gets the name of the plant.
     *
     * @return the name.
     */
    @Override
    public final String getName() {
        return type.getName();
    }
}
