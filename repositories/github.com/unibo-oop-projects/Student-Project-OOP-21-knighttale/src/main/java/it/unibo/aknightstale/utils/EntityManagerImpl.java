package it.unibo.aknightstale.utils;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.aknightstale.controllers.entity.EntityController;
import it.unibo.aknightstale.models.entity.Character;
import it.unibo.aknightstale.views.entity.AnimatedEntityView;

import java.util.ArrayList;
import java.util.List;

public class EntityManagerImpl implements EntityManager {

    private final List<EntityController<? super Character, ? super AnimatedEntityView>> entities;
    private CollisionManager collision;

    public EntityManagerImpl() {
        super();
        this.entities = new ArrayList<>();
    }

    public EntityManagerImpl(final List<EntityController<? super Character, ? super AnimatedEntityView>> l) {
        super();
        this.entities = new ArrayList<>(l);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<List<EntityController<? super Character, ? super AnimatedEntityView>>> update() {
        return this.collision.update();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addEntity(final EntityController<? super Character, ? super AnimatedEntityView> ec) {
        if (!this.entities.contains(ec)) {
            this.entities.add(ec);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeEntity(final EntityController<? super Character, ? super AnimatedEntityView> ec) {
        this.entities.remove(ec);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressFBWarnings("EI_EXPOSE_REP") // can be modified later
    @Override
    public List<EntityController<? super Character, ? super AnimatedEntityView>> getEntities() {
        return this.entities;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressFBWarnings("EI_EXPOSE_REP") // must return a reference because it will be modified
    @Override
    public CollisionManager getCollisionManager() {
        return this.collision;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressFBWarnings("EI_EXPOSE_REP2") // must return a reference because it will be modified
    @Override
    public void setCollisionManager(final CollisionManager collision) {
        this.collision = collision;
    }

}
