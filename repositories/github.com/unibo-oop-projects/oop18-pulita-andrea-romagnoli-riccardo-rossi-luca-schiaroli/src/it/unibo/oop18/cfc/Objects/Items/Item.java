package it.unibo.oop18.cfc.Objects.Items;

import java.awt.Graphics2D;

import it.unibo.oop18.cfc.Objects.AbstractGameObject;
import it.unibo.oop18.cfc.TileMap.Tile;
import it.unibo.oop18.cfc.Util.Position;

/**
 * This class manages a block.
 */
public class Item extends AbstractGameObject {

    private final Tile sprite;
    /**
     * Creates a generic {@code Block}.
     * 
     * @param breakable the possibility to break the block
     * @param position block's position
     * @param sprite block's sprite
     */
    public Item(final Position position, final Tile sprite) {
        super(position);
        this.sprite = sprite;
    }
}
