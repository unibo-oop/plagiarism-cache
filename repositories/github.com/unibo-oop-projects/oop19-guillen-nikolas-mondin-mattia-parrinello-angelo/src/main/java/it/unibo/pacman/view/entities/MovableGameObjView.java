package it.unibo.pacman.view.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Map;

import it.unibo.pacman.model.utilities.Direction;
import it.unibo.pacman.model.utilities.EntityType;
import it.unibo.pacman.model.utilities.Pair;
import it.unibo.pacman.model.utilities.Status;
/**
 * An implementation of {@link MovableView}.
 */
public class MovableGameObjView extends SimpleGameObjView implements MovableView {
    private Status status;
    private Direction direction;
    private final Map<Pair<Direction, Status>, BufferedImage> images;
    /**
     * Constructor that extends the constructor of {@link SimpleGameObjView}.
     * 
     * @param type   {@inheritDoc}}
     * @param width  {@inheritDoc}
     * @param height {@inheritDoc}
     * @param images the map that associates each direction an image.
     */
    protected MovableGameObjView(final EntityType type, final int width, final int height,
            final Map<Pair<Direction, Status>, BufferedImage> images) {
        super(type, null, width, height);
        this.images = images;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final Graphics g) {
        g.drawImage(images.get(new Pair<>(this.direction, this.status)), this.getPosition().getX(),
                this.getPosition().getY(), this.getWidth(), this.getHeight(), null);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void updateStatus(final Status status) {
        this.status = status;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void updateDirection(final Direction newDir) {
        this.direction = newDir;
    }
}
