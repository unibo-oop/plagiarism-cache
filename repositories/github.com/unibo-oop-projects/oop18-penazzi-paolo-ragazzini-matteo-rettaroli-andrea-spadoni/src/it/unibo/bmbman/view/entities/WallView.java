package it.unibo.bmbman.view.entities;


import java.awt.Image;
import it.unibo.bmbman.model.utilities.Dimension;
import it.unibo.bmbman.model.utilities.EntityType;
import it.unibo.bmbman.model.utilities.Position;
import it.unibo.bmbman.view.utilities.SpriteSheet;
/**
 * Used to show a wall sprite in the maze.
 *
 */
public class WallView extends AbstractEntityView {
    private static final String WALL_PATH = "/wall.png";
    private static final int WALL_SPRITE_DIMENSION = 50;
    private final SpriteSheet ss = new SpriteSheet(WALL_PATH);
    private final Image idleImage = ss.getSprite(1, 1, WALL_SPRITE_DIMENSION);
    /**
     * Used to create a wall view image.
     * @param position the position of the wall
     */
    public WallView(final Position position) {
        super(position, new Dimension(WALL_SPRITE_DIMENSION, WALL_SPRITE_DIMENSION), true, EntityType.WALL);
    }
    /**
     *{@inheritDoc}
     */
    @Override
    public Image getSprite() {
        return idleImage;
    }
}
