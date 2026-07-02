package zombieversity.model.entities.zombie;

import java.util.Random;
import java.util.Set;

import javafx.geometry.Point2D;

/**
 * Implementation of {@link Spawn}.
 */
public class SpawnImpl implements Spawn {

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
    public final Zombie getZombieFromTier(final Point2D spawnPoint, final Tiers tier) {
        return new Zombie.Builder(spawnPoint)
                         .velocity(tier.getVelocity())
                         .maxHp(tier.getMxHp())
                         .damageDealt(tier.getDamageDealt())
                         .build();
    }

    /**
     * Chooses a random spawn point from the given set.
     * @return a Point2D representing spawn point coordinates
     */
    private Point2D chooseSpawnPoint() {
        return this.spawnPoints.stream().skip(new Random().nextInt(this.spawnPoints.size())).findFirst().get();

    }

}
