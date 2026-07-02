package it.unibo.plantsfarm.model.plant.api;

import it.unibo.plantsfarm.model.plant.PlantType;
import it.unibo.plantsfarm.model.plant.Rarity;

/**
 * Represents a plant in the game with its growth, water needs, and harvest logic.
 */
public interface Plant {

    long WATER_REDUCTION_TIME = 10_000L;
    long WATER_TIME_COOLDOWN = 15_000L;
    long GROWTH_TIME = 30_000L;

    /**
     * Increases the growth stage of the plant based on time status, water status and a multiplier.
     *
     * @param now The current time in milliseconds.
     * @param multiplier A multiplier to speed up growth.
     * 
     * @return true if the plant grew to the next stage, false otherwise.
     */
    boolean increaseGrowthStage(long now, double multiplier);

    /**
     * Waters the plant and changes plant's water status.
     *
     * @param now The current time in milliseconds.
     */
    void water(Long now);

    /**
     * Updates the water needs of the plant based on time.
     *
     * @param now The current time in milliseconds.
     */
    void updateNeedsWater(Long now);

    /**
     * Checks if the plant is ready to be harvested.
     *
     * @return true if harvestable, false otherwise.
     */
    boolean isHarvestable();

    /**
     * Checks if the plant has reached its max growth.
     *
     * @return true if mature, false otherwise.
     */
    boolean isMature();

    /**
     * Gets the selling value of a single unit of this plant.
     *
     * @return the value in coins, or 0 if ornamental.
     */
    int getSellValue();

    /**
     * Sets the harvest multiplier for this plant.
     *
     * @param multiplier The multiplier to set.
     */
    void setHarvestMultiplier(double multiplier);

    /**
     * Calculates the amount of items obtained from harvesting this plant.
     *
     * @return a random number between min and max yield, or 0 if ornamental.
     */
    int harvest();

    /**
     * Gets the quantity harvested from the last harvest action.
     *
     * @return the harvested quantity.
     */
    int getHarvestedQuantity();

    /**
     * Gets the current stage time of the plant.
     *
     * @return the current stage time in milliseconds.
     */
    long getCurrentStageTime();

    /**
     * Sets the current stage time of the plant.
     *
     * @param newTime The new stage time in milliseconds.
     */
    void setCurrentStageTime(long newTime);

    /**
     * Gets the last watered time of the plant.
     *
     * @return the last watered time in milliseconds.
     */
    long getLastWateredTime();

    /**
     * Sets the last watered time of the plant.
     *
     * @param newTime The new last watered time in milliseconds.
     */
    void setLastWateredTime(long newTime);

    /**
     * Returns the type of this plant.
     *
     * @return The PlantType object.
     */
    PlantType getType();

    /**
     * Returns the current growth stage.
     *
     * @return the growth stage.
     */
    int getGrowthStage();

    /**
     * Sets the growth stage of the plant.
     *
     * @param stage The new growth stage to set.
     */
    void setGrowthStage(int stage);

    /**
     * Returns true if the plant is edible.
     *
     * @return true if edible, false otherwise.
     */
    boolean isEdible();

    /**
     * Checks if the plant needs water.
     *
     * @return true if the plant needs water, false otherwise.
     */
    boolean needsWater();

    /**
     * Checks if the plant is planted.
     *
     * @return true if planted, false otherwise.
     */
    boolean isPlanted();

    /**
     * Checks if the plant type has been discovered.
     *
     * @return true if discovered, false otherwise.
     */
    boolean isDiscovered();

    /**
     * Gets the rarity of the plant.
     *
     * @return the rarity Enum.
     */
    Rarity getRarity();

    /**
     * Gets the max growth stage.
     *
     * @return the max growth stage.
     */
    int getMaxGrowthStage();

    /**
     * Gets the name of the plant.
     *
     * @return the name.
     */
    String getName();
}
