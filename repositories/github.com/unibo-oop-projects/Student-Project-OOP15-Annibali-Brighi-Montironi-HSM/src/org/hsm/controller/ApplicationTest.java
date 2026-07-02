package org.hsm.controller;

import org.hsm.controller.simulator.SimulatorImpl;
import org.hsm.model.db.Database;

/**
 *
 * Class for test the application.
 *
 */
public class ApplicationTest {

    /**
     * Method that preloading software information.
     */
    // CHECKSTYLE:OFF:
    public ApplicationTest() {
        final Controller controller = ControllerImpl.getController();
        final SimulatorImpl simulator = new SimulatorImpl();
        if (controller.isGhLoad()) {
            controller.deleteGreenhouse();
        }

        controller.createGreenhouse("Greenhouse Test", "Grid", 800, 30);
        final Database db = controller.getDatabase();

        controller.createNewPlant("Onion", "Allium cepa", 6, 4000, 16, 6, 23, 90, 800);
        controller.createNewPlant("Eggplant", "Solanum melongena", 6, 6000, 28, 6, 28, 80, 1600);
        controller.createNewPlant("Turnip", "Brassica rapa", 6, 5300, 20, 3, 24, 45, 900);
        controller.createNewPlant("Carrots", "Daucus carota", 6, 5300, 18, 6, 25, 65, 750);
        controller.createNewPlant("Tomato", "Solanum lycopersicum", 6, 5100, 35, 6, 25, 70, 2200);
        controller.createNewPlant("Watermelon", "Citrullus lanatus", 6, 3500, 20, 4, 35, 110, 5000);
        controller.createNewPlant("Cabbage", "Brassica oleracea (capitata)", 6, 5300, 26, 6, 27, 100, 3000);
        controller.createNewPlant("Basil", "Ocimum basilicum", 6, 5000, 12, 7, 21, 70, 900);
        controller.createNewPlant("Squash blossoms", "Cucurbita (winter)", 6, 4200, 20, 4, 35, 65, 1300);
        controller.createNewPlant("Strawberrie", "Fragaria × ananassa", 6, 5600, 20, 10, 27, 120, 1100);
        controller.createNewPlant("Cucumber", "Cucumis sativus", 6, 5300, 20, 3, 33, 50, 1200);
        controller.createNewPlant("Spinach", "Spinacia oleracea", 6, 5100, 20, 5, 21, 45, 3000);
        controller.createNewPlant("Blueberry", "Vaccinium myrtilloides", 5, 4500, 19, 5, 22, 60, 1200);
        controller.createNewPlant("Broccoli", "Brassica oleracea", 6, 5400, 31, 5, 25, 65, 1000);
        controller.createNewPlant("Artichoke", "Cynara cardunculus", 7, 5000, 12, 15, 21, 80, 2200);
        controller.createNewPlant("Bean (Common)", "Phaseolus vulgaris", 6, 5100, 30, 7, 24, 50, 300);
        controller.createNewPlant("Pumpkin", "Cucurbita", 6, 5800, 22, 4, 36, 90, 5000);
        controller.createNewPlant("Pea", "Pisum sativum", 6, 4600, 12, 6, 23, 60, 1200);
        controller.createNewPlant("Leek", "Allium porrum", 7, 5200, 16, 7, 26, 120, 600);
        controller.createNewPlant("Bell Pepper", "Capsicum annuum", 6, 6000, 25, 8, 26, 75, 2300);
        controller.createNewPlant("Radish", "Raphanus sativus", 6, 4000, 18, 4, 24, 30, 850);
        controller.createNewPlant("Lettuce", "Lactuca sativa", 6, 6300, 10, 3, 23, 53, 3800);

        controller.addPlants(db.getPlantModel("Allium cepa"), 640, 5);
        controller.addPlants(db.getPlantModel("Daucus carota"), 320, 4);
        controller.addPlants(db.getPlantModel("Ocimum basilicum"), 430, 2);
        controller.addPlants(db.getPlantModel("Solanum lycopersicum"), 570, 20);
        controller.addPlants(db.getPlantModel("Fragaria × ananassa"), 700, 10);
        controller.addPlants(db.getPlantModel("Capsicum annuum"), 610, 5);
        controller.addPlants(db.getPlantModel("Lactuca sativa"), 335, 15);

        controller.autoUpdate(1);

        for (int i = 0; i < 70; i++) {
            controller.getGreenhouse().incrementCounter();
            controller.getGreenhouse().getPlants().forEach((a, b) -> {
                b.addPhValue(simulator.getSimulatedPh(b));
                b.addBrightValue(simulator.getSimulatedBrightness(b));
                b.addConductValue(simulator.getSimulatedConductibility(b));
                b.addTempValue(simulator.getSimulatedTemperature(b));
                b.addPhValueTraditional(simulator.getRealPh(b));
                b.addBrightValueTraditional(simulator.getRealBrightness(b));
                b.addConductValueTraditional(simulator.getRealConductibility(b));
                b.addTempValueTraditional(simulator.getRealTemperature(b));

            });
        }

        controller.stopUpdate();
    }

}
