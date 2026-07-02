package it.unibo.aknightstale.controllers.entity;


import it.unibo.aknightstale.models.entity.Character;
import it.unibo.aknightstale.utils.EntityManager;
import it.unibo.aknightstale.views.entity.AnimatedEntityView;

public class EnemyController<M extends Character, V extends AnimatedEntityView> extends AbstractController<M, V> {

    private long lastAttack = System.currentTimeMillis();

    public EnemyController(final M model, final V view, final EntityManager manager) {
        super(model, view, manager);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void attack() {
        final var attackIdle = System.currentTimeMillis() - this.lastAttack;
        if (attackIdle >= 1000) {
            super.attack();
            this.lastAttack = System.currentTimeMillis();
        }
    }
}
