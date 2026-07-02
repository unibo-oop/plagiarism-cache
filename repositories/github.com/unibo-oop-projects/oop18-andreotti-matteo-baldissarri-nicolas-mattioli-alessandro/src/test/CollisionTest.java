package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Toolkit;
import java.util.List;
import java.util.stream.Collectors;

import org.dyn4j.collision.Collidable;
import org.dyn4j.collision.broadphase.BroadphaseDetector;
import org.dyn4j.collision.broadphase.Sap;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;

import controller.EntityFactory;
import model.entities.Position;
import model.palace.Window;
import model.palace.Window.StatusOfWindow;

/**
 * Test the collision between the stuntman and the other entities.
 */
public class CollisionTest {
    private static final double WINDOWS_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 12.5;

    private final EntityFactory factory = new EntityFactory();
    private final Collidable<BodyFixture> stunt = new Body();
    private final Collidable<BodyFixture> env = new Body();
    private final BroadphaseDetector<Collidable<BodyFixture>, BodyFixture> bp = new Sap<>();

    /**
     * Test collision between stuntman and vase.
     */
    @org.junit.Test
    public void testWithVase() {
        this.factory.getVases().get(0)
                .setPosition(new Position(this.factory.getStuntman().getPosition().getX(),
                        this.factory.getStuntman().getEnvironment().getShape().getVertices()[0].y
                                - this.factory.getVases().get(0).getHeight() + 10));
        stunt.addFixture(this.factory.getStuntman().getEnvironment().getShape());
        env.addFixture(this.factory.getVases().get(0).getEnvironment().getShape());
        assertFalse(bp.detect(stunt, env));
        this.factory.getVases().get(0).moveDown();
        this.factory.getVases().get(0).moveDown();
        this.factory.getVases().get(0).moveDown();
        this.factory.getVases().get(0).moveDown();
        assertTrue(bp.detect(stunt, env));
    }

    /**
     * Test collision between stuntman and hawk.
     */
    @org.junit.Test
    public void testWithHawk() {
        this.factory.getHawks().get(0)
                .setPosition(new Position(this.factory.getStuntman().getPosition().getX() - WINDOWS_WIDTH,
                        this.factory.getStuntman().getPosition().getY()));
        stunt.addFixture(this.factory.getStuntman().getEnvironment().getShape());
        env.addFixture(this.factory.getHawks().get(0).getEnvironment().getShape());
        assertFalse(bp.detect(stunt, env));
        for (int i = 0; i <= 10; i++) {
            this.factory.getHawks().get(0).moveRight();
        }
        assertTrue(bp.detect(stunt, env));
    }

    /**
     * Test collision between stuntman and a closed window.
     */
    @org.junit.Test
    public void testWithWindow() {
        // The stuntman is initially situated between first and second floor
        List<Window> wins = this.factory.getPalace().getFloors().get(0).getWindows();
        wins.stream().filter(win -> win.getStatus().equals(StatusOfWindow.CLOSE)).forEach(
                win -> assertFalse(this.factory.getStuntman().getEnvironment().getShape().contains(win.getPosition())));

        wins = wins.stream().peek(win -> win.changeStatus()).collect(Collectors.toList());
        wins.stream().filter(win -> win.getStatus().equals(StatusOfWindow.CLOSE)).forEach(
                win -> assertFalse(this.factory.getStuntman().getEnvironment().getShape().contains(win.getPosition())));

        wins = wins.stream().peek(win -> win.changeStatus()).collect(Collectors.toList());
        wins.stream().filter(win -> this.factory.getStuntman().getEnvironment().getShape().contains(win.getPosition()))
                .forEach(win -> assertTrue(win.getStatus().equals(StatusOfWindow.CLOSE)));
    }

    /**
     * Test collision between stuntman and a bonus.
     */
    @org.junit.Test
    public void testWithBonus() {
        this.factory.getBonus().get(0)
                .setPosition(new Position(
                        this.factory.getPalace().getFloors().get(0).getWindows().get(0).getPosition().getX(),
                        this.factory.getPalace().getFloors().get(0).getWindows().get(0).getPosition().getY()));
        this.stunt.addFixture(this.factory.getStuntman().getEnvironment().getShape());
        this.env.addFixture(this.factory.getBonus().get(0).getEnvironment().getShape());
        assertFalse(bp.detect(this.stunt, this.env));
        this.factory.getStuntman().moveLeft();
        this.factory.getStuntman().moveLeft();
        this.factory.getStuntman().moveLeft();
        assertTrue(bp.detect(this.stunt, this.env));
    }
}
