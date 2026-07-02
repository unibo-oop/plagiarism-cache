//package clashclass.battle.destruction;
//
//import clashclass.battle.battlereport.BattleReportControllerImpl;
//import clashclass.battle.battlereport.BattleReportModelImpl;
//import clashclass.battle.battlereport.BattleReportViewJavaFXImpl;
//import clashclass.battle.manager.BattleManagerControllerImpl;
//import clashclass.battle.manager.BattleManagerModelImpl;
//import clashclass.battle.manager.BattleManagerViewJavaFXImpl;
//import clashclass.ecs.GameObject;
//import clashclass.ecs.GameObjectImpl;
//import clashclass.commons.HealthComponent;
//import javafx.scene.Scene;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.nio.file.Paths;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class DestructionAndBattleManagementTest {
////
////    private BattleTroopsBehaviorManagerImpl manager;
////    private GameObject troop;
////    private GameObject destroyedBuilding;
////
////    @BeforeEach
////    public void setUpBattleTroopsBehaviorManager() {
//////        manager = new BattleTroopsBehaviorManagerImpl();
////        troop = new GameObjectImpl();
////        destroyedBuilding = new GameObjectImpl();
////    }
////
////    @Test
////    public void testAddTroop_BattleTroopsBehaviorManager() {
////        manager.addTroop(troop);
////        assertDoesNotThrow(() -> manager.updateTroopsBehavior(destroyedBuilding), "Should update troops behavior without exceptions.");
////    }
////
////    @Test
////    public void testRemoveTroop_BattleTroopsBehaviorManager() {
////        manager.addTroop(troop);
////        manager.removeTroop(troop);
////        assertDoesNotThrow(() -> manager.updateTroopsBehavior(destroyedBuilding), "Behavior updates should work even with no troops.");
////    }
////
////    @Test
////    public void testAddSameTroopTwice_BattleTroopsBehaviorManager() {
////        manager.addTroop(troop);
////        manager.addTroop(troop); // Add the same troop again
////        assertDoesNotThrow(() -> manager.updateTroopsBehavior(destroyedBuilding), "Adding the same troop twice should be handled gracefully.");
////    }
////
////
////
////    private DestructionObservableImpl observable;
////    private SimpleDestructionObserver observer;
////    private GameObject simpleGameObject;
////
////    @BeforeEach
////    public void setUpDestructionObservable() {
////        observable = new DestructionObservableImpl();
////        observer = new SimpleDestructionObserver();
////        simpleGameObject = new GameObjectImpl();
////        observable.setGameObject(simpleGameObject);
////    }
////
////    /**
////     * A simple implementation of DestructionObserver for testing purposes.
////     */
////    private static class SimpleDestructionObserver implements DestructionObserver {
////        private boolean notified = false;
////
////        @Override
////        public void notifyDestruction(GameObject obj) {
////            notified = true;
////        }
////
////        public boolean wasNotified() {
////            return notified;
////        }
////
////        @Override
////        public void setGameObject(GameObject gameObject) {
////
////        }
////
////        @Override
////        public GameObject getGameObject() {
////            return null;
////        }
////
////        @Override
////        public void initialize() {
////
////        }
////    }
////
////    @Test
////    public void testAddObserver_DestructionObservable() {
////        observable.addObserver(observer);
////
////        // Simulate destruction event
////        observable.update(1f);
////
////        assertTrue(observer.wasNotified(), "Observer should have been notified of the destruction.");
////    }
////
////    @Test
////    public void testRemoveObserver_DestructionObservable() {
////        observable.addObserver(observer);
////        observable.removeObserver(observer);
////
////        // Simulate destruction event
////        observable.update(1f);
////
////        assertFalse(observer.wasNotified(), "Observer should NOT have been notified after removal.");
////    }
////
////    @Test
////    public void testObserverNotNotifiedWhenAlive_DestructionObservable() {
////        // Ensure the GameObject remains "alive" by default, preventing destruction notifications
////        observable.addObserver(observer);
////        observable.update(1f);
////
////        assertFalse(observer.wasNotified(), "Observer should NOT be notified if the object is not destroyed.");
////    }
////
////    //==================================================================
////    // SECTION 3: Tests for EndBattleAllVillageDestroyedImpl
////    //==================================================================
////
////    private EndBattleAllVillageDestroyedImpl villageDestroyedObserver;
////
//////    @BeforeEach
//////    public void setUpEndBattleAllVillageDestroyed() {
//////        villageDestroyedObserver = new EndBattleAllVillageDestroyedImpl(
//////                new BattleManagerControllerImpl(
//////                        new BattleManagerModelImpl(
//////                                Paths.get("Villages-Data/player-village.csv"),
//////                                Paths.get("Villages-Data/battle-village.csv")),
//////                        new BattleManagerViewJavaFXImpl(null, null)
//////                ),
//////                new BattleReportControllerImpl(
//////                        new BattleReportModelImpl(100),
//////                        new BattleReportViewJavaFXImpl(null)
//////                )
//////        );
////    }
////
////    @Test
////    public void testVillageNotFullyDestroyedInitially_EndBattleAllVillageDestroyed() {
////        assertFalse(villageDestroyedObserver.isFullyDestroyed(), "Initially, the village should not be fully destroyed.");
////    }
////
////    @Test
////    public void testNotifyDestructionBeforeFullyDestroyed_EndBattleAllVillageDestroyed() {
////        GameObject building = new GameObjectImpl();
////
////        // Call notifyDestruction
////        villageDestroyedObserver.notifyDestruction(building);
////
////        // Since `isFullyDestroyed()` returns false by default, no further effects should occur
////        assertFalse(villageDestroyedObserver.isFullyDestroyed(), "Even after notification, the village should remain not fully destroyed since no actual destruction logic is implemented.");
////    }
////
////
////    private EndBattleTimerIsOverImpl timerObserver;
////
//////    @BeforeEach
//////    public void setUpEndBattleTimerIsOver() {
//////        timerObserver = new EndBattleTimerIsOverImpl();
//////    }
////
////    @Test
////    public void testTimerNotFinishedInitially_EndBattleTimerIsOver() {
////        assertFalse(timerObserver.isFinished(), "Timer should not be finished initially.");
////    }
////
////    @Test
////    public void testNotifyDestructionWhenTimerNotFinished_EndBattleTimerIsOver() {
////        GameObject obj = new GameObjectImpl();
////
////        timerObserver.notifyDestruction(obj);
////
////        // Since timer is not finished, no further effects should occur
////        assertFalse(timerObserver.isFinished(), "Timer should stay unfinished and no effects should have occurred.");
////    }
////
////    //==================================================================
////    // SECTION 5: General Tests for DestructionObserver (Interface)
////    //==================================================================
////
////    @Test
////    public void testNotifyDestruction_DestructionObserver() {
////        DestructionObserver observer = new SimpleDestructionObserver();
////        GameObject obj = new GameObjectImpl();
////
////        observer.notifyDestruction(obj);  // Observer is notified
////
////        assertTrue(((SimpleDestructionObserver) observer).wasNotified(), "The observer should have been notified successfully.");
////    }
////
////    //==================================================================
////    // SECTION 6: General Tests for DestructionObservable (Interface)
////    //==================================================================
////
////    @Test
////    public void testAddAndRemoveObserver_DestructionObservable() {
////        DestructionObservable observable = new DestructionObservableImpl();
////        SimpleDestructionObserver observer = new SimpleDestructionObserver();
////
////        // Test adding the observer
////        observable.addObserver(observer);
////
////        // Simulate destruction event
////        observable.update(1f);
////
////        assertTrue(observer.wasNotified(), "Observer should be notified after being added.");
////
////        // Test removing the observer
////        observer = new SimpleDestructionObserver(); // Reset the observer
////        observable.removeObserver(observer);
////
////        // Simulate destruction event
////        observable.update(1f);
////
////        assertFalse(observer.wasNotified(), "Observer should not be notified after being removed.");
////    }
//}