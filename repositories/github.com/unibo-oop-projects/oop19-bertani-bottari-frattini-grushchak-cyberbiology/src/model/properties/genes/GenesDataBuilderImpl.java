package model.properties.genes;

import model.properties.defaultdata.DefaultDataEnum;
import model.properties.defaultdata.GenesDefaultDataEnum;
import model.properties.utilities.NumbersComparator;
import model.world.World;

/**
 * 
 * A builder for an {@link GenesData}. Can create only a single instance.
 *
 */
public class GenesDataBuilderImpl implements GenesDataBuilder {
    private final World world;
    private int reproductionCost;
    private int sunEnergy;
    private int mineralsAbsorption;
    private int nutritionOfDeadCell;
    private float sunPenetration;
    private float mineralsDepth;
    private float mutationRate;

    private boolean isBuild;

    /**
     * @param world the current world of simulation.
     */
    public GenesDataBuilderImpl(final World world) {
        this.world = world;

        this.reproductionCost = GenesDefaultDataEnum.REPRODUCTION_COST.getData().getDafaultValue().intValue();
        this.sunEnergy = GenesDefaultDataEnum.SUN_ENERGY.getData().getDafaultValue().intValue();
        this.mineralsAbsorption = GenesDefaultDataEnum.MINERALS_ABSORPTION.getData().getDafaultValue().intValue();
        this.nutritionOfDeadCell = GenesDefaultDataEnum.NUTRITION_OF_DEAD_CELL.getData().getDafaultValue().intValue();
        this.sunPenetration = GenesDefaultDataEnum.SUN_PENETRATION.getData().getDafaultValue().floatValue();
        this.mineralsDepth = GenesDefaultDataEnum.MINERALS_DEPTH.getData().getDafaultValue().floatValue();
        this.mutationRate = GenesDefaultDataEnum.MUTATION_RATE.getData().getDafaultValue().floatValue();
    }

    private void controlIsBuilt() {
        if (this.isBuild) {
            throw new IllegalStateException("This builder has already built");
        }
    }

    private void checkLimits(final Number value, final DefaultDataEnum defaultData) {
        if (NumbersComparator.isBiggerThan(defaultData.getData().getMinimumValue(), value)
                || NumbersComparator.isBiggerThan(value, defaultData.getData().getMaximumValue())) {
            throw new IllegalArgumentException("The parameter " + value + " is out of limits");
        }
    }

    @Override
    public final GenesDataBuilder setReproductionCost(final int cost) {
        controlIsBuilt();
        checkLimits(cost, GenesDefaultDataEnum.REPRODUCTION_COST);
        this.reproductionCost = cost;
        return this;
    }

    @Override
    public final GenesDataBuilder setSunPenetration(final float penetration) {
        controlIsBuilt();
        checkLimits(penetration, GenesDefaultDataEnum.SUN_PENETRATION);
        this.sunPenetration = penetration;
        return this;
    }

    @Override
    public final GenesDataBuilder setMineralsDepth(final float depth) {
        controlIsBuilt();
        checkLimits(depth, GenesDefaultDataEnum.MINERALS_DEPTH);
        this.mineralsDepth = depth;
        return this;
    }

    @Override
    public final GenesDataBuilder setSunEnergy(final int energy) {
        controlIsBuilt();
        checkLimits(energy, GenesDefaultDataEnum.SUN_ENERGY);
        this.sunEnergy = energy;
        return this;
    }

    @Override
    public final GenesDataBuilder setMineralsAbsorption(final int absorption) {
        controlIsBuilt();
        checkLimits(absorption, GenesDefaultDataEnum.MINERALS_ABSORPTION);
        this.mineralsAbsorption = absorption;
        return this;
    }

    @Override
    public final GenesDataBuilder setMutationRate(final float rate) {
        controlIsBuilt();
        checkLimits(rate, GenesDefaultDataEnum.MUTATION_RATE);
        this.mutationRate = rate;
        return this;
    }

    @Override
    public final GenesDataBuilder setNutritionOfDeadCell(final int nutrition) {
        controlIsBuilt();
        checkLimits(nutrition, GenesDefaultDataEnum.NUTRITION_OF_DEAD_CELL);
        this.nutritionOfDeadCell = nutrition;
        return this;
    }

    @Override
    public final GenesData build() {
        controlIsBuilt();
        this.isBuild = true;
        return new GenesData() {

            @Override
            public World getWorld() {
                return world;
            }

            @Override
            public int getReproductionCost() {
                return reproductionCost;
            }

            @Override
            public float getSunPenetration() {
                return sunPenetration;
            }

            @Override
            public int getSunEnergy() {
                return sunEnergy;
            }

            @Override
            public float getMineralsDepth() {
                return mineralsDepth;
            }

            @Override
            public int getMineralsAbsorption() {
                return mineralsAbsorption;
            }

            @Override
            public float getMutationRate() {
                return mutationRate;
            }

            @Override
            public int getNutritionOfDeadCell() {
                return nutritionOfDeadCell;
            }

        };
    }

}
