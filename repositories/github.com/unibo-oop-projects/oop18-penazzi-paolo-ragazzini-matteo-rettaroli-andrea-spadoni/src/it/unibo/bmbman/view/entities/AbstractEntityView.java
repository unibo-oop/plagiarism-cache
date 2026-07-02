package it.unibo.bmbman.view.entities;

import java.awt.Graphics;
import java.awt.Image;

import it.unibo.bmbman.model.utilities.Dimension;
import it.unibo.bmbman.model.utilities.Direction;
import it.unibo.bmbman.model.utilities.EntityType;
import it.unibo.bmbman.model.utilities.Position;
import it.unibo.bmbman.view.utilities.ScreenToolUtils;
/**
 * Abstract implementation of entityView.
 */
public abstract class AbstractEntityView implements EntityView {

    private Position position;
    private  Dimension dimension;
    private Direction direction;
    private boolean visible;
    private final EntityType entityType;
    /**
     * Constructor for an EntityView.
     * @param position where is the entity in our terrain
     * @param dimension the dimension of entity
     * @param visible if the entity is visible or not
     * @param entityType the type of the entity
     */
    public AbstractEntityView(final Position position, final Dimension dimension, final boolean visible, final EntityType entityType) {
        this.position = position;
        this.dimension = new Dimension(dimension.getHeight() * ScreenToolUtils.SCALE, dimension.getWidth() * ScreenToolUtils.SCALE);
        this.visible = visible;
        this.direction = Direction.IDLE;
        this.entityType = entityType;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public EntityType getType() {
        return entityType;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setPosition(final Position position) {
        this.position = position;

    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Position getPosition() {
        return this.position;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setDimension(final Dimension dimension) {
        this.dimension = dimension;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Dimension getDimension() {
        return this.dimension;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public abstract Image getSprite();
    /**
     * {@inheritDoc}
     */
    @Override
    public void setVisible(final boolean visible) {
        this.visible = visible;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isVisible() {
        return this.visible;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setDirection(final Direction direction) {
        this.direction = direction;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Direction getDirection() {
        return this.direction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final Graphics g) {
        if (this.visible) {
            g.drawImage(getSprite(), getPosition().getX(), getPosition().getY(), getDimension().getWidth(), getDimension().getHeight(), null);
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void remove() {
        this.visible = false;
    }


}
