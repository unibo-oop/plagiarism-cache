package physics;

import java.awt.geom.Rectangle2D;

import graphics.SpriteSheet;
import model.GameObject;

/**
 * This class manage the object's physic.
 */
public class PhysicComponentImpl implements PhysicComponent {

    private final GameObject object;

    /**
     * Constructor.
     * @param object : the entire object
     */
    public PhysicComponentImpl(final GameObject object) {
        this.object = object;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Rectangle2D getBounds() {
        return new Rectangle2D.Double(this.object.getPosition().getX(), this.object.getPosition().getY(),
                SpriteSheet.SPRITE_SIZE_IN_GAME, SpriteSheet.SPRITE_SIZE_IN_GAME);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObject getObject() {
        return this.object;
    }

}
