package test.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;

import model.GameModel;
import model.GameModelImpl;
import model.entities.CharacterType;
import model.entities.Character;
import model.hitbox.HitboxCircle;
import model.map.StandardRoom;
import model.utils.Collisions;
import model.utils.Direction;

/**
 * 
 * Test the entity collisions.
 *
 */
public class TestCollision {

    private static final double DOUBLEDELTA = 0.001;
    private static final double MDELTA = 1;

    /**
     * Test if the entity collisions works.
     */
    @Test
    public void testEntityCollision() {
        final HitboxCircle c1 = new HitboxCircle(1, 1, CharacterType.PLAYER.getRadius());
        final HitboxCircle c2 = new HitboxCircle(0, 0, CharacterType.PLAYER.getRadius());
        Collection<Direction> c = Collisions.entityCollision(c2, c1);

        if (!c.containsAll(new ArrayList<>(Arrays.asList(Direction.LEFT, Direction.UP)))) {
            fail("Collision on the bottom-right corner doesn't work");
        }

        c1.changePosition(-1, -1);

        c = Collisions.entityCollision(c2, c1);

        if (!c.containsAll(new ArrayList<>(Arrays.asList(Direction.RIGHT, Direction.DOWN)))) {
            fail("Collision on the upper-left corner doesn't work");
        }

        c1.changePosition(1, -1);

        c = Collisions.entityCollision(c2, c1);

        if (!c.containsAll(new ArrayList<>(Arrays.asList(Direction.LEFT, Direction.DOWN)))) {
            fail("Collision on the upper-right corner doesn't work");
        }

        c1.changePosition(-1, 1);

        c = Collisions.entityCollision(c2, c1);

        if (!c.containsAll(new ArrayList<>(Arrays.asList(Direction.RIGHT, Direction.UP)))) {
            fail("Collision on the bottom-left corner doesn't work");
        }
    }

    /**
     * Test if the boundary collision work.
     */
    @Test
    public void testBoundaryCollision() {
        final GameModel gm = new GameModelImpl(false);
        final Character p = gm.getPlayer();
        boolean go = true;
        double lastY;
        double lastX;

        while (go) {
            lastY = p.getHitbox().getY();
            gm.update(new ArrayList<Direction>(Arrays.asList(Direction.UP, Direction.RIGHT)),
                    new ArrayList<Direction>(Arrays.asList(Direction.UP)), 1);
            if (lastY <= StandardRoom.getRoomSpace().getY() + p.getHitbox().getRadius()) {
                assertEquals(lastY, gm.getPlayer().getHitbox().getY(), DOUBLEDELTA);
                go = false;
            }
        }

        go = true;
        while (go) {
            lastX = p.getHitbox().getX();
            gm.update(new ArrayList<Direction>(Arrays.asList(Direction.RIGHT)),
                    new ArrayList<Direction>(Arrays.asList(Direction.UP)), MDELTA);
            if (lastX >= StandardRoom.getRoomSpace().getX() + StandardRoom.getRoomSpace().getWidth()
                    - p.getHitbox().getRadius()) {
                assertEquals(lastX, gm.getPlayer().getHitbox().getX(), DOUBLEDELTA);
                go = false;
            }
        }

        gm.update(new ArrayList<Direction>(Arrays.asList(Direction.DOWN)), new ArrayList<Direction>(Arrays.asList()),
                MDELTA);
        gm.update(new ArrayList<Direction>(Arrays.asList(Direction.DOWN)), new ArrayList<Direction>(Arrays.asList()),
                MDELTA);

        go = true;
        while (go) {
            lastX = p.getHitbox().getX();
            gm.update(new ArrayList<Direction>(Arrays.asList(Direction.LEFT)),
                    new ArrayList<Direction>(Arrays.asList(Direction.UP)), MDELTA);
            if (lastX <= StandardRoom.getRoomSpace().getX() + p.getHitbox().getRadius()) {
                assertEquals(lastX, gm.getPlayer().getHitbox().getX(), DOUBLEDELTA);
                go = false;
            }
        }

        gm.update(new ArrayList<Direction>(Arrays.asList(Direction.RIGHT)), new ArrayList<Direction>(Arrays.asList()),
                MDELTA);
        gm.update(new ArrayList<Direction>(Arrays.asList(Direction.RIGHT)), new ArrayList<Direction>(Arrays.asList()),
                MDELTA);

        go = true;
        while (go) {
            lastY = p.getHitbox().getY();
            gm.update(new ArrayList<Direction>(Arrays.asList(Direction.DOWN)),
                    new ArrayList<Direction>(Arrays.asList(Direction.UP)), MDELTA);
            if (lastY >= StandardRoom.getRoomSpace().getY() + StandardRoom.getRoomSpace().getHeight()
                    - p.getHitbox().getRadius()) {
                assertEquals(lastY, gm.getPlayer().getHitbox().getY(), DOUBLEDELTA);
                go = false;
            }
        }
    }

}
