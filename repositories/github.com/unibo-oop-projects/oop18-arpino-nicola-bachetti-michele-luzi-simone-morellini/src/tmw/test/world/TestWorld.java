package tmw.test.world;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;
import tmw.common.Dim2D;
import tmw.common.EntityFactoryImpl;
import tmw.common.P2d;
import tmw.common.V2d;
import tmw.controller.entities.MilkControllerImpl;
import tmw.model.entities.MilkEntity;
import tmw.model.entities.MilkEntityImpl;
import tmw.model.world.GameWorld;
import tmw.model.world.Level;

/**
 * This class is the test class for the gameWorld.
 * 
 * @version 1.2
 */

public class TestWorld {

    /**
     * Utilities to initialize test.
     *
     * @return
     */
    private GameWorld init() {
        return new Level(new Rectangle(800, 600));
    }

    /**
     * Initially world should be empty.
     */
    @Test
    public void testInitialConditions() {
        final GameWorld w = init();
        assertTrue(w.getEnemies().isEmpty());
        assertTrue(!w.getPlayer().isPresent());
        assertTrue(!w.getPlayerPosition().isPresent());
        assertTrue(w.getObstacles().isEmpty());
        assertTrue(w.getItems().isEmpty());
    }

    /**
     * Test for player entity.
     */
    @Test
    public void testPlayer() {
        final GameWorld w = init();

        w.insertPlayer((MilkEntity) new MilkEntityImpl(new P2d(0, 0), new V2d(0, 0),
                new Dim2D(800, 600)));

        assertTrue("player present", w.getPlayer().isPresent());
        assertTrue("pass", w.getPlayerPosition().isPresent());

        assertTrue("pass", w.getWorldArea().contains(
                new Point2D(w.getPlayer().get().getCurrentPos().getX(), w.getPlayer().get().getCurrentPos().getY())));

        w.getPlayer().get().setPos(new P2d(100000, 100000));

        assertFalse("pass", w.getWorldArea().contains(
                new Point2D(w.getPlayer().get().getCurrentPos().getX(), w.getPlayer().get().getCurrentPos().getY())));

    }

}
