package org.hsm.controller.simulator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.hsm.controller.ControllerImpl;
import org.hsm.model.plant.Plant;

/**
 *
 * Simulator class: provide a simulated paramters for an Hydroponic Greenhouse.
 *
 */
public class SimulatorImpl implements Simulator, Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -5168313834926068201L;
    /**
     *  Hydroponic Delta values for PH.
     */
    protected static final double MAXRAND_PH = 0.3;
    /**
     *  Hydroponic Delta values for Brightness.
     */
    protected static final double MAXRAND_BRIGHT = 25.0;
    /**
     *  Hydroponic Delta values for Conductivity.
     */
    protected static final double MAXRAND_COND = 1.0;
    /**
     *  Hydroponic Delta values for Temperature.
     */
    protected static final double MAXRAND_TEMP = 0.7;
    private static final double MAXRAND_WATER = 0.9; // in mL
    private static final double MINRAND_WATER = 0.4; // in mL
    private static final double MAXRAND_GROW = 0.015; // in %
    private static final double MINRAND_GROW = 0.008; // in %

    /**
     *  Conventional coltivation Delta values for PH.
     */
    protected static final double MAXRAND_REAL_PH = 1.5;
    /**
     *  Conventional coltivation Delta values for Brightness.
     */
    protected static final double MAXRAND_REAL_BRIGHT = 500.0;
    /**
     *  Conventional coltivation Delta values for Conductivity.
     */
    protected static final double MAXRAND_REAL_COND = 11.0;
    /**
     *  Conventional coltivation Delta values for Temperature.
     */
    protected static final double MAXRAND_REAL_TEMP = 10.0;
    private static final double CONVENTIONAL_WATER_MULTIPLIER = 7; // 70%
    private static final double CONVENTIONAL_GROW_MULTIPLIER = 4; // times than
                                                                  // hydroponic

    private static final double ROUND_TO = 10.00;

    private final Random random = new Random();

    @Override
    public double getOptimalPh(final Plant plant) {
        return plant.getModel().getPH();
    }

    @Override
    public double getOptimalBrightness(final Plant plant) {
        return plant.getModel().getBrightness();
    }

    @Override
    public double getOptimalConductibility(final Plant plant) {
        return plant.getModel().getConductivity();
    }

    @Override
    public double getOptimalTemperature(final Plant plant) {
        return plant.getModel().getOptimalTemperature();
    }

    @Override
    public double getSimulatedPh(final Plant plant) {
        final double min = getOptimalPh(plant) - MAXRAND_PH;
        final double max = getOptimalPh(plant) + MAXRAND_PH;

        return this.roundTo(min + (max - min) * random.nextDouble());

    }

    @Override
    public double getSimulatedBrightness(final Plant plant) {
        final double min = getOptimalBrightness(plant) - MAXRAND_BRIGHT;
        final double max = getOptimalBrightness(plant) + MAXRAND_BRIGHT;

        return this.roundTo(min + (max - min) * random.nextDouble());
    }

    @Override
    public double getSimulatedConductibility(final Plant plant) {
        final double min = getOptimalConductibility(plant) - MAXRAND_COND;
        final double max = getOptimalConductibility(plant) + MAXRAND_COND;

        return this.roundTo(min + (max - min) * random.nextDouble());
    }

    @Override
    public double getSimulatedTemperature(final Plant plant) {
        final double min = getOptimalTemperature(plant) - MAXRAND_TEMP;
        final double max = getOptimalTemperature(plant) + MAXRAND_TEMP;

        return this.roundTo(min + (max - min) * random.nextDouble());
    }

    @Override
    public double getRealPh(final Plant plant) {
        final double min = getOptimalPh(plant) - MAXRAND_REAL_PH;
        final double max = getOptimalPh(plant) + MAXRAND_REAL_PH;

        return this.roundTo(min + (max - min) * random.nextDouble()) == 0 ? 0
                : this.roundTo(min + (max - min) * random.nextDouble());
    }

    @Override
    public double getRealBrightness(final Plant plant) {
        final double min = getOptimalBrightness(plant) - MAXRAND_REAL_BRIGHT;
        final double max = getOptimalBrightness(plant) + MAXRAND_REAL_BRIGHT;

        return this.roundTo(min + (max - min) * random.nextDouble()) == 0 ? 0
                : this.roundTo(min + (max - min) * random.nextDouble());
    }

    @Override
    public double getRealConductibility(final Plant plant) {
        final double min = getOptimalConductibility(plant) - MAXRAND_REAL_COND;
        final double max = getOptimalConductibility(plant) + MAXRAND_REAL_COND;

        return this.roundTo(min + (max - min) * random.nextDouble()) == 0 ? 0
                : this.roundTo(min + (max - min) * random.nextDouble());
    }

    @Override
    public double getRealTemperature(final Plant plant) {
        final double min = getOptimalTemperature(plant) - MAXRAND_REAL_TEMP;
        final double max = getOptimalTemperature(plant) + MAXRAND_REAL_TEMP;

        return this.roundTo(min + (max - min) * random.nextDouble()) == 0 ? 0
                : this.roundTo(min + (max - min) * random.nextDouble());
    }

    @Override
    public List<Double> getSimulatedWaterConsuption() {
        final List<Double> list = new ArrayList<>();
        list.add(0.0);
        for (int i = 1; i < ControllerImpl.getController().getGreenhouse().getUpdateCounter(); i++) {
            list.add(list.get(i - 1)
                    + (this.roundTo((MINRAND_WATER + (MAXRAND_WATER - MINRAND_WATER)) * random.nextDouble())));
        }
        return list;
    }

    @Override
    public List<Double> getSimulatedPlantGrow() {
        final List<Double> list = new ArrayList<>();
        list.add(0.0);
        for (int i = 1; i < ControllerImpl.getController().getGreenhouse().getUpdateCounter(); i++) {
            list.add(list.get(i - 1) + ((MINRAND_GROW + (MAXRAND_GROW - MINRAND_GROW) * random.nextDouble())));
        }
        return list;
    }

    @Override
    public List<Double> getRealWaterConsuption() {
        final List<Double> list = new ArrayList<>();
        list.add(0.0);
        for (int i = 1; i < ControllerImpl.getController().getGreenhouse().getUpdateCounter(); i++) {
            list.add(list.get(i - 1)
                    + (this.roundTo((MINRAND_WATER + (MAXRAND_WATER - MINRAND_WATER)) * random.nextDouble())
                            * CONVENTIONAL_WATER_MULTIPLIER));
        }
        return list;
    }

    @Override
    public List<Double> getRealPlantGrow() {
        final List<Double> list = new ArrayList<>();
        list.add(0.0);
        for (int i = 1; i < ControllerImpl.getController().getGreenhouse().getUpdateCounter(); i++) {
            list.add(list.get(i - 1) + ((MINRAND_GROW + (MAXRAND_GROW - MINRAND_GROW) * random.nextDouble())
                    / CONVENTIONAL_GROW_MULTIPLIER));
        }
        return list;
    }

    private double roundTo(final double value) {
        return Math.round(value * ROUND_TO) / ROUND_TO;
    }

}
