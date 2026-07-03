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
 * State that spawns two star shooting enemies, one each side.
 *
 */
public final class DoubleStarsState extends AbstractState {

    private static final int SPAWN_DELAY = 6500;
    private static final int ENEMY_LIFE = 500;
    private static final double PROPORTION = 1.0 / 2.5;
    private static final int ENEMY_RADIUS = 80;
    private static final int ENEMY_SPEED = 75;
    private static final int FIRE_RATE = 900;
    private static final int STOP_TIME = 2200;
    private static final int MAX_DIFFICULTY = 3;
    private static final int BOSS_DELAY = 10000;

    /**
     * 
     * @param spaceship
     *            of the player.
     * @param difficulty
     *            of the state.
     */
    public DoubleStarsState(final Spaceship spaceship, final double difficulty) {
        super(spaceship, difficulty);
    }

    /**
     * 
     * @param spaceship
     *            of the player.
     */
    public DoubleStarsState(final Spaceship spaceship) {
        this(spaceship, 1);
    }

    @Override
    public Collection<Enemy> spawn() {
        final List<Enemy> enemies = new ArrayList<>();

        enemies.add(
                super.getEnemyFactory()
                        .createStopEnemy(
                                super.getEnemyFactory().createStarShootEnemy(
                                        super.getEnemyFactory().createBasicActive(new VelocityImpl(ENEMY_SPEED, 0),
                                                new Circle(-ENEMY_RADIUS, PROPORTION * ModelImpl.GAME_HEIGHT,
                                                        ENEMY_RADIUS),
                                                (int) (ENEMY_LIFE * Math.pow(super.getDifficulty(), 2)), FIRE_RATE)),
                                STOP_TIME));
        enemies.add(super.getEnemyFactory().createStopEnemy(
                super.getEnemyFactory().createStarShootEnemy(super.getEnemyFactory().createBasicActive(
                        new VelocityImpl(-ENEMY_SPEED, 0),
                        new Circle(ModelImpl.GAME_WIDTH + ENEMY_RADIUS, PROPORTION * ModelImpl.GAME_HEIGHT,
                                ENEMY_RADIUS),
                        (int) (ENEMY_LIFE * Math.pow(super.getDifficulty(), 2)), FIRE_RATE)),
                STOP_TIME));

        return enemies;
    }

    @Override
    public boolean isStateEnded() {
        return true;
    }

    @Override
    public Optional<State> getNextState() {
        if (super.getDifficulty() == MAX_DIFFICULTY) {
            return Optional.of(new BossState(super.getSpaceship()));
        }
        return Optional.of(new DoubleLineState(super.getSpaceship(), super.getDifficulty() + 1));
    }

    @Override
    public int getSpawnDelay() {
        return this.getDifficulty() == MAX_DIFFICULTY ? BOSS_DELAY : SPAWN_DELAY;
    }

}
