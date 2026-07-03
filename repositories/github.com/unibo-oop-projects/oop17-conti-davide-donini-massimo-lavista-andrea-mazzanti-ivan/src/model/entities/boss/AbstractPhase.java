package model.entities.boss;

import java.util.ArrayList;
import java.util.List;
import model.entities.ActiveEnemy;
import model.entities.Bullet;
import model.entities.Spaceship;
import model.factories.EnemyFactory;

/**
 * 
 * Abstract phase of the enemy boss.
 *
 */
public abstract class AbstractPhase implements Phase {

    private final List<ActiveEnemy> subordinateEnemies;
    private final EnemyFactory enemyFactory;
    private final Spaceship spaceship;

    /**
     * @param spaceship
     *            of the player.
     * @param enemyFactory
     *            .
     */
    public AbstractPhase(final Spaceship spaceship, final EnemyFactory enemyFactory) {
        this.spaceship = spaceship;
        this.enemyFactory = enemyFactory;
        this.subordinateEnemies = new ArrayList<>();
    }

    @Override
    public final List<Bullet> shoot() {
        final List<Bullet> bullets = new ArrayList<>();

        this.subordinateEnemies.stream().forEach(e -> {
            bullets.addAll(e.shoot());
        });

        return bullets;
    }

    /**
     * 
     * @return subordinate enemies.
     */
    public final List<ActiveEnemy> getSubordinateEnemies() {
        return subordinateEnemies;
    }

    /**
     * 
     * @return the enemy factory
     */
    public final EnemyFactory getEnemyFactory() {
        return enemyFactory;
    }

    /**
     * 
     * @return the spaceship.
     */
    public final Spaceship getSpaceship() {
        return spaceship;
    }

}
