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
 * State that spawns five kamikaze enemies.
 *
 */
public class FiveKamikazeState extends AbstractState {

    private static final int SPAWN_DELAY = 1500;
    private static final int ENEMY_LIFE = 50;
    private static final double PROPORTION = 1.0 / 6.0;
    private static final int KAMIKAZE_NUMBER = 5;
    private static final int KAMIKAZE_DIMENSION = 35;
    private static final int ENEMY_SPEED = 150;

    private int n;

    /**
     * 
     * @param spaceship
     *            of the player.
     * @param difficulty
     *            of the state.
     */
    public FiveKamikazeState(final Spaceship spaceship, final double difficulty) {
        super(spaceship, difficulty);
        this.n = 0;
    }

    /**
     * 
     * @param spaceship
     *            of the player.
     */
    public FiveKamikazeState(final Spaceship spaceship) {
        this(spaceship, 1);
    }

    @Override
    public final Collection<Enemy> spawn() {
        final List<Enemy> enemies = new ArrayList<>();
        this.n++;

        IntStream.iterate(1, i -> ++i).limit(KAMIKAZE_NUMBER).forEach(i -> {
            enemies.add(super.getEnemyFactory().createKamikaze(
                    super.getEnemyFactory().createBasicPassive(new VelocityImpl(0, ENEMY_SPEED),
                            new Rectangle(PROPORTION * i * ModelImpl.GAME_WIDTH, 0 - KAMIKAZE_DIMENSION / 2,
                                    KAMIKAZE_DIMENSION, KAMIKAZE_DIMENSION),
                            (int) (ENEMY_LIFE * Math.pow(super.getDifficulty(), 2))),
                    super.getSpaceship()));
        });

        return enemies;
    }

    @Override
    public final boolean isStateEnded() {
        return this.n == 3;
    }

    @Override
    public final Optional<State> getNextState() {
        return Optional.of(new ObliqueAimEnemiesState(super.getSpaceship(), super.getDifficulty()));
    }

    @Override
    public final int getSpawnDelay() {
        if (this.n == 3) {
            return SPAWN_DELAY * 2;
        }
        return SPAWN_DELAY;
    }

}
