package clashclass.battle.battlereport;

import clashclass.commons.VectorInt2D;
import clashclass.ecs.GameObject;
import clashclass.elements.buildings.BattleBuildingFactoryImpl;
import clashclass.elements.buildings.BuildingFactory;
import clashclass.resources.ResourceManager;
import clashclass.resources.ResourceManagerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BattleReportTest {

    private BattleReportModelImpl model;
    private BattleReportControllerImpl controller;
    private VillageDestructionManagerImpl destructionManager;
    private BuildingFactory buildingFactory;

    @BeforeEach
    void setUp() {
        // Initialize real implementations
        model = new BattleReportModelImpl(5); // Assuming the village has 5 buildings
        BattleReportViewImpl view = new BattleReportViewImpl();
        controller = new BattleReportControllerImpl(model, view);
        destructionManager = new VillageDestructionManagerImpl(controller);
        buildingFactory = new BattleBuildingFactoryImpl();
    }

    @Test
    void testIncreaseDestructionPercentage() {
        // Create a mock GameObject (real implementation not provided, so a dummy is used)
        final GameObject destroyedBuilding = this.buildingFactory.createCannon(new VectorInt2D(0, 0));

        // Notify destruction
        destructionManager.notifyDestruction(destroyedBuilding);

        // Assert that the destruction percentage has increased
        final double expectedPercentage = 20.0; // Assuming 1 building destroyed out of 5 increases 20%
        assertEquals(expectedPercentage, model.getDestructionPercentage(), 0.01, "Destruction percentage should match expected value.");
    }

//    @Test
//    void testIncreaseStolenResources() {
//        // Initial stolen resources in the game model should be zero
//        assertEquals(1_000_000, model.getStolenResources().getCurrentValue(), "Initial stolen resources should be the max value.");
//
//        // Step 1: Add the first set of stolen resources
//        final ResourceManager firstResources = new ResourceManagerImpl(1_000_000); // Start with zero
//        firstResources.increase(500); // Simulate stealing 500 resources
//        controller.increaseStolenResources(firstResources);
//
//        // Verify the stolen resources have increased to 500
//        assertEquals(500, model.getStolenResources().getCurrentValue(),
//                "Stolen resources should increase to 500 after adding the first set.");
//
//        // Step 2: Add another set of stolen resources
//        final ResourceManager secondResources = new ResourceManagerImpl(0); // Start with zero
//        secondResources.increase(1000); // Simulate stealing 1000 resources
//        controller.increaseStolenResources(secondResources);
//
//        // Verify the stolen resources have increased to 1500 (500 + 1000)
//        assertEquals(1500, model.getStolenResources().getCurrentValue(),
//                "Stolen resources should increase to 1500 after adding the second set.");
//    }



    @Test
    void testSetTroopCount() {
        // Set troop count
        final int troopCount = 25;
        controller.setTroopCount(troopCount);

        // Assert that the troop count is set in the model
        assertEquals(troopCount, model.getTroopCount(), "Troop count should be set correctly in the model.");
    }

    @Test
    void testNotifyTownHallDestroyed() {
        // Notify that the Town Hall has been destroyed
        controller.notifyTownHallDestroyed();

        // Assert that the model reflects Town Hall as destroyed
        assertTrue(model.isTownHallDestroyed(), "The model should indicate that the Town Hall is destroyed.");
    }

    @Test
    void testGetDestructionPercentage() {
        // Simulate some destruction
        final GameObject destroyedBuilding = this.buildingFactory.createCannon(new VectorInt2D(0, 0));
        destructionManager.notifyDestruction(destroyedBuilding);

        // Get the destruction percentage via the controller
        final double percentage = controller.getDestructionPercentage();

        // Assert the percentage matches the expected value
        assertEquals(20.0, percentage, 0.01, "Destruction percentage should be 20% after 1 building destroyed.");
    }

    @Test
    void testGetStars() {
        // Destroy buildings to earn stars (simulate rules like 50% destruction earns 1 star)
        final GameObject building1 = this.buildingFactory.createCannon(new VectorInt2D(0, 0));
        final GameObject building2 = this.buildingFactory.createCannon(new VectorInt2D(1, 0));
        final GameObject building3 = this.buildingFactory.createCannon(new VectorInt2D(0, 1));

        destructionManager.notifyDestruction(building1);
        destructionManager.notifyDestruction(building2);
        destructionManager.notifyDestruction(building3);

        // Assert correct stars via model and controller
        assertEquals(1, model.getStars(), "Model should reflect 1 star for 50% destruction.");
        assertEquals(1, controller.getStars(), "Controller should return 1 star for the battle.");
    }


    @Test
    void testIsVictory() {
        // Destroy buildings to earn a star (simulate rules like 50% destruction earns 1 star)
        final GameObject building1 = this.buildingFactory.createCannon(new VectorInt2D(0, 0));
        final GameObject building2 = this.buildingFactory.createCannon(new VectorInt2D(1, 0));
        final GameObject building3 = this.buildingFactory.createCannon(new VectorInt2D(0, 1));

        destructionManager.notifyDestruction(building1);
        destructionManager.notifyDestruction(building2);
        destructionManager.notifyDestruction(building3);

        // Assert that the battle is marked as a victory
        assertTrue(controller.isVictory(), "The battle should be marked as a victory if at least 1 star is earned.");
    }


    @Test
    void testVillageDestructionManagerCoordinatesWithController() {
        // Create a destroyed building object
        final GameObject destroyedBuilding = this.buildingFactory.createCannon(new VectorInt2D(0, 0));

        // Notify destruction
        destructionManager.notifyDestruction(destroyedBuilding);

        // Assert that the destruction percentage is updated
        assertEquals(20.0, model.getDestructionPercentage(), 0.01, "Destruction percentage should increase in the model.");
    }
}