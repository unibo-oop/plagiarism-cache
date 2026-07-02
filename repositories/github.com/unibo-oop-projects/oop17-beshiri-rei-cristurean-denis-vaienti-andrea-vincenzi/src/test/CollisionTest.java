package test;

import static model.animated.EntityStats.STATIC_ENEMY;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import model.animated.Animated;
import model.animated.BulletImpl;
import model.hitbox.CircleHitBox;
import model.hitbox.RectangularHitBox;
import model.strategy.BulletMovement;
import model.utility.CollisionUtil;
import utility.Command;

/**
 * Test class for all types of collision.
 *
 */
class CollisionTest {

    @Test
    public void collisionBetweenCicles() {
        final CircleHitBox player = new CircleHitBox(10, 10, 2);
        final CircleHitBox bullet = new CircleHitBox(12, 10, 0.2);
        assertFalse(CollisionUtil.entityCollision(player, bullet).isEmpty());
    }

    @Test
    public void checkBlockedCommand() {
        final CircleHitBox player = new CircleHitBox(1, 1, 2);
        final CircleHitBox bullet = new CircleHitBox(1, 3, 0.2);
        assertTrue(CollisionUtil.entityCollision(bullet, player).size() == 1);
        assertTrue(CollisionUtil.entityCollision(bullet, player).contains(Command.DOWN));
        final CircleHitBox player1 = new CircleHitBox(1, 1, 2);
        final CircleHitBox bullet1 = new CircleHitBox(3, 1.5, 0.2);
        assertTrue(CollisionUtil.entityCollision(bullet1, player1).size() == 2);
        assertTrue(CollisionUtil.entityCollision(bullet1, player1).contains(Command.DOWN)
                && CollisionUtil.entityCollision(bullet1, player1).contains(Command.RIGHT));
        final CircleHitBox player2 = new CircleHitBox(3, 3, 2);
        final CircleHitBox bullet2 = new CircleHitBox(2, 2, 0.2);
        assertTrue(CollisionUtil.entityCollision(bullet2, player2).size() == 2);
        assertTrue(CollisionUtil.entityCollision(bullet2, player2).contains(Command.UP)
                && CollisionUtil.entityCollision(bullet2, player2).contains(Command.LEFT));
    }

    @Test
    public void collisionBetweenBulletAndPlayer() {
        final CircleHitBox player = new CircleHitBox(0, 0, 2);
        final CircleHitBox sender = new CircleHitBox(3, 3, 0.2);
        final double angle = Math.toDegrees(Math.atan2(player.getY() - sender.getY(), player.getX() - sender.getX()));
        final Animated bullet = new BulletImpl(sender, 1, new BulletMovement(angle), STATIC_ENEMY.getBulletRange(),
                null, STATIC_ENEMY.getBulletDamage());
        boolean collisionDetected = false;
        for (int i = 0; i < 10; i++) {
            if (!CollisionUtil.entityCollision(player, (CircleHitBox) bullet.getHitBox()).isEmpty()) {
                collisionDetected = true;
                break;
            }
            bullet.update(1);
        }
        assertTrue(collisionDetected);
    }

    @Test
    public void collisionBetweenEntityAndRoomBorders() {
        // TOP BORDER COLLISION.
        final RectangularHitBox room = new RectangularHitBox(0, 0, 600, 800);
        final CircleHitBox player = new CircleHitBox(3, 1.9, 2);
        assertTrue(CollisionUtil.checkBoundaryCollision(player, room));

        // BOTTOM COLLISION BORDER.
        final CircleHitBox enemy = new CircleHitBox(400, 600, 2);
        assertTrue(CollisionUtil.checkBoundaryCollision(enemy, room));

        // RIGHT COLLISION BORDER.
        final CircleHitBox enemy2 = new CircleHitBox(800, 300, 2);
        assertTrue(CollisionUtil.checkBoundaryCollision(enemy2, room));

        // LEFT COLLISION BORDER
        final CircleHitBox enemy3 = new CircleHitBox(0, 300, 2);
        assertTrue(CollisionUtil.checkBoundaryCollision(enemy3, room));

        // NO COLLISION.
        final CircleHitBox boss = new CircleHitBox(400, 300, 10);
        assertFalse(CollisionUtil.checkBoundaryCollision(boss, room));
    }

    @Test
    public void collisionBetweenPlayerAndDoor() {
        final RectangularHitBox rigthDoor = new RectangularHitBox(780, 270, 30, 20);
        final CircleHitBox player = new CircleHitBox(778, 200, 2);
        assertFalse(CollisionUtil.rectPlayerCollision(player, rigthDoor));

        // COLLISION WITH RIGHT DOOR.
        final double deltaY = 80;
        player.changePosition(player.getX(), player.getY() + deltaY);
        assertTrue(CollisionUtil.rectPlayerCollision(player, rigthDoor));

        // COLLISION WITH LEFT DOOR.
        final RectangularHitBox leftDoor = new RectangularHitBox(0, 300, 30, 20);
        final CircleHitBox enemy = new CircleHitBox(2, 298, 2);
        assertTrue(CollisionUtil.rectPlayerCollision(enemy, leftDoor));
    }

    @Test
    public void collisionWithHearth() {
        final CircleHitBox hearth = new CircleHitBox(400, 600, 0.5);
        final CircleHitBox player = new CircleHitBox(400, 598, 2);
        assertFalse(CollisionUtil.entityCollision(hearth, player).isEmpty());
        assertTrue(CollisionUtil.entityCollision(hearth, player).size() == 1);
        assertTrue(CollisionUtil.entityCollision(hearth, player).contains(Command.DOWN));
    }
}
