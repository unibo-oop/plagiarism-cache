package model.spawner;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import javafx.scene.shape.Circle;
import model.ModelImpl;
import model.entities.Enemy;
import model.entities.Spaceship;
import model.entities.properties.VelocityImpl;

/**
 * 
 * State that spawns two line of enemies: the first from right, the second from
 * left.
 *
 */
public final class DoubleLineState extends AbstractState {

    private static final double RADIUS = 20.0;
    private static final int FIRE_RATE = 500;
    private static final int NEXT_DELAY = 2000;
    private static final int SPAWN_DELAY = 350;
    private static final int ENEMY_LIFE = 26;
    private static final int ENEMY_LINE = 7;
    private static final int ENEMY_SPEED = 250;
    private static final double PROPORTION_Y = 1.0 / 7.5;

    private int enemiesSpawned;

    /**
     * 
     * @param spaceship
     *            of the player.
     * @param difficulty
     *            of the state.
     */
    public DoubleLineState(final Spaceship spaceship, final double difficulty) {
        super(spaceship, difficulty);
        this.enemiesSpawned = 0;
    }

    /**
     * 
     * @param spaceship
     *            of the player.
     */
    public DoubleLineState(final Spaceship spaceship) {
        this(spaceship, 1);
    }

    @Override
    public Collection<Enemy> spawn() {
        this.enemiesSpawned++;
        if (this.enemiesSpawned <= ENEMY_LINE) {
            return Arrays.asList(super.getEnemyFactory().createBasicActive(new VelocityImpl(-ENEMY_SPEED, 0),
                    new Circle(ModelImpl.GAME_WIDTH + RADIUS, ModelImpl.GAME_HEIGHT * PROPORTION_Y, RADIUS),
                    (int) (ENEMY_LIFE * super.getDifficulty()), FIRE_RATE));
        } else {
            return Arrays.asList(super.getEnemyFactory().createBasicActive(new VelocityImpl(ENEMY_SPEED, 0),
                    new Circle(-RADIUS, ModelImpl.GAME_HEIGHT * PROPORTION_Y, RADIUS),
                    (int) (ENEMY_LIFE * Math.pow(super.getDifficulty(), 2)), FIRE_RATE));
        }
    }

    @Override
    public boolean isStateEnded() {
        return this.enemiesSpawned >= ENEMY_LINE * 2;
    }

    @Override
    public Optional<State> getNextState() {
        // TODO Auto-generated method stub
        return Optional.of(new FiveKamikazeState(super.getSpaceship(), super.getDifficulty()));
    }

    @Override
    public int getSpawnDelay() {
        return this.enemiesSpawned == ENEMY_LINE * 2 ? NEXT_DELAY : SPAWN_DELAY;
    }

}
