package model.spawner;

import java.util.Collection;
import java.util.Optional;

import model.entities.Enemy;
import model.entities.Spaceship;
import model.factories.EnemyFactory;
import model.factories.EnemyFactoryImpl;

/**
 * 
 * Abstract state of the spawner.
 *
 */
public abstract class AbstractState implements State {

    private final Spaceship spaceship;

    private final EnemyFactory enemyFactory;
    private final double difficulty;

    /**
     * 
     * @param spaceship
     *            of the player.
     * @param difficulty
     *            of the state.
     */
    public AbstractState(final Spaceship spaceship, final double difficulty) {
        this.spaceship = spaceship;
        this.difficulty = difficulty;
        this.enemyFactory = new EnemyFactoryImpl();
    }

    /**
     * 
     * @param spaceship
     *            of the player.
     */
    public AbstractState(final Spaceship spaceship) {
        this(spaceship, 1);
    }

    /**
     * 
     * @return player's spaceship.
     */
    public final Spaceship getSpaceship() {
        return this.spaceship;
    }

    /**
     * 
     * @return enemy factory.
     */
    public final EnemyFactory getEnemyFactory() {
        return this.enemyFactory;
    }

    /**
     * 
     * @return the difficulty of the state.
     */
    public double getDifficulty() {
        return this.difficulty;
    }

    @Override
    public abstract Collection<Enemy> spawn();

    @Override
    public abstract boolean isStateEnded();

    @Override
    public abstract Optional<State> getNextState();

    @Override
    public abstract int getSpawnDelay();

}
