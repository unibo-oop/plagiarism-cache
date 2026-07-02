package graphics;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import utilities.Position;

/**
 * This class represents the graphic of a block. 
 */
public class StillObjectGraphicComponent implements GraphicsComponent {

    private final Sprite sprite;

    /**
     * Constructor.
     * @param sprite : block's sprite
     */
    public StillObjectGraphicComponent(final Sprite sprite) {
        this.sprite = sprite;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final Graphics2D g, final Position position) {
        g.drawImage(this.sprite.getBufferedImage(), 
                AffineTransform.getTranslateInstance(position.getX(), position.getY()), null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadSprites(final SpriteSheet sheet) {
        //TODO
    }

}
