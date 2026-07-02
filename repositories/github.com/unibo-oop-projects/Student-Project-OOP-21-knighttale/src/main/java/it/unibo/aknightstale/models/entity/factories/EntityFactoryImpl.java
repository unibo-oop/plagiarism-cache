package it.unibo.aknightstale.models.entity.factories;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.aknightstale.controllers.entity.CharacterController;
import it.unibo.aknightstale.controllers.entity.EnemyController;
import it.unibo.aknightstale.controllers.entity.PlayerController;
import it.unibo.aknightstale.models.Enemy;
import it.unibo.aknightstale.models.entity.Character;
import it.unibo.aknightstale.models.entity.Player;
import it.unibo.aknightstale.utils.EntityManager;
import it.unibo.aknightstale.utils.EntityManagerImpl;
import it.unibo.aknightstale.utils.Point;
import it.unibo.aknightstale.views.entity.AnimatedEntityView;
import it.unibo.aknightstale.views.entity.EnemyView;
import it.unibo.aknightstale.views.entity.PlayerView;

public class EntityFactoryImpl implements EntityFactory {

    private final EntityManager manager;

    public EntityFactoryImpl() {
        super();
        this.manager = new EntityManagerImpl();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressFBWarnings("EI_EXPOSE_REP")       //must return a reference because it will be modified
    @Override
    public EntityManager getEntityManager() {
        return this.manager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public  CharacterController<? super Character, ? super AnimatedEntityView> getPlayer(final Point p) {
        final var player = new PlayerController<Character, AnimatedEntityView>(new Player(p),
                new PlayerView(), getEntityManager());
        this.manager.addEntity(player);
        return player;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public  CharacterController<? super Character, ? super  AnimatedEntityView> getEnemy(final Point spawnPosition) {

        final Enemy enemyModel = new Enemy(spawnPosition);
        final EnemyView enemyView = new EnemyView();
        final var enemy = new EnemyController<Character, AnimatedEntityView>(enemyModel, enemyView, getEntityManager());

        this.manager.addEntity(enemy);
        return enemy;
    }



}
