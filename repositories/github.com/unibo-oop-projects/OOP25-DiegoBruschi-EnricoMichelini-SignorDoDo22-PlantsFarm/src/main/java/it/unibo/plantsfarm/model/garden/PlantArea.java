package it.unibo.plantsfarm.model.garden;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import it.unibo.plantsfarm.model.plant.PlantImpl;
import it.unibo.plantsfarm.model.tiles.SoilImpl;
import it.unibo.plantsfarm.model.plant.PlantEffect;
import it.unibo.plantsfarm.model.plant.OrnamentalBehavior;

/**
 * Represents a specific area in the garden containing multiple soils.
 * Calculate and apply passive effects based on the plant located at its geometric center.
 * The "centerSoil" represents the ornamental plant that provides bonuses to the growth of surrounding edible plants.
 */
public final class PlantArea {

    private final Rectangle bounds;
    private final List<SoilImpl> soils = new ArrayList<>();
    private SoilImpl centerSoil;

    /**
     * Creates a new PlantArea.
     *
     * @param bounds The rectangle defining the area limits.
     */
    public PlantArea(final Rectangle bounds) {
        this.bounds = new Rectangle(bounds);
    }

    /**
     * Adds a soil to this area if it is contained.
     * Find out if the soil is the center of the area.
     *
     * @param soil The soil object to try to add.
     */
    @edu.umd.cs.findbugs.annotations.SuppressFBWarnings(
        value = "EI2",
        justification = "The PlantArea needs to hold the reference to the actual Soil object to update the game state."
    )
    public void addSoil(final SoilImpl soil) {
        if (bounds.contains(soil.getCoordinate())) {
            soils.add(soil);
            if (soil.getCoordinate().contains(bounds.getCenterX(), bounds.getCenterY())) {
                this.centerSoil = soil;
            }
        }
    }

    /**
     * Updates all plants in this area, applying bonuses if applicable.
     *
     * @param now Current time in milliseconds.
     *
     * @return true if at least one plant grew, false otherwise.
     */
    public boolean updateArea(final long now) {
        boolean areaGrew = false;
        double growthMultiplier = 1.0;
        double harvestMultiplier = 1.0;
        if (centerSoil != null && centerSoil.isPlanted()) {
            final PlantImpl centerPlant = centerSoil.getPlant();

            if (centerPlant.increaseGrowthStage(now)) {
                areaGrew = true;
            }
            centerPlant.updateNeedsWater(now);

            if (centerPlant.isMature() && centerPlant.getType().getBehavior() instanceof OrnamentalBehavior) {
                final OrnamentalBehavior behavior = (OrnamentalBehavior) centerPlant.getType().getBehavior();

                if (behavior.getEffect() == PlantEffect.GROWTH_SPEED) {
                    growthMultiplier = behavior.getValue();
                } else if (behavior.getEffect() == PlantEffect.BIG_HARVEST) {
                    harvestMultiplier = behavior.getValue();
                }
            }
        }

        for (final SoilImpl soil : soils) {
            if (soil.equals(centerSoil)) {
                continue;
            }

            if (soil.isPlanted()) {
                final PlantImpl p = soil.getPlant();
                if (p.increaseGrowthStage(now, growthMultiplier)) {
                    areaGrew = true;
                }
                p.setHarvestMultiplier(harvestMultiplier);
                p.updateNeedsWater(now);
            }
        }

        return areaGrew;
    }

    /**
     * Check if a soil belongs to this area.
     *
     * @param soil The soil to check.
     *
     * @return True if the soil is inside the area bounds, false otherwise.
     */
    public boolean contains(final SoilImpl soil) {
        return bounds.contains(soil.getCoordinate());
    }
}
