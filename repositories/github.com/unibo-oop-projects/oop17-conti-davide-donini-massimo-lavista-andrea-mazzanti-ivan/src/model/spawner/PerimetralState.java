package model.spawner;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import model.ModelImpl;
import model.entities.Enemy;
import model.entities.Spaceship;
import model.entities.properties.Velocity;
import model.entities.properties.VelocityImpl;

/**
 * 
 * State that spawn enemies on the perimeter.
 *
 */
public final class PerimetralState extends AbstractState {

    private static final double RADIUS = 20.0;
    private static final int FIRE_RATE = 950;
    private static final int NEXT_DELAY = 2000;
    private static final int SPAWN_DELAY = 500;
    private static final int ENEMY_LIFE = 25;
    private static final int ENEMY_LINE = 5;
    private static final int ENEMY_SPEED = 250;
    private static final double PROPORTION = 1.0 / 6.0;
    private static final int TIME_BEFORE_STOP = 300;
    private static final int TIME_STOPPED = 750;

    private int enemiesSpawned;

    /**
     * 
     * @param spaceship
     *            of the player.
     * @param difficulty
     *            of the state.
     */
    public PerimetralState(final Spaceship spaceship, final double difficulty) {
        super(spaceship, difficulty);
        this.enemiesSpawned = 0;
    }

    /**
     * 
     * @param spaceship
     *            of the player.
     */
    public PerimetralState(final Spaceship spaceship) {
        this(spaceship, 1);
    }

    @Override
    public Collection<Enemy> spawn() {
        Velocity velocity;
        Shape shape;
        this.enemiesSpawned++;

        if (this.enemiesSpawned <= ENEMY_LINE) {
            velocity = new VelocityImpl(ENEMY_SPEED, 0);
            shape = new Circle(-RADIUS,
                    ModelImpl.GAME_WIDTH - (ModelImpl.GAME_WIDTH * PROPORTION * this.enemiesSpawned), RADIUS);
        } else if (this.enemiesSpawned <= ENEMY_LINE * 2) {
            velocity = new VelocityImpl(0, ENEMY_SPEED);
            shape = new Circle(ModelImpl.GAME_WIDTH * PROPORTION * (this.enemiesSpawned - ENEMY_LINE), -RADIUS, RADIUS);
        } else {
            velocity = new VelocityImpl(-ENEMY_SPEED, 0);
            shape = new Circle(ModelImpl.GAME_WIDTH + RADIUS,
                    ModelImpl.GAME_WIDTH * PROPORTION * (this.enemiesSpawned - ENEMY_LINE * 2), RADIUS);
        }

        return Arrays.asList(super.getEnemyFactory().createComeBackEnemy(super.getEnemyFactory()
                .createAimEnemy(velocity, shape, (int) (ENEMY_LIFE * Math.pow(super.getDifficulty(), 2)), FIRE_RATE, super.getSpaceship()),
                TIME_BEFORE_STOP, TIME_STOPPED));
    }

    @Override
    public boolean isStateEnded() {
        return enemiesSpawned >= ENEMY_LINE * 3;
    }

    @Override
    public Optional<State> getNextState() {
        return Optional.of(new DoubleStarsState(super.getSpaceship(), super.getDifficulty()));
    }

    @Override
    public int getSpawnDelay() {
        return this.enemiesSpawned == ENEMY_LINE * 3 ? NEXT_DELAY : SPAWN_DELAY;
    }

}
