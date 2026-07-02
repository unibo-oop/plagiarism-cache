package it.unibo.isaccoop.model.enemy;

import it.unibo.isaccoop.graphics.factory.ConcreteEnemyGraphicsComponentFactory;
import it.unibo.isaccoop.model.action.NonShootingHitStrategy;
import it.unibo.isaccoop.model.action.NonShootingMovementStrategy;
import it.unibo.isaccoop.model.player.PlayerStat;

/**
 * NonShootingEnemy class that models non shooting enemy.
 * */
public final class NonShootingEnemy extends AbstractEnemy {

    /**
     * Constructor for {@link NonShootingEnemy}.
     */
    public NonShootingEnemy() {
        super(EnemyHearts.ENEMY_HEARTS, new NonShootingHitStrategy(), new NonShootingMovementStrategy(),
                new ConcreteEnemyGraphicsComponentFactory().getNonShootingGraphicsComponent());
    }

    @Override
    public void onHit(final PlayerStat player) {
        if (((NonShootingHitStrategy) super.getHitStrategy()).canHit()) {
            super.onHit(player);
        }
    }
}
