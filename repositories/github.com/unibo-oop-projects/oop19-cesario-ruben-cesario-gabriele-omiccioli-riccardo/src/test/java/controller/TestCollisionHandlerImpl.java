package controller;

import java.util.Set;

import org.junit.jupiter.api.Test;

import controller.stagehandler.collisionhandler.CollisionHandler;
import controller.stagehandler.collisionhandler.CollisionHandlerImpl;
import model.Stage;
import model.StageImpl;
import model.entity.CollidableEntity;
import model.entity.CollidableEntityImpl;
import model.entity.EntityID;
import model.ship.EnemyShip;
import model.ship.PlayerShip;
import model.ship.PlayerShip.PlayerScore;
import model.ship.basic.BasicSpaceShipFactory;
import model.weapon.Weapon;
import model.weapon.Weapon.Projectile;
import model.weapon.basic.BasicWeapon;
import utilities.math.Point2DImpl;

/**
 * This set of tests checks the correct implementation of the CollisionHandler.
 */
public class TestCollisionHandlerImpl {

    private final CollisionHandler collisionHandler = new CollisionHandlerImpl();
    private final BasicSpaceShipFactory factory = new BasicSpaceShipFactory();
    private final Weapon weapon = new BasicWeapon();

    /**
     * This test will check if the collision handler can properly detect collisions.
     */
    @Test
    public void testCollisionDetection() {
        //TEST INITIALIZATION
        //--------------------------------------------------|position               |radius
        final CollidableEntity e1 = new CollidableEntityImpl(new Point2DImpl(0,  0), 3);
        final CollidableEntity e2 = new CollidableEntityImpl(new Point2DImpl(3,  0), 3);
        final CollidableEntity e3 = new CollidableEntityImpl(new Point2DImpl(6,  0), 3);
        final CollidableEntity e4 = new CollidableEntityImpl(new Point2DImpl(12, 0), 1);
        //[---e1(3){---]e2(3)[---}e3(3)---]--[-e4(1)-]  (different brackets highlight different hit-boxes)

        //TEST IMPLEMENTATION
        //an entity shouldn't be able to collide with itself
        assert !collisionHandler.areColliding(e1, e1);

        //expected collisions: (e1,e2) (e2,e3)
        assert collisionHandler.areColliding(e1, e2);
        assert collisionHandler.areColliding(e2, e3);
        //checking complementary collisions
        assert collisionHandler.areColliding(e2, e1);
        assert collisionHandler.areColliding(e3, e2);

        //not expected collisions: (e1, e3) (e1,e4) (e2,e4) (e3,e4)
        assert !collisionHandler.areColliding(e1, e3);
        assert !collisionHandler.areColliding(e1, e4);
        assert !collisionHandler.areColliding(e2, e4);
        assert !collisionHandler.areColliding(e3, e4);
    }

    /**
     * This test will check if the collision handler can properly reposition two spaceships after a collision.
     */
    @Test
    public void testSpaceshipReposition() {
        //TEST INITIALIZATION
        final Stage stage = new StageImpl();
        final Point2DImpl origin = new Point2DImpl(-10, 0);
        final Point2DImpl origin1 = new Point2DImpl(0, 0);
        final Point2DImpl origin2 = new Point2DImpl(3, 0);
        final Point2DImpl origin3 = new Point2DImpl(12, 0);
        final Point2DImpl origin4 = new Point2DImpl(20, 0);
        //The collision handler will only work with a proper stage (a stage with a player). 
        //This player won't be used for anything else in this test.
        final PlayerShip player = factory.buildPlayerShip(EntityID.SPACESHIP_BASIC, origin, PlayerScore.DEFAULT_NAME);
        final EnemyShip enemy1 = factory.buildEnemyShip(EntityID.SPACESHIP_BASIC, origin1);
        final EnemyShip enemy2 = factory.buildEnemyShip(EntityID.SPACESHIP_BASIC, origin2);
        final EnemyShip enemy3 = factory.buildEnemyShip(EntityID.SPACESHIP_BASIC, origin3);
        final EnemyShip enemy4 = factory.buildEnemyShip(EntityID.SPACESHIP_BASIC, origin4);
        //N.B.: an EntityID.SPACESHIP_BASIC has a radius of 2
        //[--en1-{-]-en2--}-----[--en3--]----[--en4--]

        //TEST IMPLEMENTATION
        //Spaceships should reposition only after a collision with another spaceship
        //expected: en1&en2 should collide and reposition; 
        //          en3&en4 shouldn't collide, therefore they shouldn't reposition
        stage.spawnPlayer(player);
        stage.spawnEnemies(Set.of(enemy1, enemy2, enemy3, enemy4));
        assert this.collisionHandler.areColliding(enemy1, enemy2);
        assert !this.collisionHandler.areColliding(enemy3, enemy4);

        this.collisionHandler.checkCollisions(stage);
        assert !origin1.equals(enemy1.getPosition());
        assert !origin2.equals(enemy2.getPosition());
        assert origin3.equals(enemy3.getPosition());
        assert origin4.equals(enemy4.getPosition());

        //After a collision, two spaceships shouldn't be colliding anymore.
        assert !this.collisionHandler.areColliding(enemy1, enemy2);
    }

    /**
     * This test will check if the collision handler can properly reposition a spaceship colliding
     * with a projectile after a collision.
     */
    @Test
    public void testProjectileReposition() {
        //TEST INITIALIZATION
        final Stage stage = new StageImpl();
        final Point2DImpl origin = new Point2DImpl(0, 0);
        final PlayerShip player = factory.buildPlayerShip(EntityID.SPACESHIP_BASIC, origin, PlayerScore.DEFAULT_NAME);
        final Projectile projectile = weapon.shoot(origin, 0).iterator().next();
        //[--{pl==pr}--]   (the projectile is spawned on top of the player and has a smaller hitbox)

        //TEST IMPLEMENTATION
        //Spaceships shouldn't reposition after a collision with a projectile, viceversa.
        //expected: pl&pr should collide but they shouldn't reposition
        stage.spawnPlayer(player);
        stage.spawnProjectile(projectile);
        assert this.collisionHandler.areColliding(player, projectile);

        this.collisionHandler.checkCollisions(stage);
        assert origin.equals(player.getPosition());
        assert origin.equals(projectile.getPosition());

        //After a collision, a spaceship and a projectile should still be colliding.
        assert this.collisionHandler.areColliding(player, projectile);
    }

}
