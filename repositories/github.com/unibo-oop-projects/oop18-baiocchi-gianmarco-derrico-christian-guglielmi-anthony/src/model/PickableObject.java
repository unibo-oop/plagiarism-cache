package model;

import java.awt.Graphics2D;

import graphics.GraphicsComponent;
import graphics.Sprite;
import graphics.StillObjectGraphicComponent;
import utilities.Position;

/**
 * This class manage all the objects that could be taken by the player.
 */
public class PickableObject extends AbstractStillObject {

    private final PowerUPAction action;
    private final GraphicsComponent graphicComponent;

    /**
     * Constructor.
     * @param position : object's position
     * @param action : the propriety of the object
     * @param sprite : the sprite of the object
     */
    public PickableObject(final Position position, final PowerUPAction action, 
            final Sprite sprite) {
        super(true, false, position);
        this.action = action;
        this.graphicComponent = new StillObjectGraphicComponent(sprite);
    }

    /**
     * Getter for the propriety action of the object.
     * @return pickable object's propriety action
     */
    public PowerUPAction getAction() {
        return this.action;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final Graphics2D g) {
        this.graphicComponent.render(g, super.getPosition());
    }

}
