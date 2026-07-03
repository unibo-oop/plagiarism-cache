package model.entities.boss;

import java.util.Optional;

import model.ModelImpl;
import model.entities.Spaceship;
import model.factories.EnemyFactory;

/**
 * 
 * Second boss's phase.
 *
 */
public final class PhaseTwo extends AbstractPhase {

    private static final int FIRE_RATE = 20;
    private static final int SHOOT_NUMBER = 10;

    /**
     * @param spaceship
     *            of the player.
     * @param enemyFactory
     *            .
     */
    public PhaseTwo(final Spaceship spaceship, final EnemyFactory enemyFactory) {
        super(spaceship, enemyFactory);

        super.getSubordinateEnemies().add(enemyFactory.createStarShootEnemy(
                enemyFactory.createAimEnemyPrototype(ModelImpl.GAME_WIDTH / 2, ModelImpl.GAME_HEIGHT / 2, spaceship),
                SHOOT_NUMBER));
    }

    @Override
    public Optional<Phase> getNextPhase() {
        return Optional.of(new PhaseThree(super.getSpaceship(), super.getEnemyFactory()));
    }

    @Override
    public int getPhaseFireRate() {
        return FIRE_RATE;
    }
}
