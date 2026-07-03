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
 * State that spawn kamikaze at the backside of the player.
 *
 */
public final class BacksideKamikazeState extends AbstractState {

    private static final int SPAWN_DELAY = 1500;
    private static final int ENEMY_LIFE = 25;
    private static final double PROPORTION = 1.0 / 6.0;
    private static final int KAMIKAZE_NUMBER = 5;
    private static final int KAMIKAZE_DIMENSION = 35;
    private static final int ENEMY_SPEED = 150;

    /**
     * 
     * @param spaceship
     *            of the player.
     * @param difficulty
     *            of the state.
     */
    public BacksideKamikazeState(final Spaceship spaceship, final double difficulty) {
        super(spaceship, difficulty);
    }

    /**
     * 
     * @param spaceship
     *            of the player.
     */
    public BacksideKamikazeState(final Spaceship spaceship) {
        this(spaceship, 1);
    }

    @Override
    public Collection<Enemy> spawn() {
        final List<Enemy> enemies = new ArrayList<>();

        IntStream.iterate(1, i -> ++i).limit(KAMIKAZE_NUMBER).forEach(i -> {
            enemies.add(
                    super.getEnemyFactory().createKamikaze(
                            super.getEnemyFactory().createBasicPassive(new VelocityImpl(0, -ENEMY_SPEED),
                                    new Rectangle(PROPORTION * i * ModelImpl.GAME_WIDTH,
                                            ModelImpl.GAME_HEIGHT + KAMIKAZE_DIMENSION / 2, KAMIKAZE_DIMENSION,
                                            KAMIKAZE_DIMENSION),
                                    (int) (ENEMY_LIFE * Math.pow(super.getDifficulty(), 2))),
                            super.getSpaceship()));
        });

        return enemies;
    }

    @Override
    public boolean isStateEnded() {
        return true;
    }

    @Override
    public Optional<State> getNextState() {
        return Optional.of(new PerimetralState(super.getSpaceship(), super.getDifficulty()));
    }

    @Override
    public int getSpawnDelay() {
        return SPAWN_DELAY;
    }

}
