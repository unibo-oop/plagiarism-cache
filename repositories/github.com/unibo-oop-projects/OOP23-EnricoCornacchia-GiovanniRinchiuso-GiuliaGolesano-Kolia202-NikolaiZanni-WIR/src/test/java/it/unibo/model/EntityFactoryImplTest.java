package it.unibo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.common.Pair;
import it.unibo.controller.impl.GameController;
import it.unibo.model.api.ComponentType;
import it.unibo.model.api.Entity;
import it.unibo.model.api.GamePerformance;
import it.unibo.model.impl.EntityFactoryImpl;
import it.unibo.model.impl.GamePerformanceImpl;
import it.unibo.utilities.EntityType;

/**
 * Test class for {@link EntityFactoryImpl}.
 */
final class EntityFactoryImplTest {

        /**
         * The EntityFactoryImpl instance to be tested.
         */
        private EntityFactoryImpl entityFactoryImpl;

        /**
         * Sets up the test environment before each test.
         */
        @BeforeEach
        void setUp() {
                initializeEntityFactory();
        }

        /**
         * Initializes the EntityFactoryImpl instance.
         */
        private void initializeEntityFactory() {
                final GameController gameController = new GameController();
                final GamePerformance gamePerformance = new GamePerformanceImpl(gameController);
                entityFactoryImpl = new EntityFactoryImpl(gamePerformance);
        }

        /**
         * Tests the createFelix method.
         */
        @Test
        void testCreateFelix() {
                final Pair<Double, Double> position = new Pair<>(10.0, 10.0);
                final Entity felix = entityFactoryImpl.createFelix(position);
                assertNotNull(felix, "Felix entity should not be null");
                assertEquals(EntityType.FELIX, felix.getEntityType(), "Entity type should be FELIX");
                assertEquals(position, felix.getPosition(), "Felix position should be as initialized");
                assertTrue(felix.getComponents().stream().anyMatch(c -> c.getComponent() == ComponentType.MOVEMENT),
                                "Felix should have a MovementComponent");
                assertTrue(felix.getComponents().stream().anyMatch(c -> c.getComponent() == ComponentType.HITBOX),
                                "Felix should have a HitboxComponent");
                assertTrue(felix.getComponents().stream().anyMatch(c -> c.getComponent() == ComponentType.POINTS),
                                "Felix should have a PointsComponent");
                assertTrue(felix.getComponents().stream().anyMatch(c -> c.getComponent() == ComponentType.IMMORTALITY),
                                "Felix should have an ImmortalityComponent");
                assertTrue(felix.getComponents().stream().anyMatch(c -> c.getComponent() == ComponentType.LIFE),
                                "Felix should have a LivesComponent");
                assertTrue(felix.getComponents().stream().anyMatch(c -> c.getComponent() == ComponentType.FIXWINDOWS),
                                "Felix should have a FixWindowsComponent");
        }

        /**
         * Tests the createRalph method.
         */
        @Test
        void testCreateRalph() {
                final Pair<Double, Double> position = new Pair<>(10.0, 10.0);
                final Entity ralph = entityFactoryImpl.createRalph(position);
                assertNotNull(ralph, "Ralph entity should not be null");
                assertEquals(EntityType.RALPH, ralph.getEntityType(), "Entity type should be RALPH");
                assertEquals(position, ralph.getPosition(), "Ralph position should be as initialized");
                assertTrue(ralph.getComponents().stream().anyMatch(c -> c.getComponent() == ComponentType.MOVEMENT),
                                "Ralph should have a MovementComponent");
                assertTrue(ralph.getComponents().stream().anyMatch(c -> c.getComponent() == ComponentType.HITBOX),
                                "Ralph should have a HitboxComponent");
                assertTrue(ralph.getComponents().stream().anyMatch(c -> c.getComponent() == ComponentType.STOPRALPH),
                                "Ralph should have a StopRalphComponent");
                assertTrue(ralph.getComponents().stream().anyMatch(c -> c.getComponent() == ComponentType.THROWBRICK),
                                "Ralph should have a ThrowBrickComponent");
        }

