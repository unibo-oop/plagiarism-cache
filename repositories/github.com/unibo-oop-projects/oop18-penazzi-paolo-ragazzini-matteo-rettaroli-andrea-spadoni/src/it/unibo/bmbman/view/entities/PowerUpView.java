package it.unibo.bmbman.view.entities;

import java.awt.Image;

import it.unibo.bmbman.model.utilities.Dimension;
import it.unibo.bmbman.model.utilities.EntityType;
import it.unibo.bmbman.model.utilities.Position;
import it.unibo.bmbman.view.utilities.ScreenToolUtils;
import it.unibo.bmbman.view.utilities.SpriteSheet;
/**
 * Create the power-up view.
 */
public class PowerUpView extends AbstractEntityView {
    private static final int DIMENSION = 48;
    private final Image idleImage;
    /**
     * Constructor for a general power-up.
     * @param position where is the power-up in our terrain
     * @param path the path of the image to load.
     */
    public PowerUpView(final Position position,  final String path) {
        super(new Position(position.getX() * ScreenToolUtils.SCALE, position.getY() * ScreenToolUtils.SCALE), new Dimension(DIMENSION, DIMENSION), true, EntityType.POWER_UP);
        final String imagePath = path;
        final SpriteSheet ss = new SpriteSheet(imagePath);
        this.idleImage = ss.getSprite(1, 1, DIMENSION);
    }
    /**
     * Load the image of the power-up.
     */
    @Override
    public Image getSprite() {
        return idleImage;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setPosition(final Position position) {
    }
}
