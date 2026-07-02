package tests;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import zombieversity.model.entities.Player;
import zombieversity.model.entities.PlayerImpl;
import zombieversity.model.entities.zombie.Zombie;
import zombieversity.model.entities.zombie.ZombieModel;
import zombieversity.model.entities.zombie.ZombieModelImpl;
import zombieversity.model.rounds.Round;
import zombieversity.model.rounds.RoundImpl;

/**
 * Test rounds flow.
 */
public class RoundTest {

    private final ZombieModel zombieModel;
    private final Round round;

    /**
     * Tests setup.
     */
    public RoundTest() {
        final Set<Point2D> spawnPoints;
        final Set<BoundingBox> obstacles;
        final Player player;

        this.zombieModel = new ZombieModelImpl();
        spawnPoints = new HashSet<>();
        spawnPoints.add(new Point2D(10, 10));
        this.zombieModel.setSpawnPoints(spawnPoints);
        obstacles = new HashSet<>();
        obstacles.add(new BoundingBox(100, 100, 10, 10));
        this.zombieModel.setObstacles(obstacles);
        player = new PlayerImpl();
        this.zombieModel.setPlayer(player);
        this.round = new RoundImpl(this.zombieModel);
    }

    /**
     * Test round start.
     */
    @Test
    public void testRoundStart() {
        this.round.update();
        assertFalse(this.round.isRoundRunning());
        while (this.round.getTimeToStart() > 0) {
            this.round.update();
        }
        this.round.update();
        assertTrue(this.round.isRoundRunning());
    }

    /**
     * Test round end.
     */
    @Test
    public void testRoundEnd() {
        while (!this.round.isRoundRunning()) {
            this.round.update();
        }
        this.zombieModel.setZombiesToSpawn(1);
        this.zombieModel.update();
        final Zombie zombie = this.zombieModel.getZombies().stream().findAny().get();
        final int zombieHp = zombie.getLifeManager().getHP();
        this.zombieModel.hitZombie(zombie, zombieHp);
        this.zombieModel.update();
        this.round.update();
        assertFalse(this.round.isRoundRunning());
    }

}