        /**
         * Tests the createWindows method.
         */
        @Test
        void testCreateWindows() {
                final Pair<Double, Double> position = new Pair<>(10.0, 10.0);
                final Entity windows = entityFactoryImpl.createWindows(position, true);
                assertNotNull(windows, "Windows entity should not be null");
                assertEquals(EntityType.WINDOW, windows.getEntityType(), "Entity type should be WINDOW");
                assertEquals(position, windows.getPosition(), "Windows position should be as initialized");
                assertTrue(windows.getComponents().stream().anyMatch(c -> c.getComponent() == ComponentType.MOVEMENT),
                                "Windows should have a MovementComponent");
                assertTrue(windows.getComponents().stream().anyMatch(c -> c.getComponent() == ComponentType.HITBOX),
                                "Windows should have a HitboxComponent");
                assertTrue(windows.getComponents().stream().anyMatch(c -> c.getComponent() == ComponentType.FIXEDWINDOWS),
                                "Windows should have a FixedWindowsComponent");
        }

        /**
         * Tests the createBrick method.
         */
        @Test
        void testCreateBrick() {
                final Pair<Double, Double> position = new Pair<>(10.0, 10.0);
                final Entity brick = entityFactoryImpl.createBrick(position);
                assertNotNull(brick, "Brick entity should not be null");
                assertEquals(EntityType.BRICK, brick.getEntityType(), "Entity type should be BRICK");
                assertEquals(position, brick.getPosition(), "Brick position should be as initialized");
                assertTrue(brick.getComponents().stream().anyMatch(c -> c.getComponent() == ComponentType.MOVEMENT),
                                "Brick should have a MovementComponent");
                assertTrue(brick.getComponents().stream().anyMatch(c -> c.getComponent() == ComponentType.HITBOX),
                                "Brick should have a HitboxComponent");
        }

        /**
         * Tests the createCake method.
         */
        @Test
        void testCreateCake() {
                final Pair<Double, Double> position = new Pair<>(10.0, 10.0);
                final Entity cake = entityFactoryImpl.createCake(position);
                assertNotNull(cake, "Cake entity should not be null");
                assertEquals(EntityType.CAKE, cake.getEntityType(), "Entity type should be CAKE");
                assertEquals(position, cake.getPosition(), "Cake position should be as initialized");
                assertTrue(cake.getComponents().stream().anyMatch(c -> c.getComponent() == ComponentType.MOVEMENT),
                                "Cake should have a MovementComponent");
                assertTrue(cake.getComponents().stream().anyMatch(c -> c.getComponent() == ComponentType.CAKEPOSITION),
                                "Cake should have a CakePositionComponent");
                assertTrue(cake.getComponents().stream().anyMatch(c -> c.getComponent() == ComponentType.HITBOX),
                                "Cake should have a HitboxComponent");
        }

        /**
         * Tests the createBird method.
         */
        @Test
        void testCreateBird() {
                final Pair<Double, Double> position = new Pair<>(10.0, 10.0);
                final Entity bird = entityFactoryImpl.createBird(position);
                assertNotNull(bird, "Bird entity should not be null");
                assertEquals(EntityType.BIRD, bird.getEntityType(), "Entity type should be BIRD");
                assertEquals(position, bird.getPosition(), "Bird position should be as initialized");
                assertTrue(bird.getComponents().stream().anyMatch(c -> c.getComponent() == ComponentType.MOVEMENT),
                                "Bird should have a MovementComponent");
                assertTrue(bird.getComponents().stream().anyMatch(c -> c.getComponent() == ComponentType.BIRDPOSITION),
                                "Bird should have a BirdPositionComponent");
                assertTrue(bird.getComponents().stream().anyMatch(c -> c.getComponent() == ComponentType.HITBOX),
                                "Bird should have a HitboxComponent");
        }
}
