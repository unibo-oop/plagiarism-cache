package model.spawner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javafx.scene.shape.Circle;
import model.ModelImpl;
import model.entities.Enemy;
import model.entities.Spaceship;
import model.entities.properties.VelocityImpl;

/**
 * 
 * State that spawns two line of aim enemies at the same time.
 *
 */
public final class ObliqueAimEnemiesState extends AbstractState {

    private static final double RADIUS = 20.0;
    private static final int FIRE_RATE = 600;
    private static final int NEXT_DELAY = 2000;
    private static final int SPAWN_DELAY = 500;
    private static final int ENEMY_LINE = 8;
    private static final int ENEMY_LIFE = 26;
    private static final int ENEMY_SPEED = 250;
    private static final double PROPORTION_Y = 1.0 / 2.5;

    private int enemiesSpawned;

    /**
     * 
     * @param spaceship
     *            of the player.
     * @param difficulty
     *            of the state.
     */
    public ObliqueAimEnemiesState(final Spaceship spaceship, final double difficulty) {
        super(spaceship, difficulty);
    }

    /**
     * 
     * @param spaceship
     *            of the player.
     */
    public ObliqueAimEnemiesState(final Spaceship spaceship) {
        this(spaceship, 1);
    }

    @Override
    public Collection<Enemy> spawn() {
        this.enemiesSpawned += 2;
        final List<Enemy> enemies = new ArrayList<>();

        enemies.add(super.getEnemyFactory().createAimEnemy(new VelocityImpl(-ENEMY_SPEED, -ENEMY_SPEED / 2),
                new Circle(ModelImpl.GAME_WIDTH + RADIUS, ModelImpl.GAME_HEIGHT * PROPORTION_Y, RADIUS),
                (int) (ENEMY_LIFE * Math.pow(super.getDifficulty(), 2)), FIRE_RATE, super.getSpaceship()));

        enemies.add(super.getEnemyFactory().createAimEnemy(new VelocityImpl(ENEMY_SPEED, -ENEMY_SPEED / 2),
                new Circle(0 - RADIUS, ModelImpl.GAME_HEIGHT * PROPORTION_Y, RADIUS),
                (int) (ENEMY_LIFE * Math.pow(super.getDifficulty(), 2)), FIRE_RATE, super.getSpaceship()));

        return enemies;

    }

    @Override
    public boolean isStateEnded() {
        return this.enemiesSpawned >= ENEMY_LINE * 2;
    }

    @Override
    public Optional<State> getNextState() {
        return Optional.of(new X3x3ShootEnemyState(super.getSpaceship(), super.getDifficulty()));
    }

    @Override
    public int getSpawnDelay() {
        return this.enemiesSpawned == ENEMY_LINE * 2 ? NEXT_DELAY : SPAWN_DELAY;
    }

}
