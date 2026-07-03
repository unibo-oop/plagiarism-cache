package model.entities.boss;

import java.util.Optional;
import java.util.stream.IntStream;

import model.ModelImpl;
import model.entities.Spaceship;
import model.entities.properties.VelocityImpl;
import model.factories.EnemyFactory;

/**
 * 
 * First boss's phase.
 *
 */
public final class PhaseOne extends AbstractPhase {

    private static final int AIM_SUBORDINATE = 7;
    private static final int SUBORDINATE_RADIUS = 1;
    private static final double AIM_PROPORTION = 1.0 / 7.0;
    private static final double PROPORTION = 1.0 / 6.0;
    private static final int FIRE_RATE = 900;
    private static final int BULLET_VELOCITY = 150;

    /**
     * 
     * @param spaceship
     *            of the player.
     * @param enemyFactory
     *            .
     */
    public PhaseOne(final Spaceship spaceship, final EnemyFactory enemyFactory) {
        super(spaceship, enemyFactory);

        super.getSubordinateEnemies().add(enemyFactory.createAimEnemyPrototype(ModelImpl.GAME_WIDTH / 2,
                (int) (ModelImpl.GAME_HEIGHT * PROPORTION), spaceship));

        IntStream.iterate(2, i -> ++i).limit(AIM_SUBORDINATE).forEach(i -> {
            super.getSubordinateEnemies().add(enemyFactory.createActivePrototype(-SUBORDINATE_RADIUS,
                    (int) (AIM_PROPORTION * i * ModelImpl.GAME_HEIGHT), new VelocityImpl(BULLET_VELOCITY, 0)));
            super.getSubordinateEnemies().add(enemyFactory.createActivePrototype(
                    ModelImpl.GAME_WIDTH + SUBORDINATE_RADIUS,
                    (int) ((AIM_PROPORTION * i * ModelImpl.GAME_HEIGHT) - (ModelImpl.GAME_HEIGHT * AIM_PROPORTION / 2)),
                    new VelocityImpl(-BULLET_VELOCITY, 0)));
        });
    }

    @Override
    public Optional<Phase> getNextPhase() {
        return Optional.of(new PhaseTwo(super.getSpaceship(), super.getEnemyFactory()));
    }

    @Override
    public int getPhaseFireRate() {
        return FIRE_RATE;
    }

}
