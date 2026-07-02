package entity.manager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import javax.swing.JFrame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.GameInfo;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Dimension2D;
import model.entity.DynamicEntity;
import model.entity.SpawnLevel;
import model.entity.factory.EntityFactory;
import model.entity.factory.EntityFactoryImpl;
import model.entity.manager.EntityManager;
import model.entity.manager.EntityManagerImpl;


class ManagerTest {

    private static final int NUM_ITERATIONS = 10_000;
    private static final int MIN_ENTITIES = 3;
    private static final int RESULT_ONE = 6;
    private static final int OUT_OF_SCREEN = 1200;

    private EntityManager generator;
    private EntityFactory factory;

    @BeforeEach
    void setUp() {
        /*Lines to initializes JavaFx environment, so tests work, otherwise we get
         * "java.lang.RuntimeException: Internal graphics not initialized yet" error */
        final JFrame frame = new JFrame("");
        final JFXPanel jfxPanel = new JFXPanel();
        frame.add(jfxPanel);

        final GameInfo info = new GameInfo();
        final Dimension2D dimension = new Dimension2D(info.getWidth(), info.getHeight());
        generator = new EntityManagerImpl(dimension);
        factory = new EntityFactoryImpl(dimension);
    }

    @Test
    void testGetter() {
        final List<DynamicEntity> entities = generator.getEntities();
        /*Empty list at the beginning*/
        assertEquals(entities.size(), 0);
        /*Add some entities using the factory*/
        entities.add(factory.createCoin(SpawnLevel.ZERO));
        entities.addAll(factory.combineAll(SpawnLevel.ONE, SpawnLevel.ZERO, SpawnLevel.TWO));
        entities.addAll(factory.combineObstacleCoin(SpawnLevel.ZERO, SpawnLevel.ONE));
        /*Check if the size of the list as changed*/
        assertEquals(entities.size(), RESULT_ONE);
    }

    @Test
    void testUpdate() {
        /*Update the generator, if the size of the list is zero the test should fail*/
        for (int i = 0; i < NUM_ITERATIONS; i++) {
            generator.updateList();
            if (generator.getEntities().isEmpty()) {
                fail("Entities list's size should be positive.");
            }
        }
        /*Change the speed of the entities so they go out of screen, when the list get updated 
         * all the entities must have been removed except for the new added ones*/
        generator.setSpeedX(OUT_OF_SCREEN);
        generator.updateList();
        assertTrue(generator.getEntities().size() <= MIN_ENTITIES);
    }
}
