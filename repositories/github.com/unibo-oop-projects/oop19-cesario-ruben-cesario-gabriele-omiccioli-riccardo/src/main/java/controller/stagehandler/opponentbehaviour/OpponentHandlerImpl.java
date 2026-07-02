package controller.stagehandler.opponentbehaviour;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import model.entity.EntityID.EntityIDCategory;
import model.ship.EnemyShip;
import model.ship.PlayerShip;
import model.ship.basic.BasicEnemyShip;
import model.weapon.Weapon.Projectile;

/**
 * The controller that manages the creation and update of the enemies'
 * AI and the projectiles fired by them.
 * @see OpponentHandler
 */
public class OpponentHandlerImpl implements OpponentHandler {

    private final List<Behaviour> aiList;

    public OpponentHandlerImpl() {
        this.aiList = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset(final Collection<EnemyShip> enemies) {
        for (final EnemyShip enemy : enemies) {
            if (enemy.getID().belongsTo(EntityIDCategory.SPACESHIPS_BASIC)) {
                this.aiList.add(new BasicOpponentAIImpl((BasicEnemyShip) enemy));
            } else {
                throw new IllegalArgumentException("No IA developed to support these categories");
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void invoke(final PlayerShip player) {
        aiList.forEach(ai -> {
            ai.evaluateStrategy(player);
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final Collection<EnemyShip> deadEnemies) {
        this.aiList.removeIf(ai -> deadEnemies.contains(ai.getEnemy()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Projectile> getProjectiles() {
        final Collection<Projectile> projectiles = new ArrayList<>();
        this.aiList.forEach(ia -> projectiles.addAll(ia.getFiredProjectiles()));
        return projectiles;
    }

}
