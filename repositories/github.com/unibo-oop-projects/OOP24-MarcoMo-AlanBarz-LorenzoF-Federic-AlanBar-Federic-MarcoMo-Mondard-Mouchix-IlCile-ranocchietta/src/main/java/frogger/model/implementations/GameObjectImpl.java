package frogger.model.implementations;

import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import frogger.common.LoadSave;
import frogger.common.Pair;
import frogger.common.Position;
import frogger.model.interfaces.GameObject;

/**
 * Implementation of GameObject.
 * Abstract base class representing a general game object with position, dimension,
 * image, and collision hitbox. Implements common behavior shared by all game objects.
 */
public abstract class GameObjectImpl implements GameObject {
    /** The current position of the object in the game world.*/
    private Position pos;
    /** The dimension (width and height in blocks) of the object.*/
    private final Pair dimension;
    private Rectangle2D.Float hitbox;
    private BufferedImage img;

    /**
     * Constructs a game object with the specified position and dimension.
     * Initializes the hitbox based on those values.
     *
     * @param pos       the initial position of the object
     * @param dimension the width and height of the object
     */
    public GameObjectImpl(final Position pos, final Pair dimension) {
        this.pos = pos;
        this.dimension = dimension;
        initHitBox();
    }

    /**
     * Initializes the hitbox to match the current position and dimensions.
     */
    private void initHitBox() {
        this.hitbox = new Rectangle2D.Float(this.pos.x(), this.pos.y(), this.dimension.width(), this.dimension.height());
    }

    /**
     * Updates the hitbox coordinates to match the current position.
     */
    protected void updateHitBox() {
        this.hitbox.x = this.pos.x();
        this.hitbox.y = this.pos.y();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressFBWarnings(
        value = "EI_EXPOSE_REP",
        justification = "HitBox is managed externally and this exposure is intentional"
    )
    @Override
    public Rectangle2D.Float getHitBox() {
        return new Rectangle2D.Float(
            this.hitbox.x,
            this.hitbox.y,
            this.hitbox.width,
            this.hitbox.height);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Position getPos() {
        return this.pos;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPos(final Position pos) {
        this.pos = pos;
        updateHitBox();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair getDimension() {
        return dimension;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BufferedImage getImage() {
        if (this.img == null) {
            return null;
        }

        final BufferedImage copy = new BufferedImage(
            this.img.getWidth(),
            this.img.getHeight(),
            this.img.getType()
        );
        copy.setData(this.img.getData());
        return copy;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setImage(final String fileName) {
        img = LoadSave.getSprite(fileName);
    }
}
