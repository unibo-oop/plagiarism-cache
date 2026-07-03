package model.spawner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javafx.scene.shape.Rectangle;
import model.ModelImpl;
import model.entities.Enemy;
import model.entities.Spaceship;
import model.entities.properties.VelocityImpl;

/**
 * 
 * State that spawns three debris.
 *
 */
public final class ThreeDebrisState extends AbstractState {

    private static final int SPAWN_DELAY = 2000;
    private static final int ENEMY_LIFE = 26;
    private static final int ENEMY_SPEED_X = 75;
    private static final int ENEMY_SPEED_Y = 150;
    private static final double SIDE = 30.0;

    /**
     * 
     * @param spaceship of the player.
     */
    public ThreeDebrisState(final Spaceship spaceship) {
        super(spaceship);
    }

    @Override
    public Collection<Enemy> spawn() {
        final List<Enemy> enemies = new ArrayList<>();

        enemies.add(super.getEnemyFactory().createBasicPassive(
                new VelocityImpl(-ENEMY_SPEED_X, ENEMY_SPEED_Y), new Rectangle(ModelImpl.GAME_WIDTH - SIDE / 2, 0 - SIDE / 2, SIDE, SIDE), ENEMY_LIFE));
        enemies.add(super.getEnemyFactory().createBasicPassive(
                new VelocityImpl(0, ENEMY_SPEED_Y), new Rectangle(ModelImpl.GAME_WIDTH / 2, 0 - SIDE / 2, SIDE, SIDE), ENEMY_LIFE));
        enemies.add(super.getEnemyFactory().createBasicPassive(
                new VelocityImpl(ENEMY_SPEED_X, ENEMY_SPEED_Y), new Rectangle(0 + SIDE / 2, 0 - SIDE / 2, SIDE, SIDE), ENEMY_LIFE));

        return enemies;
    }

    @Override
    public boolean isStateEnded() {
        return true;
    }

    @Override
    public Optional<State> getNextState() {
        return Optional.of(new DoubleLineState(super.getSpaceship()));
    }

    @Override
    public int getSpawnDelay() {
        return SPAWN_DELAY;
    }

}
