package tests;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import zombieversity.model.collisions.CollisionsUtils;
import zombieversity.model.entities.Player;
import zombieversity.model.entities.PlayerImpl;
import zombieversity.model.entities.zombie.Zombie;
import zombieversity.model.entities.zombie.ZombieModel;
import zombieversity.model.entities.zombie.ZombieModelImpl;

/**
 * Test for zombie behavior.
 */
public class ZombieTest {

    private final ZombieModel zombieModel;
    private final Player player;

    /**
     * Tests setup.
     */
    public ZombieTest() {
        final Set<Point2D> spawnPoints;
        final Set<BoundingBox> obstacles;

        this.zombieModel = new ZombieModelImpl();
        spawnPoints = new HashSet<>();
        spawnPoints.add(new Point2D(10, 10));
        this.zombieModel.setSpawnPoints(spawnPoints);
        obstacles = new HashSet<>();
        obstacles.add(new BoundingBox(100, 100, 10, 10));
        this.zombieModel.setObstacles(obstacles);
        this.player = new PlayerImpl();
        this.zombieModel.setPlayer(this.player);
    }

    /**
     * Test zombies spawn.
     */
    @Test
    public void testSpawn() {
        this.zombieModel.setZombiesToSpawn(1);
        this.zombieModel.update();
        assertTrue(Integer.valueOf(this.zombieModel.getZombies().size()).equals(1));
    }

    /**
     * Test to check zombies correct movement.
     */
    @Test
    public void testMovementToPlayer() {
        this.zombieModel.setZombiesToSpawn(1);
        this.zombieModel.update();
        final Zombie zombie = this.zombieModel.getZombies().stream().findAny().get();
        while (!CollisionsUtils.isColliding(zombie.getBBox(), this.player.getBBox())) {
            this.zombieModel.update();
        }
        assertTrue(CollisionsUtils.isColliding(zombie.getBBox(), this.player.getBBox()));
    }

    /**
     * Test damage dealt to zombies.
     */
    @Test
    public void testHitZombie() {
        this.zombieModel.setZombiesToSpawn(1);
        this.zombieModel.update();
        final Zombie zombie = this.zombieModel.getZombies().stream().findAny().get();
        final int zombieHp = zombie.getLifeManager().getHP();
        final int damage = zombieHp / 2;
        this.zombieModel.hitZombie(zombie, damage);
        assertTrue(Integer.valueOf(zombie.getLifeManager().getHP()).equals(zombieHp - damage));
    }

    /**
     * Test zombies death.
     */
    @Test
    public void testKillZombie() {
        this.zombieModel.setZombiesToSpawn(1);
        this.zombieModel.update();
        final Zombie zombie = this.zombieModel.getZombies().stream().findAny().get();
        final int zombieHp = zombie.getLifeManager().getHP();
        this.zombieModel.hitZombie(zombie, zombieHp);
        this.zombieModel.update();
        assertTrue(this.zombieModel.getZombies().isEmpty());
    }

}
