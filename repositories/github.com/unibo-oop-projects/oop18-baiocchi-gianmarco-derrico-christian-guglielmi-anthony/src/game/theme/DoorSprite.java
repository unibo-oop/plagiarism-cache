package game.theme;

import graphics.Sprite;
import graphics.SpriteSheet;

/**
 * Class to store the door sprites.
 */
public class DoorSprite {

    private static final int Y_LOCATION = 1;
    private static final int OPEN_DOOR_LOCATION = 4;
    private static final int CLOSED_DOOR_LOCATION = 5;

    private final Sprite openDoor;
    private final Sprite closedDoor;

    /**
     * Constructor that initialize door sprites.
     * @param sheet : png sheet where to tale the sprites
     */
    public DoorSprite(final SpriteSheet sheet) {
        this.openDoor = new Sprite(sheet, OPEN_DOOR_LOCATION, Y_LOCATION);
        this.closedDoor = new Sprite(sheet, CLOSED_DOOR_LOCATION, Y_LOCATION);
    }

    /**
     * Open door sprite getter.
     * @return open door sprite
     */
    public Sprite getOpenDoor() {
        return this.openDoor;
    }

    /**
     * Closed door sprite getter.
     * @return closed door sprite
     */
    public Sprite getClosedDoor() {
        return this.closedDoor;
    }
}
