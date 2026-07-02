package it.unibo.bmbman.view.entities;

import java.awt.Image;
import it.unibo.bmbman.model.utilities.Dimension;
import it.unibo.bmbman.model.utilities.EntityType;
import it.unibo.bmbman.model.utilities.Position;
import it.unibo.bmbman.view.utilities.SpriteSheet;
/**
 * Used to show a block sprite in the maze.
 *
 */
public class BlockView extends AbstractEntityView {
    private static final String BLOCK_PATH = "/block.png";
    private static final int BLOCK_SPRITE_DIMENSION = 50;
    private final SpriteSheet ss = new SpriteSheet(BLOCK_PATH);
    private final Image idleImage = ss.getSprite(1, 1, BLOCK_SPRITE_DIMENSION);
    /**
     * Used to create a block view image.
     * @param position the block position
     */
    public BlockView(final Position position) {
        super(position, new Dimension(BLOCK_SPRITE_DIMENSION, BLOCK_SPRITE_DIMENSION), true, EntityType.BLOCK);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Image getSprite() {
        return idleImage;
    }
}
