package tests;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import static javafx.scene.input.KeyCode.A;
import static javafx.scene.input.KeyCode.D;
import static javafx.scene.input.KeyCode.W;
import static javafx.scene.input.KeyCode.S;

import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import zombieversity.controller.entities.PlayerKeyboardInputUtils;
import zombieversity.model.collisions.CollisionsUtils;
import zombieversity.model.entities.Player;
import zombieversity.model.entities.PlayerImpl;

public class TestPlayer {

    private static final int MAX_HP = 100;
    private static final int PLAYER_BB = 20;
    private static final Point2D PLAYER_POSITION = new Point2D(10, 10);
    private static final BoundingBox OBSTACLE = new BoundingBox(100, 100, 15, 15);
    private static final BoundingBox ZOMBIE = new BoundingBox(250, 100, 15, 15);
    private final Player player = new PlayerImpl();
    private final Set<KeyCode> keys = new HashSet<>();

    /**
     * Test player's position.
     */
    @Test
    public void testPlayerMovement() {
        /*
         * Test initial player state.
         */
        assertTrue(Integer.valueOf(this.player.getLifeManager().getHP()).equals(MAX_HP));

        /*
         * Test of right movement of player
         */
        player.setPosition(PLAYER_POSITION);
        final Point2D rightPosition = new Point2D(PLAYER_POSITION.getX() + 1, PLAYER_POSITION.getY());
        keys.clear();
        keys.add(D);
        player.setVelocity(PlayerKeyboardInputUtils.calculateVelocity(keys));
        player.update();
        assertTrue(player.getPosition().equals(rightPosition));

        /*
         * Test of left movement of player
         */
        player.setPosition(PLAYER_POSITION);
        final Point2D leftPosition = new Point2D(PLAYER_POSITION.getX() - 1, PLAYER_POSITION.getY());
        keys.clear();
        keys.add(A);
        player.setVelocity(PlayerKeyboardInputUtils.calculateVelocity(keys));
        player.update();
        assertTrue(player.getPosition().equals(leftPosition));

        /*
         * Test of up movement of player
         */
        player.setPosition(PLAYER_POSITION);
        final Point2D upPosition = new Point2D(PLAYER_POSITION.getX(), PLAYER_POSITION.getY() - 1);
        keys.clear();
        keys.add(W);
        player.setVelocity(PlayerKeyboardInputUtils.calculateVelocity(keys));
        player.update();
        assertTrue(player.getPosition().equals(upPosition));

        /*
         * Test of down movement of player
         */
        player.setPosition(PLAYER_POSITION);
        final Point2D downPosition = new Point2D(PLAYER_POSITION.getX(), PLAYER_POSITION.getY() + 1);
        keys.clear();
        keys.add(S);
        player.setVelocity(PlayerKeyboardInputUtils.calculateVelocity(keys));
        player.update();
        assertTrue(player.getPosition().equals(downPosition));

        /*
         * Test multiple movement
         */
        keys.clear();
        keys.add(W);
        keys.add(W);
        keys.add(S);
        keys.add(A);
        final Point2D newPosition = new Point2D(PLAYER_POSITION.getX() - 1, PLAYER_POSITION.getY() + 1);
        player.setVelocity(PlayerKeyboardInputUtils.calculateVelocity(keys));
        player.update();
        assertTrue(player.getPosition().equals(newPosition));
    }

    /**
     * Test player's collisions.
     */
    @Test
    public void testPlayerCollision() {
        player.setBBox(PLAYER_BB, PLAYER_BB);

        /*
         * Collision with obstacles
         */
        final Set<BoundingBox> obstacles = new HashSet<>();
        player.setPosition(new Point2D(OBSTACLE.getMinX(), OBSTACLE.getMinY()));
        obstacles.add(OBSTACLE);
        player.setObstacles(obstacles);
        player.checkCollision(Point2D.ZERO);
        assertTrue(player.getVelocity().equals(Point2D.ZERO));

        /*
         * Collision with zombies
         */
        player.setPosition(new Point2D(ZOMBIE.getMinX(), ZOMBIE.getMinY()));
        assertTrue(CollisionsUtils.isColliding(player.getBBox(), ZOMBIE));
        if (CollisionsUtils.isColliding(player.getBBox(), ZOMBIE)) {
            player.hitPlayer(1);
        }
        assertTrue(Integer.valueOf(this.player.getLifeManager().getHP()).equals(MAX_HP - 1));
    }
}
