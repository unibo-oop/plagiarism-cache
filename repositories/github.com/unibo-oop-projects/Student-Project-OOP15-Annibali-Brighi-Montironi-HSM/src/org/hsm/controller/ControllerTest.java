package org.hsm.controller;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.hsm.model.db.Database;

/**
 *
 * Test class for Controller.
 *
 */
public class ControllerTest {

    private static final int GH_SIZE = 25;
    private static final int GH_COST = 450;

    private static final String NAME = "name";
    private static final String B_NAME = "botanical name";
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
     *
     * Method to test correct save and load from file.
     *
     */
    @Test
    public void controllerTeset() {
        final ControllerImpl controller = ControllerImpl.getController();
        final Database db = controller.getDatabase();

        controller.createGreenhouse("Test", "Grid", GH_COST, GH_SIZE);
        controller.createNewPlant(NAME, B_NAME, PH, LUX, COND, GROW, TEMP, LIFE, SIZE);
        controller.addPlants(db.getPlantModel(B_NAME), PLANT_COST, PLANTS);

        controller.saveGreenhouse();
        controller.saveDatabase();

        controller.deleteGreenhouse();

        controller.loadGreenhouse();
        controller.loadDatabase();

        assertTrue(db.getPlantModel(B_NAME).getName().equals(NAME));
        assertFalse(db.getPlantModel(B_NAME).getName().equals("nameerror"));


        assertEquals(PH, db.getPlantModel(B_NAME).getPH(), 0);
        assertEquals(LUX, db.getPlantModel(B_NAME).getBrightness(), 0);
        assertEquals(COND, db.getPlantModel(B_NAME).getConductivity(), 0);
        assertEquals(GROW, db.getPlantModel(B_NAME).getOptimalGrowthTime(), 0);
        assertEquals(TEMP, db.getPlantModel(B_NAME).getOptimalTemperature(), 0);
        assertEquals(LIFE, db.getPlantModel(B_NAME).getLife(), 0);
        assertEquals(SIZE, db.getPlantModel(B_NAME).getSize(), 0);
        assertEquals(PLANTS, controller.getGreenhouse().getNumberOfPlants());

    }

}
