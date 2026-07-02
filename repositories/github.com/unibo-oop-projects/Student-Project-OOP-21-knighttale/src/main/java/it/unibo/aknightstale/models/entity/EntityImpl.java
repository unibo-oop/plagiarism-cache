package it.unibo.aknightstale.models.entity;

import it.unibo.aknightstale.utils.Borders;
import it.unibo.aknightstale.utils.BordersImpl;
import it.unibo.aknightstale.utils.Point2D;

public class EntityImpl implements Entity {

    private Borders borders;
    private final EntityType type;
    private final boolean collidable;

    public EntityImpl(final Borders borders, final EntityType type, final boolean collidable) {
        super();
        this.borders = borders;
        this.type = type;
        this.collidable = collidable;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Point2D getPosition() {
        return new Point2D(this.borders.getX(), this.borders.getY());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPosition(final Point2D p) {
        this.borders = new BordersImpl(p.getX(), p.getY(), this.borders.getWidth(), this.borders.getHeight());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Borders getBorders() {
        return this.borders;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBorders(final Borders b) {
        this.borders = b;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityType getType() {
        return this.type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isCollidable() {
        return this.collidable;
    }

}
