package model.spawner;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import model.entities.Enemy;
import model.entities.Spaceship;

/**
 * 
 * The boss state.
 *
 */
public final class BossState extends AbstractState {

    private static final int NEXT_DELAY = 1000000;

    /**
     * 
     * @param spaceship
     *            of the player.
     */
    public BossState(final Spaceship spaceship) {
        super(spaceship);
    }

    @Override
    public Collection<Enemy> spawn() {
        return Arrays.asList(super.getEnemyFactory().createEnemyBoss(super.getSpaceship()));
    }

    @Override
    public boolean isStateEnded() {
        return true;
    }

    @Override
    public Optional<State> getNextState() {
        return Optional.empty();
    }

    @Override
    public int getSpawnDelay() {
        return NEXT_DELAY;
    }

}
