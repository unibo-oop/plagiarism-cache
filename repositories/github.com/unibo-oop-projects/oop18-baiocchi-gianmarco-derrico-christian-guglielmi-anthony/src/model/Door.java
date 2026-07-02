package model;

import java.awt.Graphics2D;

import game.theme.DoorSprite;
import graphics.DoorGraphicComponent;
import graphics.GraphicsComponent;
import utilities.Position;

/**
 * This class manage a simple door.
 */
public class Door extends AbstractStillObject {

    private boolean unlocked;
    private final GraphicsComponent graphicComponent;

    /**
     * Constructor.
     * @param position : door's position
     * @param doorSprite : door's sprites
     */
    public Door(final Position position, final DoorSprite doorSprite) {
        super(true, false, position);
        this.unlocked = false;
        this.graphicComponent = new DoorGraphicComponent(this, doorSprite);
    }

    /**
     * The door will be opened.
     */
    public void open() {
        this.unlocked = true;
    }

    /**
     * The door will be closed.
     */
    public void close() {
        this.unlocked = false;
    }

    /**
     * Getter for the field open.
     * @return true if the door is open, otherwise false
     */
    public boolean isOpen() {
        return this.unlocked;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final Graphics2D g) {
        this.graphicComponent.render(g, super.getPosition());
    }

}
