package it.unibo.oop18.cfc.Objects.Entity;

import java.awt.geom.Rectangle2D;

import it.unibo.oop18.cfc.Objects.AbstractGameObject;
import it.unibo.oop18.cfc.TileMap.Tile;
import it.unibo.oop18.cfc.TileMap.TileMap;
import it.unibo.oop18.cfc.Util.Position;

/**
 * This class is an abstract implementation of {@link DynamicObject}.
 * It defines a generic dynamic game entity.
 */
public abstract class AbstractEntity extends AbstractGameObject implements DynamicObject {

    private final TileMap world;

    /**
     * Creates an {@code AbstractEntity}.
     * 
     * @param position initial entity
     * @param breakable is a boolean value that says if entity is breakable or not
     * @param world reference 
     */
    public AbstractEntity(final Position position, final TileMap world) {
    	super(position);
        this.world = world;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TileMap getTileMap() {
        return this.world;
    }
}
