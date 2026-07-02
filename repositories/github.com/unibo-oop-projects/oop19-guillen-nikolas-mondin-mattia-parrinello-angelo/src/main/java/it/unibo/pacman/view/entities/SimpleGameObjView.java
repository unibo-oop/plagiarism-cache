package it.unibo.pacman.view.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import it.unibo.pacman.model.utilities.EntityType;
import it.unibo.pacman.model.utilities.Position;
/**
 * An implementation of {@link EntityView}.
 */
public class SimpleGameObjView implements EntityView {
    private final EntityType type;
    private Position position;
    private final BufferedImage image;
    private final int width;
    private final int height;
    /**
     * Construct an implementation of {@link EntityView}.
     * 
     * @param type   the type of the entity handled graphically
     * @param image  the image associated with the entity
     * @param width  the width of the image
     * @param height the height of the image
     */
    protected SimpleGameObjView(final EntityType type, final BufferedImage image, final int width, final int height) {
        this.type = type;
        this.image = image;
        this.width = width;
        this.height = height;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public EntityType getType() {
        return type;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final Graphics g) {
        g.drawImage(image, position.getX(), position.getY(), width, height, null);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void updatePosition(final Position position) {
        this.position = position;
    }
    /**
     * Getter for the actual position.
     * 
     * @return the last position
     */
    public Position getPosition() {
        return this.position;
    }
    /**
     * Getter for the actual width of the view.
     * 
     * @return the width of the view
     */
    public int getWidth() {
        return width;
    }
    /**
     * Getter for the actual height of the view.
     * 
     * @return the height of the view
     */
    public int getHeight() {
        return height;
    }
}
