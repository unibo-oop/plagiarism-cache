package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static model.animated.EntityStats.PLAYER;
import static model.animated.EntityStats.STATIC_ENEMY;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

import model.ai.BasicAI;
import model.animated.AbstractCharacter;
import model.animated.Animated;
import model.animated.EnemyImpl;
import model.animated.PlayerImpl;
import model.hitbox.CircleHitBox;
import model.strategy.PlayerMovement;
import model.strategy.PlayerProjectile;
import model.strategy.SimplyDirectionMovement;
import model.utility.ModelUtility;
import utility.Command;

/**
 * Class test for entities movement types.
 *
 */
class EntityMovementTest {

    @Test
    public void playerMovementTest() {

        final Animated player = new PlayerImpl(1, PLAYER.getLife(), new CircleHitBox(0, 0, 2),
                new BasicAI(new PlayerMovement(), new PlayerProjectile()), null, PLAYER.getShotRatio(),
                PLAYER.getBulletRadius(), PLAYER.getBulletVel(), PLAYER.getBulletRange(), PLAYER.getBulletDamage());

        // RIGHT MOVEMENT.
        final double oldX = player.getHitBox().getX();
        final double oldY = player.getHitBox().getY();
        ModelUtility.updateListMovementCommand(Arrays.asList(Command.RIGHT));
        player.update(1);
        assertEquals(oldX + 1, player.getHitBox().getX());
        assertEquals(oldY, player.getHitBox().getY());

        // UP MOVEMENT.
        final double oldXUp = player.getHitBox().getX();
        final double oldYUp = player.getHitBox().getY();
        ModelUtility.updateListMovementCommand(Arrays.asList(Command.UP));
        player.update(1);
        assertEquals(oldXUp, player.getHitBox().getX());
        assertEquals(oldYUp - 1, player.getHitBox().getY());

        // LEFT MOVEMENT.
        final double oldXLeft = player.getHitBox().getX();
        final double oldYLeft = player.getHitBox().getY();
        ModelUtility.updateListMovementCommand(Arrays.asList(Command.LEFT));
        player.update(1);
        assertEquals(oldXLeft - 1, player.getHitBox().getX());
        assertEquals(oldYLeft, Math.round(player.getHitBox().getY()));

        // DOWN MOVEMENT.
        final double oldXDown = player.getHitBox().getX();
        final double oldYDown = player.getHitBox().getY();
        ModelUtility.updateListMovementCommand(Arrays.asList(Command.DOWN));
        player.update(1);
        assertEquals(oldXDown, Math.round(player.getHitBox().getX()));
        assertEquals(oldYDown + 1, player.getHitBox().getY());
    }

    @Test
    public void enemySimpleMovementTest() {
        // UP MOVEMENT.
        final AbstractCharacter enemy = new EnemyImpl(1, STATIC_ENEMY.getLife(), new CircleHitBox(0, 0, 2),
                new BasicAI(new SimplyDirectionMovement(Command.UP), null), STATIC_ENEMY.getPoints(), null,
                STATIC_ENEMY.getShotRatio(), null, STATIC_ENEMY.getBulletRadius(), STATIC_ENEMY.getBulletVel(),
                STATIC_ENEMY.getBulletRange(), STATIC_ENEMY.getBulletDamage());
        enemy.update(1);
        assertEquals(0, enemy.getHitBox().getX());
        assertEquals(-1, enemy.getHitBox().getY());

        // REVERSE (DOWN) MOVEMENT.
        enemy.getAI().setMovementStrategy(new SimplyDirectionMovement(Command.UP.getOppositeCommand()));
        enemy.update(1);
        assertEquals(0, enemy.getHitBox().getX());
        assertEquals(0, enemy.getHitBox().getY());

        // RIGHT MOVEMENT.
        enemy.getAI().setMovementStrategy(new SimplyDirectionMovement(Command.RIGHT));
        enemy.update(1);
        assertEquals(1, enemy.getHitBox().getX());
        assertEquals(0, enemy.getHitBox().getY());

        // REVERSE (LEFT) MOVEMENT.
        enemy.getAI().setMovementStrategy(new SimplyDirectionMovement(Command.RIGHT.getOppositeCommand()));
        enemy.update(1);
        assertEquals(0, enemy.getHitBox().getX());
        assertEquals(0, enemy.getHitBox().getY());
    }
}
