package model;

import java.awt.Graphics2D;

import graphics.GraphicsComponent;
import graphics.Sprite;
import graphics.StillObjectGraphicComponent;
import utilities.Position;

/**
 * This class contains all informations about a block.
 */
public class Block extends AbstractStillObject {

    private final GraphicsComponent graphicComponent;
    /**
     * Constructor.
     * @param breakable : the possibility to break the block
     * @param position : block's position
     * @param sprite : block's sprite
     */
    public Block(final boolean breakable, final Position position, final Sprite sprite) {
        super(true, breakable, position);
        this.graphicComponent = new StillObjectGraphicComponent(sprite);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final Graphics2D g) {
        this.graphicComponent.render(g, super.getPosition());
    }

}
