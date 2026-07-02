package it.unibo.bmbman.controller.game;

import java.util.Optional;

import it.unibo.bmbman.model.collision.EntityCollisionManager;
import it.unibo.bmbman.model.collision.EntityCollisionManagerImpl;
import it.unibo.bmbman.model.entities.AbstractLivingEntity;
import it.unibo.bmbman.model.entities.Entity;
import it.unibo.bmbman.model.utilities.EntityType;
import it.unibo.bmbman.view.entities.EntityView;
/**
 * An implementation of {@link EntityController}.
 */
public class EntityControllerImpl implements EntityController {

    private final Entity en;
    private final EntityView ev;
    private final Optional<EntityCollisionManager> entityCollisionManager;

    /**
     * Construct an {@link EntityControllerImpl}.
     * @param en the entity followed
     * @param ev the {@link EntityView} of entity
     */
    public EntityControllerImpl(final Entity en, final EntityView ev) {
        this.en = en;
        this.ev = ev;
        updateView();
        if (en.getType() == EntityType.HERO || en.getType() == EntityType.MONSTER) {
            this.entityCollisionManager = Optional.of(new EntityCollisionManagerImpl(en.getCollisionComponent()));
        } else {
            this.entityCollisionManager = Optional.empty();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity getEntity() {
        return this.en;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public EntityView getEntityView() {
        return this.ev;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<EntityCollisionManager> getCollisionManager() {
        return this.entityCollisionManager;
    }
    private void updateView() {
        this.ev.setPosition(en.getPosition());
        if (en instanceof AbstractLivingEntity) {
            this.ev.setDirection(((AbstractLivingEntity) en).getDirection()); 
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        updateView();
        this.en.update();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void remove() {
        ev.remove();
    }
}
