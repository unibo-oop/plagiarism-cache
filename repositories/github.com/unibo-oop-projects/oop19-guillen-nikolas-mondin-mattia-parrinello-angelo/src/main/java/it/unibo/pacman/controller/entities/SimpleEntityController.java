package it.unibo.pacman.controller.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Optional;

import it.unibo.pacman.model.entities.Entity;
import it.unibo.pacman.model.utilities.EntityType;
import it.unibo.pacman.view.entities.EntityView;
/**
 * The implementation of {@link EntityController}.
 */
public class SimpleEntityController implements EntityController {
    private final Entity entity;
    private final EntityView ev;
    private boolean remove;
    /**
     * Create a {@link SimpleEntityController}.
     * @param entity the entity to control.
     * @param ev the view to control.
     */
    public SimpleEntityController(final Entity entity, final EntityView ev) {
        this.entity = entity;
        this.ev = ev;
        this.remove = false;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void updateView() {
        if (!this.isRemoved()) {
            this.ev.updatePosition(this.entity.getPosition());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Entity> getCollision(final Rectangle collider) {
        if (this.entity.getBounds().intersects(collider) && !this.isRemoved()) {
            return Optional.of(this.entity);
        } else {
            return Optional.empty();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove() {
        this.remove = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isRemoved() {
        return this.remove;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final Graphics g) {
        this.ev.render(g);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public EntityType getType() {
        return this.entity.getType();
    }
}
