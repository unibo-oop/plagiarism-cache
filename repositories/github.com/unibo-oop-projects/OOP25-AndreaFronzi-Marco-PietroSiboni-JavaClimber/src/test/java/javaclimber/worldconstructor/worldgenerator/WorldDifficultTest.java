package javaclimber.worldconstructor.worldgenerator;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.score.impl.ScoreManagerImpl;
import it.unibo.model.world.api.QueueWorld;
import it.unibo.model.world.impl.BoundWorldImpl;
import it.unibo.model.world.impl.BoundY;
import it.unibo.model.world.impl.Boundary;
import it.unibo.model.world.impl.UpperWorld;
import it.unibo.model.worldconstructor.data.Difficult;
import it.unibo.model.worldconstructor.gameobjectspawn.addonspawn.impl.AddOnPoolEasy;
import it.unibo.model.worldconstructor.gameobjectspawn.addonspawn.impl.AddOnPoolMedium;
import it.unibo.model.worldconstructor.gameobjectspawn.addonspawn.impl.GameObjDimension;
import it.unibo.model.worldconstructor.gameobjectspawn.impl.SpawnPoolCreatorImpl;
import it.unibo.model.worldconstructor.gameobjectspawn.impl.SpawnPoolEasy;
import it.unibo.model.worldconstructor.gameobjectspawn.platformspawn.impl.Distance;
import it.unibo.model.worldconstructor.gameobjectspawn.platformspawn.impl.PlatformPoolEasy;
import it.unibo.model.worldconstructor.gameobjectspawn.platformspawn.impl.PlatformPoolMedium;
import it.unibo.model.worldconstructor.worldgenerator.impl.WorldConstructorImpl;
import it.unibo.model.worldconstructor.worldgenerator.impl.WorldDifficultImpl;

/**
 * Test for the {@link WorldDifficultImpl}.
 */
class WorldDifficultTest {

        private static final double Y_MIN = 0;
        private static final double Y_MAX = 600;

        private static final double X_MIN = 0;
        private static final double X_MAX = 800;

        private static final double HEIGHT_EASY = 0;
        private static final double HEIGHT_MEDIUM = 50_000;

        /**
         * The WorldDifficultImpl to test.
         */
        private WorldDifficultImpl worldDifficult;

        /**
         * The WorldConstructorImpl used for testing.
         */
        private WorldConstructorImpl worldConstructor;

        /**
         * The upper world for testing.
         */
        private QueueWorld upperWorld;

        /**
         * The medium difficulty level for testing.
         */
        private Difficult difficultMedium;

        /**
         * Set up the test environment.
         */
        @BeforeEach
        void setUp() {
                final var boundary = new BoundWorldImpl(new BoundY(Y_MIN, Y_MAX), new Boundary(X_MIN, X_MAX));
                this.upperWorld = new UpperWorld(boundary);
                final var distanceEasy = new Distance(200, 100, 300);
                final var distanceMedium = new Distance(300, 150, 250);
                final var spawnPoolCreator = new SpawnPoolCreatorImpl(upperWorld);
                final var spawnPoolEasy = new SpawnPoolEasy(GameObjDimension.EASY_PLATFORM_WIDTH,
                                GameObjDimension.EASY_PLATFORM_HEIGHT, new ScoreManagerImpl());
                final var platformPoolEasy = new PlatformPoolEasy(spawnPoolCreator,
                                GameObjDimension.EASY_PLATFORM_WIDTH,
                                GameObjDimension.EASY_PLATFORM_HEIGHT);
                final var platformPoolMedium = new PlatformPoolMedium(spawnPoolCreator,
                                GameObjDimension.MEDIUM_PLATFORM_WIDTH,
                                GameObjDimension.MEDIUM_PLATFORM_HEIGHT);
                final var addOnPoolEasy = new AddOnPoolEasy(spawnPoolCreator, 0.3);
                final var addOnPoolMedium = new AddOnPoolMedium(spawnPoolCreator, 0.5);
                final var difficultEasy = new Difficult(HEIGHT_EASY, distanceEasy, spawnPoolEasy, addOnPoolEasy,
                                platformPoolEasy);
                this.difficultMedium = new Difficult(HEIGHT_MEDIUM, distanceMedium, spawnPoolEasy, addOnPoolMedium,
                                platformPoolMedium);
                final var difficultList = List.of(
                                difficultEasy,
                                this.difficultMedium);
                spawnPoolCreator.setSpawnPool(spawnPoolEasy);
                this.worldConstructor = new WorldConstructorImpl(upperWorld, difficultList.getFirst(),
                                new SpawnPoolCreatorImpl(upperWorld));
                this.worldDifficult = new WorldDifficultImpl(difficultList);
        }

        /**
         * Test for registering an observer.
         */
        @Test
        void testRegisterObserver() {
                assertTrue(worldDifficult.registerObserver(this.worldConstructor));
        }

        /**
         * Test for removing an observer.
         */
        @Test
        void testRemoveObserver() {
                worldDifficult.registerObserver(this.worldConstructor);
                assertTrue(worldDifficult.removeObserver(this.worldConstructor));
        }

        /**
         * Test for notifying observers.
         */
        @Test
        void testNotifyObservers() {
                this.worldDifficult.registerObserver(this.worldConstructor);
                this.worldDifficult.notifyObservers(this.difficultMedium);
                this.worldConstructor.fillWorld();
                assertTrue(!this.upperWorld.getGadgets().isEmpty() || !this.upperWorld.getMoneys().isEmpty()
                                || !this.upperWorld.getMonsters().isEmpty()
                                || !this.upperWorld.getMovingPlatforms().isEmpty()
                                || !this.upperWorld.getOnTouchPlatforms().isEmpty()
                                || !this.upperWorld.getStaticPlatforms().isEmpty());
        }

        /**
         * Test for creating a difficulty level.
         */
        @Test
        void testCreateDifficult() {
                this.worldDifficult.registerObserver(this.worldConstructor);
                this.worldDifficult.createDifficult(HEIGHT_MEDIUM + 1);
                this.worldConstructor.fillWorld();
                assertTrue(!this.upperWorld.getGadgets().isEmpty() || !this.upperWorld.getMoneys().isEmpty()
                                || !this.upperWorld.getMonsters().isEmpty()
                                || !this.upperWorld.getMovingPlatforms().isEmpty()
                                || !this.upperWorld.getOnTouchPlatforms().isEmpty()
                                || !this.upperWorld.getStaticPlatforms().isEmpty());
        }

}
