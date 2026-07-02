package frogger.model.implementations;

import java.util.Random;
import java.util.Set;

import frogger.common.Direction;
import frogger.common.Position;
import frogger.model.interfaces.EntitySpawner;
import frogger.model.interfaces.PickableObject;
import frogger.model.interfaces.SpawnerFactory;

/**
 * {@inheritDoc}.
 * <p>
 * Concreate creator of SpawnerFactory, it specify the behaviour creating spawner with random logic.
 * </p>
 */
public class RandomSpawnerFactory implements SpawnerFactory {

    private final Random ran = new Random();

    /**
     * {@inheritDoc}
     * <p>
     * Create the spawner with a random logic.
     * </p>
     */
    @Override
    public EntitySpawner<Car> carSpawner(final int laneIndex, final float speed, final Direction direction) {
        return new RandomObstaclesSpawner<>(Car.class, laneIndex, speed, direction, ran);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Create the spawner with a random logic.
     * </p>
     */
    @Override
    public EntitySpawner<Trunk> trunkSpawner(final int laneIndex, final float speed, final Direction direction) {
        return new RandomObstaclesSpawner<>(Trunk.class, laneIndex, speed, direction, ran);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Create the spawner with a random logic.
     * </p>
     */
    @Override
    public EntitySpawner<Eagle> eagleSpawner() {
        return new RandomEaglesSpawner(ran);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Create the spawner with a random logic.
     * </p>
     */
    @Override
    public EntitySpawner<PickableObject> powerUpSpawner(final Set<Position> alreadyPresent) {
        return new RandomPickableSpawner(ran, PowerUpImpl.class, alreadyPresent);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Create the spawner with a random logic.
     * </p>
     */
    @Override
    public EntitySpawner<PickableObject> coinSpawner(final Set<Position> alreadyPresent) {
        return new RandomPickableSpawner(ran, Coin.class, alreadyPresent);
    }

}
