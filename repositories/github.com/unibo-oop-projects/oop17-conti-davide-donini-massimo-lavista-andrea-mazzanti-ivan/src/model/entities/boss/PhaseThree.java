package model.entities.boss;

import java.util.Optional;

import model.ModelImpl;
import model.entities.Spaceship;
import model.factories.EnemyFactory;

/**
 * 
 * Third boss's phase.
 *
 */
public final class PhaseThree extends AbstractPhase {

    private static final double PROPORTION = 1.0 / 5.0;
    private static final int FIRE_RATE = 400;

    /**
     * @param spaceship
     *            of the player.
     * @param enemyFactory
     *            .
     */
    public PhaseThree(final Spaceship spaceship, final EnemyFactory enemyFactory) {
        super(spaceship, enemyFactory);

        super.getSubordinateEnemies().add(enemyFactory.createStarShootEnemy(enemyFactory.createAimEnemyPrototype(
                (int) (ModelImpl.GAME_WIDTH * PROPORTION), (int) (ModelImpl.GAME_HEIGHT * PROPORTION), spaceship)));
        super.getSubordinateEnemies().add(enemyFactory.createStarShootEnemy(
                enemyFactory.createAimEnemyPrototype((int) (ModelImpl.GAME_WIDTH - ModelImpl.GAME_WIDTH * PROPORTION),
                        (int) (ModelImpl.GAME_HEIGHT * PROPORTION), spaceship)));
    }

    @Override
    public Optional<Phase> getNextPhase() {
        return Optional.empty();
    }

    @Override
    public int getPhaseFireRate() {
        return FIRE_RATE;
    }
}
