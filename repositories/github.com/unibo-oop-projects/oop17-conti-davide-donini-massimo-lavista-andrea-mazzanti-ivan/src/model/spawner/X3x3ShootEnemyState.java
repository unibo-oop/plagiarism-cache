package model.spawner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import javafx.scene.shape.Rectangle;
import model.ModelImpl;
import model.entities.Enemy;
import model.entities.Spaceship;
import model.entities.properties.VelocityImpl;

/**
 * 
 * State that spawns three x3ShootEnemy.
 *
 */
public final class X3x3ShootEnemyState extends AbstractState {

    private static final int SPAWN_DELAY = 6500;
    private static final int ENEMY_LIFE = 100;
    private static final double PROPORTION = 1.0 / 4.0;
    private static final int ENEMY_NUMBER = 3;
    private static final int ENEMY_DIMENSION = 75;
    private static final int ENEMY_SPEED = 150;
    private static final int FIRE_RATE = 600;
    private static final int STOP_TIME = 600;

    /**
     * 
     * @param spaceship
     *            of the player.
     * @param difficulty
     *            of the state.
     */
    public X3x3ShootEnemyState(final Spaceship spaceship, final double difficulty) {
        super(spaceship, difficulty);
    }

    /**
     * 
     * @param spaceship
     *            of the player.
     */
    public X3x3ShootEnemyState(final Spaceship spaceship) {
        this(spaceship, 1);
    }

    @Override
    public Collection<Enemy> spawn() {
        final List<Enemy> enemies = new ArrayList<>();

        IntStream.iterate(1, i -> ++i).limit(ENEMY_NUMBER).forEach(i -> {
            enemies.add(super.getEnemyFactory().createStopEnemy(
                    super.getEnemyFactory().createX3ShootEnemy(super.getEnemyFactory().createBasicActive(
                            new VelocityImpl(0, ENEMY_SPEED),
                            new Rectangle(PROPORTION * i * ModelImpl.GAME_WIDTH, 0 - ENEMY_DIMENSION / 2,
                                    ENEMY_DIMENSION, ENEMY_DIMENSION),
                            (int) (ENEMY_LIFE * Math.pow(super.getDifficulty(), 2)), FIRE_RATE)),
                    STOP_TIME));
        });

        return enemies;
    }

    @Override
    public boolean isStateEnded() {
        return true;
    }

    @Override
    public Optional<State> getNextState() {
        return Optional.of(new BacksideKamikazeState(super.getSpaceship(), super.getDifficulty()));
    }

    @Override
    public int getSpawnDelay() {
        return SPAWN_DELAY;
    }

}
