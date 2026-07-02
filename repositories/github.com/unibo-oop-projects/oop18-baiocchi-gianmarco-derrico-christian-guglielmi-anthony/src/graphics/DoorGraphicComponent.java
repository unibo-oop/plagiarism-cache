package graphics;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import game.theme.DoorSprite;
import model.Door;
import utilities.Position;

/**
 * This class represents the door's graphic.
 */
public class DoorGraphicComponent implements GraphicsComponent {

    private final Door door;
    private final DoorSprite doorSprite;

    /**
     * Constructor.
     * @param door : the logic of the door
     * @param doorSprite : door's sprites
     */
    public DoorGraphicComponent(final Door door, final DoorSprite doorSprite) {
        this.door = door;
        this.doorSprite = doorSprite;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final Graphics2D g, final Position position) {
        if (this.door.isOpen()) {
            g.drawImage(this.doorSprite.getOpenDoor().getBufferedImage(),
                    AffineTransform.getTranslateInstance(this.door.getPosition().getX(), this.door.getPosition().getY()),
                    null);
        } else {
            g.drawImage(this.doorSprite.getClosedDoor().getBufferedImage(),
                    AffineTransform.getTranslateInstance(this.door.getPosition().getX(), this.door.getPosition().getY()),
                    null);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadSprites(final SpriteSheet sheet) {
        //TODO
    }

}
