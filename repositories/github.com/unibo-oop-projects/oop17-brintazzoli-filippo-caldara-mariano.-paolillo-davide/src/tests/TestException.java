package tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import controller.utility.CheckCollision;
import exceptions.TankOutOfBordersException;
import exceptions.TankWithTankException;
import exceptions.TankWithProjectileException;
import exceptions.ProjectileWithProjectileException;
import exceptions.ProjectileOutOfBordersException;
import exceptions.TankDeadException;
import model.Model;
import model.World;
import model.factory.FactoryTank;
import model.input.InputImpl;
import model.object.Projectile;
import model.object.ProjectileImpl;
import model.object.Tank;
import model.utility.Direction;
import model.utility.Pair;

/**
 * Test class for the game exceptions.
 */
public class TestException {

    private static final double PLAYER_INITIAL_POSITION = 30.0;

    /**
     * Tests the exception threw by a projectile when it bounces two times.
     */
    @Test(expected = IllegalStateException.class)
    public void testProjectileWithTwoBounces() {
        final Projectile projectile = new ProjectileImpl(new Pair<>(10.0, 10.0), 0, 4);
        projectile.bounce(Direction.RIGHT);
        projectile.bounce(Direction.LEFT);
    }

    /**
     * Tests the exception threw by a tank when it collides with the world borders.
     */
    @Test(expected = TankOutOfBordersException.class)
    public void testTankOutOfBorder() {
        final Tank playerTank = FactoryTank.createPlayer(new Pair<>(599.0, 1.0), 3);
        CheckCollision.initialize(new World());
        CheckCollision.tankWithBorders(playerTank);
    }

    /**
     * Tests the exception threw by a tank when it collides with another tank.
     */
    @Test(expected = TankWithTankException.class)
    public void testTankWithTank() {
        final Tank playerTank = FactoryTank.createPlayer(new Pair<>(30.0, 30.0), 3);
        final Tank enemyTank = FactoryTank.createEnemy(new Pair<>(25.0, 25.0), 3, 3, 4);
        CheckCollision.tankWithTank(playerTank, enemyTank);
    }

    /**
     * Tests the exception threw by a tank when it's dead.
     */
    @Test(expected = TankDeadException.class)
    public void testTankDead() {
        final Tank playerTank = FactoryTank.createPlayer(new Pair<>(PLAYER_INITIAL_POSITION, PLAYER_INITIAL_POSITION), 3);
        playerTank.damage(3);
        playerTank.update(new InputImpl());
    }

    /**
     * Tests the exception threw by a tank when it collides with a projectile.
     */
    @Test(expected = TankWithProjectileException.class)
    public void testTankWithProjectile() {
        final Model world = new World();
        world.configPlayerTank(new Pair<>(PLAYER_INITIAL_POSITION, PLAYER_INITIAL_POSITION), 3);
        final Projectile projectile = new ProjectileImpl(new Pair<>(26.0, 30.0), 0, 4);
        CheckCollision.initialize(world);
        final List<Projectile> projectiles = new ArrayList<>();
        projectiles.add(projectile);
        CheckCollision.tankWithProjectile(projectiles);
    }

    /**
     * Tests the exception threw by a projectile when it collides with a projectile.
     */
    @Test(expected = ProjectileWithProjectileException.class)
    public void testProjectileWithProjectile() {
        final Projectile projectile1 = new ProjectileImpl(new Pair<>(26.0, 30.0), 0, 4);
        final Projectile projectile2 = new ProjectileImpl(new Pair<>(25.0, 30.0), 0, 4);
        final List<Projectile> projectiles = new ArrayList<>();
        projectiles.add(projectile1);
        projectiles.add(projectile2);
        CheckCollision.projectileWithProjectile(projectiles);
    }

    /**
    * Tests the exception threw by a projectile when it collides with the world borders.
    */
   @Test(expected = ProjectileOutOfBordersException.class)
   public void testProjectileOutOfBounds() {
       final Projectile projectile1 = new ProjectileImpl(new Pair<>(599.0, 30.0), 0, 4);
       CheckCollision.initialize(new World());
       CheckCollision.projectileWithBorders(projectile1);
   }

}
