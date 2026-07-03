package model.spawner;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import javafx.scene.shape.Rectangle;
import model.ModelImpl;
import model.entities.Enemy;
import model.entities.Spaceship;
import model.entities.properties.VelocityImpl;

/**
 * 
 * Initial state of the spawner.
 *
 */
public final class InitialState extends AbstractState {

    private static final double SIDE = 30.0;
    private static final int SPAWN_DELAY = 2500;
    private static final int ENEMY_LIFE = 26;

    /**
     * 
     * @param spaceship of the player.
     */
    public InitialState(final Spaceship spaceship) {
        super(spaceship);
    }

    @Override
    public Collection<Enemy> spawn() {
        return Arrays.asList(super.getEnemyFactory().createBasicPassive(
                new VelocityImpl(0, 100), new Rectangle(ModelImpl.GAME_WIDTH / 2, 0 - SIDE / 2, SIDE, SIDE), ENEMY_LIFE));
    }

    @Override
    public boolean isStateEnded() {
        return true;
    }

    @Override
    public Optional<State> getNextState() {
        return Optional.of(new ThreeDebrisState(super.getSpaceship()));
    }

    @Override
    public int getSpawnDelay() {
        return SPAWN_DELAY;
    }

}
