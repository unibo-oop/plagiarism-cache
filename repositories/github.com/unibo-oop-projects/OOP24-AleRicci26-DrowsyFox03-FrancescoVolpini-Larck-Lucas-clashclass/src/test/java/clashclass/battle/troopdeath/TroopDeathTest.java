//package clashclass.battle.troopdeath;
//
//import clashclass.battle.destruction.BattleTroopsBehaviorManagerImpl;
//import clashclass.commons.HealthComponentImpl;
//import clashclass.ecs.GameObject;
//import clashclass.ecs.GameObjectImpl;
//import clashclass.commons.HealthComponent;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class TroopDeathTest {
//
////    private BattleTroopsBehaviorManagerImpl manager;
////    private GameObject troop;
////
////    @BeforeEach
////    public void setUp() {
////        manager = new BattleTroopsBehaviorManagerImpl();
////        troop = new GameObjectImpl();
////    }
////
////    @Test
////    public void testTroopAddedCorrectly() {
////        // Add troop to the manager
////        manager.addTroop(troop);
////
////        // Ensure no exception is thrown when adding a troop
////        assertDoesNotThrow(() -> manager.updateTroopsBehavior(new GameObjectImpl()),
////                "Manager should handle troop behavior correctly after adding.");
////    }
////
////    @Test
////    public void testTroopDeathHandling() {
////        // Add troop to the manager
////        manager.addTroop(troop);
////
////        // Add HealthComponent to simulate troop's health
////        HealthComponent healthComponent = new HealthComponentImpl(1000);
////        troop.addComponent(healthComponent);
////
////        // Initially, troop health should be above 0
////        assertTrue(healthComponent.getHealth() > 0, "Troop health should be positive initially.");
////
////        // Reduce health to 0 to simulate the troop's death
////        healthComponent.decrease(healthComponent.getHealth()); // Set health to 0
////
////        // Assume updateTroopsBehavior should clean up dead troops or mark them as dead
////        manager.updateTroopsBehavior(new GameObjectImpl());
////
////        // Check if the troop is properly handled as dead
////        // Example: implement and use manager.isTroopAlive(troop) in your codebase to complete this test.
////    }
////
////    @Test
////    public void testDeadTroopIsRemovedFromManager() {
////        // Add troop to the manager
////        manager.addTroop(troop);
////
////        // Simulate troop death
////        HealthComponent healthComponent = new HealthComponentImpl(1000);
////        troop.addComponent(healthComponent);
////        healthComponent.decrease(1500); // Kill the troop
////
////        // Remove or handle the dead troop in an update
////        manager.updateTroopsBehavior(new GameObjectImpl());
////
////        // Validation: ensure the troop is removed from the manager
////        // assertFalse(manager.containsTroop(troop), "Dead troop should be removed from the manager.");
////    }
//}