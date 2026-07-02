package org.hsm.controller.simulator;

import static org.junit.Assert.assertEquals;

import org.hsm.controller.Controller;
import org.hsm.controller.ControllerImpl;
import org.hsm.model.plant.Plant;
import org.junit.Test;

/**
 *
 * Class to test Simulator.
 *
 */
public class SimulatorTest {

    private static final int GH_COST = 650;
    private static final int GH_SIZE = 100;

    private static final String NAME = "Lettuce";
    private static final String B_NAME = "Lactuca sativa";
    private static final int PH = 6;
    private static final int LUX = 4500;
    private static final int COND = 23;
    private static final int GROW = 8;
    private static final int TEMP = 27;
    private static final int LIFE = 70;
    private static final int SIZE = 250;

    private static final int PLANT_COST = 6;
    private static final int PLANTS = 25;

    /**
     * Test Simulator values.
     */
    @Test
    public void testSimulator() {

        final Controller cont = ControllerImpl.getController();
        cont.createGreenhouse("Test", "Grid", GH_COST, GH_SIZE);
        cont.createNewPlant(NAME, B_NAME, PH, LUX, COND, GROW, TEMP, LIFE, SIZE);
        cont.addPlants(cont.getDatabase().getPlantModel(B_NAME), PLANT_COST, PLANTS);
        final Plant plant = cont.getGreenhouse().getPlants().get(1);
        final Simulator sim = new SimulatorImpl();

        // Test Parametri nei margini
        for (int i = 0; i < 1000; i++) {
            assertEquals(sim.getSimulatedPh(plant), PH, SimulatorImpl.MAXRAND_PH);
            assertEquals(sim.getSimulatedBrightness(plant), LUX, SimulatorImpl.MAXRAND_BRIGHT);
            assertEquals(sim.getSimulatedConductibility(plant), COND, SimulatorImpl.MAXRAND_COND);
            assertEquals(sim.getSimulatedTemperature(plant), TEMP, SimulatorImpl.MAXRAND_TEMP);

            assertEquals(sim.getRealPh(plant), PH, SimulatorImpl.MAXRAND_REAL_PH);
            assertEquals(sim.getRealBrightness(plant), LUX, SimulatorImpl.MAXRAND_REAL_BRIGHT);
            assertEquals(sim.getRealConductibility(plant), COND, SimulatorImpl.MAXRAND_REAL_COND);
            assertEquals(sim.getRealTemperature(plant), TEMP, SimulatorImpl.MAXRAND_REAL_TEMP);

        }
    }

}
