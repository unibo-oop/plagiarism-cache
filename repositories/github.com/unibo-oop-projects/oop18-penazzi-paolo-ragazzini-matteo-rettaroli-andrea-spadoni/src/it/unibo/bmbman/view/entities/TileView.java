package it.unibo.bmbman.view.entities;


import java.awt.Image;
import it.unibo.bmbman.model.utilities.Dimension;
import it.unibo.bmbman.model.utilities.EntityType;
import it.unibo.bmbman.model.utilities.Position;
import it.unibo.bmbman.view.utilities.SpriteSheet;
/**
 * Used to show a tile sprite in the maze.
 *
 */
public class TileView extends AbstractEntityView { 
    private static final String TILE_PATH = "/tile.png";
    private static final int TILE_SPRITE_DIMENSION = 50;
    private final SpriteSheet ss = new SpriteSheet(TILE_PATH);
    private final Image idleImage = ss.getSprite(1, 1, TILE_SPRITE_DIMENSION);
    /**
     * Used to create a tile view image.
     * @param position the tile position
     */
    public TileView(final Position position) {
        super(position, new Dimension(TILE_SPRITE_DIMENSION, TILE_SPRITE_DIMENSION), true, EntityType.TILE);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Image getSprite() {
        return idleImage;
    }
}
