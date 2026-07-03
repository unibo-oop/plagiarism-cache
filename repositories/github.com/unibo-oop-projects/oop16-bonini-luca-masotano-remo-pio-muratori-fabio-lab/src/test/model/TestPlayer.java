package test.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;

import model.GameModelImpl;
import model.entities.Bullet;
import model.entities.BulletType;
import model.entities.ItemImpl;
import model.entities.ItemType;
import model.entities.Character;
import model.hitbox.HitboxImpl;
import model.map.StandardRoom;
import model.utils.Direction;
import model.GameModel;

/**
 * 
 * Tests some behavior of the player class.
 *
 */
public class TestPlayer {

    private static final double DOUBLEDELTA = 0.001;
    private static final double MDELTA = 1;

    /**
     * Test the behavior of the Player class when it's moving.
     */
    @Test
    public void testMovement() {
        final double angleTest3 = 45;
        final double initX = StandardRoom.getRoomSpace().getX() + StandardRoom.getRoomSpace().getWidth() / 2;
        final double initY = StandardRoom.getRoomSpace().getY() + StandardRoom.getRoomSpace().getHeight() / 2;
        GameModel gm = new GameModelImpl(false);

        gm.update(new ArrayList<Direction>(Arrays.asList(Direction.UP)), new ArrayList<>(), MDELTA);
        assertEquals(initY - gm.getPlayer().getSteps(), gm.getPlayer().getHitbox().getY(), DOUBLEDELTA);

        gm.update(new ArrayList<Direction>(Arrays.asList(Direction.DOWN)),
                new ArrayList<Direction>(Arrays.asList(Direction.UP)), MDELTA);
        assertEquals(initY, gm.getPlayer().getHitbox().getY(), DOUBLEDELTA);

        gm.update(new ArrayList<Direction>(Arrays.asList(Direction.RIGHT)),
                new ArrayList<Direction>(Arrays.asList(Direction.UP)), MDELTA);
        assertEquals(initX + gm.getPlayer().getSteps(), gm.getPlayer().getHitbox().getX(), DOUBLEDELTA);

        gm = new GameModelImpl(false);
        gm.update(new ArrayList<Direction>(Arrays.asList(Direction.UP, Direction.RIGHT)),
                new ArrayList<Direction>(Arrays.asList(Direction.UP)), MDELTA);
        assertEquals(initY - gm.getPlayer().getSteps() * Math.sin(angleTest3 * Math.PI / 180),
                gm.getPlayer().getHitbox().getY(), DOUBLEDELTA);
        assertEquals(initX + gm.getPlayer().getSteps() * Math.sin(angleTest3 * Math.PI / 180),
                gm.getPlayer().getHitbox().getX(), DOUBLEDELTA);

        gm = new GameModelImpl(false);

        gm.update(
                new ArrayList<Direction>(Arrays.asList(Direction.UP, Direction.RIGHT, Direction.DOWN, Direction.LEFT)),
                new ArrayList<Direction>(Arrays.asList(Direction.UP)), MDELTA);
        assertEquals(initY, gm.getPlayer().getHitbox().getY(), DOUBLEDELTA);
        assertEquals(initX, gm.getPlayer().getHitbox().getX(), DOUBLEDELTA);
    }

    /**
     * Test if the player can shoot.
     */
    @Test
    public void testShootAndFireRate() {
        final GameModel gm = new GameModelImpl(false);
        final double fireRate = gm.getPlayer().getFireRate();
        /*
         * Because there is a delay of one frame before the player can shoot, we
         * need two updates
         */
        gm.update(new ArrayList<Direction>(Arrays.asList()), new ArrayList<Direction>(Arrays.asList(Direction.UP)),
                MDELTA);
        gm.update(new ArrayList<Direction>(Arrays.asList()), new ArrayList<Direction>(Arrays.asList(Direction.UP)),
                MDELTA);

        assertEquals(1, gm.getPlayerBullets().size());

        gm.clearBullets();

        for (double i = fireRate; i > 0; i -= MDELTA) {
            gm.update(new ArrayList<Direction>(Arrays.asList()), new ArrayList<Direction>(Arrays.asList(Direction.UP)),
                    MDELTA);
            if (gm.getPlayerBullets().size() != 0) {
                fail("The fire rate doesn't work");
            }
        }
    }

    /**
     * Test if the player can pick up an item.
     */
    @Test
    public void testItemPickUp() {
        final GameModel gm = new GameModelImpl(false);
        final Character p = gm.getPlayer();
        final double life = p.getLife();
        final double fireRate = p.getFireRate();
        final double bulletDmg = BulletType.BULLET_PLAYER.getDamage();
        final double bulletRange = BulletType.BULLET_PLAYER.getRange();
        final Collection<ItemType> c = new ArrayList<>(
                Arrays.asList(ItemType.FIREUP, ItemType.HALF_HEART, ItemType.DAMAGEUP, ItemType.RANGEUP));
        Bullet b;

        c.forEach(i -> {
            p.pickUpItem(new ItemImpl(new HitboxImpl(0, 0), i));
        });

        for (int i = 0; i <= 1; i++) {
            gm.update(new ArrayList<>(), new ArrayList<>(Arrays.asList(Direction.UP)), MDELTA);
        }

        b = (Bullet) gm.getPlayerBullets().toArray()[0];
        if (life == p.getLife()) {
            fail("The item HALF_HEART doesn't modifiy any property");
        }
        if (fireRate == p.getFireRate()) {
            fail("The item FIREUP doesn't modifiy any property");
        }
        if (bulletRange == b.getRange()) {
            fail("The item RANGEUP doesn't modifiy any property");
        }
        if (bulletDmg == b.getCollisionDamage()) {
            fail("The item DAMAGEUP doesn't modifiy any property");
        }

    }

    /**
     * Test if the player can be damaged.
     */
    @Test
    public void testDamage() {
        final GameModel gm = new GameModelImpl(false);
        final Character p = gm.getPlayer();
        final double life = p.getLife();

        p.takeDamage(1);

        if (life <= p.getLife()) {
            fail("The player cannot be damaged.");
        }

    }

}
