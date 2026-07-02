package boxhead.model.entities.zombies;

import java.util.Random;
import java.util.Set;

import boxhead.model.entities.EntityType;
import boxhead.model.entities.utils.Direction;
import javafx.geometry.Point2D;

/**
 * {@link Spawn} implementation
 */
public class SpawnImpl implements Spawn {

	private static final int DEFAULT_DAMAGE = 1;
	private static final double DEFAULT_SPEED = 1;
	private static final int DEFAULT_HEALTH = 100;
    private Set<Point2D> spawnPoints;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSpawnPoints(final Set<Point2D> spawnPoints) {
        this.spawnPoints = spawnPoints;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Point2D getSpawnPoint() {
        return this.chooseSpawnPoint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Zombie getZombie(final Point2D spawnPoint) {
    	return new Zombie(spawnPoint, Direction.SOUTH, DEFAULT_SPEED, EntityType.ZOMBIE, DEFAULT_HEALTH, DEFAULT_DAMAGE);
    }

    /**
     * Chooses a random spawn point from the set
     * @return a Point2D coordinates spawn point
     */
    private Point2D chooseSpawnPoint() {
        return this.spawnPoints.stream().skip(new Random().nextInt(this.spawnPoints.size())).findFirst().get();

    }

}
