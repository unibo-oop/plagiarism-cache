package tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import javafx.geometry.Point2D;
import zombieversity.model.entities.Player;
import zombieversity.model.entities.PlayerImpl;
import zombieversity.model.entities.zombie.Zombie;
import zombieversity.model.world.Camera;
import zombieversity.model.world.CameraImpl;


/**
 * Tests the camera.
 */
public class TestCamera {

    private final Player p = new PlayerImpl();

    private static final int ENTTY_W = 30;
    private static final int ENTITY_H = 30;

    private static final double MAP_WIDTH = 1000;
    private static final double MAP_HEIGHT = 1000;
    private static final double CAM_WIDTH = 100;
    private static final double CAM_HEIGHT = 100;

    private final Point2D stepMapSize = new Point2D(MAP_WIDTH, MAP_HEIGHT);
    private final Point2D stepCamSize = new Point2D(CAM_WIDTH, CAM_HEIGHT);

    private final Zombie z = new Zombie(new Point2D(150, 150), 1, 20, 20);
    private Camera cam;

    /**
     * Test camera movement.
     */
    @Test
    public final void testMove() {
       cam = new CameraImpl(0, 0, 1000, 1000, 100, 100);

       cam.move(new Point2D(1, 0));
       cam.move(new Point2D(0, 1));
       assertEquals(new Point2D(1, 1), cam.getOffset());

       cam.move(new Point2D(-1, 0));
       cam.move(new Point2D(0, -1));
       assertEquals(new Point2D(0, 0), cam.getOffset());

    }

    /**
     * Test border limit.
     */
    @Test
    public final void testLimit() {
        cam = new CameraImpl(0, 0, MAP_WIDTH, MAP_HEIGHT, CAM_WIDTH, CAM_HEIGHT);

        cam.move(new Point2D(-1, -1));
        assertNotEquals(new Point2D(-1, -1), cam.start());
        assertEquals(new Point2D(0, 0), cam.start());

        cam.move(this.stepMapSize);
        assertEquals(this.stepMapSize, cam.end());
        assertEquals(stepMapSize.subtract(stepCamSize), cam.start());
    }

    /**
     * Test camera position based on entities.
     */
    @Test
    public final void testCenterBasedOnEntity() {
        cam = new CameraImpl(0, 0, MAP_WIDTH, MAP_HEIGHT, CAM_WIDTH, CAM_HEIGHT);

        z.setBBox(ENTTY_W, ENTITY_H);
        cam.centerOnEntity(z);
        assertEquals(z.getPosition().subtract(new Point2D(ENTTY_W / 2, ENTITY_H / 2)), cam.midpoint());

        p.setPosition(Point2D.ZERO);
        cam.centerOnEntity(p);
        assertNotEquals(Point2D.ZERO, cam.getCenter());
    }

    /**
     * Test camera position based on a position.
     */
    @Test
    public final void testCenter() {
        cam = new CameraImpl(0, 0, MAP_WIDTH, MAP_HEIGHT, CAM_WIDTH, CAM_HEIGHT);

        p.setPosition(cam.getCenter());
        assertEquals(new Point2D(MAP_WIDTH / 2, MAP_HEIGHT / 2), p.getPosition());
    }
}
