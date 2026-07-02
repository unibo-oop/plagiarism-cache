package it.unibo.oop18.cfc.Objects;


import java.awt.geom.Rectangle2D;

import it.unibo.oop18.cfc.TileMap.Tile;
import it.unibo.oop18.cfc.Util.Position;


/**
 * This class models a simple {@link GameObject}.
 */
public abstract class AbstractGameObject implements GameObject {

    private final Position position;

    /**
     * Creates a {@code AbstractGameObject}.
     * 
     * @param breakable the possibility to break the object
     * @param position object's position
     */
    public AbstractGameObject(final Position position) {
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
    public Rectangle2D getBounds() {
        return new Rectangle2D.Double(this.getPosition().getX(), this.getPosition().getY(),
                   Tile.SPRITE_SIZE, Tile.SPRITE_SIZE);
    }

}
