package tests;

import static org.junit.Assert.assertTrue;

import java.io.InputStream;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import static javafx.scene.input.KeyCode.A;
import static javafx.scene.input.KeyCode.D;
import static javafx.scene.input.KeyCode.W;
import static javafx.scene.input.KeyCode.S;

import javafx.stage.Stage;
import javafx.application.Application;
import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import boxhead.model.entities.Player;
import boxhead.model.entities.gun.Gun;
import boxhead.model.entities.gun.GunFactory;
import boxhead.model.entities.utils.Collision;
import boxhead.model.entities.utils.Direction;
import boxhead.controller.entities.PlayerInput;

public class PlayerTest {
	private static final int MAX_HP = 100;
    private static final int PLAYER_BB = 20;
    private static final Point2D PLAYER_POSITION = new Point2D(10, 10);
    private static final BoundingBox OBSTACLE = new BoundingBox(100, 100, 15, 15);
    private static final BoundingBox ZOMBIE = new BoundingBox(250, 100, 15, 15);
    private final Player player = new Player();
    private final Set<KeyCode> keys = new HashSet<>();
    
    @Test
    	public void testPlayerMovement() {
        /*
         * Test initial player state.
         */
        assertTrue(Integer.valueOf(this.player.getHealth()).equals(MAX_HP));
        

        /*
         * Test of right movement of player
         */
        player.setPosition(PLAYER_POSITION);
        final Point2D rightPosition = new Point2D(PLAYER_POSITION.getX() + 1, PLAYER_POSITION.getY());
        keys.clear();
        keys.add(D);
        player.setSpeed(PlayerInput.calculateDirection(keys).traduce());
        player.update();
        assertTrue(player.getPosition().equals(rightPosition));

        /*
         * Test of left movement of player
         */
        player.setPosition(PLAYER_POSITION);
        final Point2D leftPosition = new Point2D(PLAYER_POSITION.getX() - 1, PLAYER_POSITION.getY());
        keys.clear();
        keys.add(A);
        player.setSpeed(PlayerInput.calculateDirection(keys).traduce());
        player.update();
        assertTrue(player.getPosition().equals(leftPosition));

        /*
         * Test of up movement of player
         */
        player.setPosition(PLAYER_POSITION);
        final Point2D upPosition = new Point2D(PLAYER_POSITION.getX(), PLAYER_POSITION.getY() + 1);
        keys.clear();
        keys.add(W);
        player.setSpeed(PlayerInput.calculateDirection(keys).traduce());
        player.update();
        assertTrue(player.getPosition().equals(upPosition));

        /*
         * Test of down movement of player
         */
        player.setPosition(PLAYER_POSITION);
        final Point2D downPosition = new Point2D(PLAYER_POSITION.getX(), PLAYER_POSITION.getY() - 1);
        keys.clear();
        keys.add(S);
        player.setSpeed(PlayerInput.calculateDirection(keys).traduce());
        player.update();
        assertTrue(player.getPosition().equals(downPosition));

        /*
         * Test multiple movement
         */
        keys.clear();
        keys.add(W);
        keys.add(S);
        keys.add(A);
        keys.add(W);
        final Point2D newPosition = new Point2D(PLAYER_POSITION.getX() - 1, PLAYER_POSITION.getY() + 1);
        player.setSpeed(PlayerInput.calculateDirection(keys).traduce());
        final Direction direction = PlayerInput.calculateDirection(keys);
        
        
        player.update();
        assertTrue(player.getPosition().equals(newPosition));
    }
    
    @Test
    public void testPlayerCollision() {
        player.setBoundingBox(PLAYER_BB, PLAYER_BB);

        /*
         * Collision with obstacles
         */
        final Set<BoundingBox> obstacles = new HashSet<>();
        player.setPosition(new Point2D(OBSTACLE.getMinX(), OBSTACLE.getMinY()));
        obstacles.add(OBSTACLE);
        player.setWalls(obstacles);
        player.checkCollision(Direction.NULL);
        assertTrue(player.getSpeed().equals(Point2D.ZERO));

        /*
         * Collision with zombies
         */
        player.setPosition(new Point2D(ZOMBIE.getMinX(), ZOMBIE.getMinY()));
        assertTrue(Collision.isColliding(player.getBoundingBox(), ZOMBIE));
        if (Collision.isColliding(player.getBoundingBox(), ZOMBIE)) {
            player.takeDamage(1);
        }
        assertTrue(Integer.valueOf(this.player.getHealth()).equals(MAX_HP - 1));
    }
    
    @Test
    public void gunTest(){
    	player.getCurrentGun();
    	player.unlockGun(new GunFactory().getGun(player.getPosition(),Gun.GunType.UZI));
    	player.previousGun();
   	player.nextGun();
    }
    
}
